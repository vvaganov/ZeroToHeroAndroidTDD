package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var  linerLayout: LinearLayout
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.titleTextView)
        linerLayout = findViewById(R.id.rootLayout)
        button = findViewById<Button>(R.id.removeButton)
        button.setOnClickListener{
            linerLayout.removeView(textView)
            button.isEnabled = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val removeTextView = linerLayout.childCount == 1
        val disableButton = button.isEnabled
        outState.putBoolean("key", removeTextView)
        outState.putBoolean("key2",disableButton )
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        button.isEnabled = savedInstanceState.getBoolean("key2")
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getBoolean("key")){
            linerLayout.removeView(textView)
        }
    }
}