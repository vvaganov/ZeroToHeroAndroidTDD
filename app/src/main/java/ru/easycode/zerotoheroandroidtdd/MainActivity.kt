package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.transition.Visibility

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.actionButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val text = findViewById<TextView>(R.id.titleTextView)


        button.setOnClickListener{
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
            button.postDelayed({
                text.visibility = View.VISIBLE
                progressBar.visibility =View.GONE
                button.isEnabled = true
            }, 3000)

        }

    }
}