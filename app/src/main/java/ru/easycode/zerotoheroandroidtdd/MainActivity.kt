package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private var state: State = State.Initial

    private lateinit var textView: TextView
    private lateinit var linerLayout: LinearLayout
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.titleTextView)
        linerLayout = findViewById(R.id.rootLayout)
        button = findViewById(R.id.removeButton)
        button.setOnClickListener {
            state = State.Remove
            state.apply(linerLayout, textView, button)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("key", state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = savedInstanceState.getSerializable("key") as State
        state.apply(linerLayout, textView, button)
    }
}


interface State : Serializable {

    fun apply(rootLayout: LinearLayout, textView: TextView, button: Button)

    object Initial : State {

        override fun apply(rootLayout: LinearLayout, textView: TextView, button: Button) = Unit
    }

    object Remove : State {
        override fun apply(rootLayout: LinearLayout, textView: TextView, button: Button) {
            rootLayout.removeView(textView)
            button.isEnabled = false
        }
    }
}