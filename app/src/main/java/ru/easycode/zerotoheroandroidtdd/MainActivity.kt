package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private var state: State = State.Initial

    private lateinit var textView: TextView
    private lateinit var rootLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.titleTextView)
        val button = findViewById<Button>(R.id.removeButton)
        rootLayout = findViewById(R.id.rootLayout)
        button.setOnClickListener {
            state = State.Remove
            state.apply(rootLayout, textView)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("key", state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            state = savedInstanceState.getSerializable("key", State::class.java) as State
        } else{
            savedInstanceState.getSerializable("key") as State
        }
        state.apply(rootLayout, textView)

    }
}

interface State: Serializable {

    fun apply(linerLayout: LinearLayout, textView: TextView)

    object Initial : State {

        override fun apply(rootLayout: LinearLayout, textView: TextView) = Unit
    }

    object Remove : State {

        override fun apply(rootLayout: LinearLayout, textView: TextView) {
            rootLayout.removeView(textView)
        }
    }
}



