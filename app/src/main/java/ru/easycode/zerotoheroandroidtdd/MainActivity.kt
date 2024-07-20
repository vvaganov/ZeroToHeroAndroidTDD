package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var rootLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.titleTextView)
        val button = findViewById<Button>(R.id.removeButton)
        rootLayout = findViewById(R.id.rootLayout)
        button.setOnClickListener {
            rootLayout.removeView(textView)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        val removeTextView = rootLayout.childCount == 1
        outState.putBoolean("key", removeTextView)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val removeTextView = savedInstanceState.getBoolean("key")
        if (removeTextView) rootLayout.removeView(textView)
    }
}