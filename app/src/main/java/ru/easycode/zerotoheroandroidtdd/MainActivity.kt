package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.hideButton)
        textView = findViewById(R.id.titleTextView)

        button.setOnClickListener {
            textView.visibility = View.INVISIBLE

            Log.d("!!!", "onCreate: ${textView.visibility}")
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("key", textView.visibility)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textView.visibility = savedInstanceState.getInt("key")
    }
}
