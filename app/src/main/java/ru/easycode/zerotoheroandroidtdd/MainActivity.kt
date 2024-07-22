package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.Serializable
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {

//    private var state: State = State.Implement


    private val base = Count.Base(2)
//    private var increment: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.incrementButton)
        val textView = findViewById<TextView>(R.id.countTextView)

//        button.setOnClickListener {
//            increment += 2
//            state = State.Increment
//            state.increment(textView, increment)
//        }

        button.setOnClickListener{
            textView.text = base.increment(textView.text.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        outState.putString("key", textView.text.toString())

//        outState.putInt("Int", increment)
//        outState.putSerializable("Key", state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
//        textView.text = savedInstanceState.getString("key")

//        state = savedInstanceState.getSerializable("Key") as State
//        increment = savedInstanceState.getInt("Int")
//        state.increment(textView, increment)
    }
}

interface State : Serializable {
    fun increment(textView: TextView, increment: Int)


    object Implement : State {
        override fun increment(textView: TextView, increment: Int) = Unit
    }

    object Increment : State {
        override fun increment(textView: TextView, increment: Int) {
            val textIn = increment.toString()
            textView.text = textIn
        }
    }
}

interface Count {
    fun increment(number: String): String

    class Base(val step: Int) : Count {

        init {
            if (step <= 0) {
                throw IllegalStateException("step should be positive, but was $step")
            }
        }
        override fun increment(number: String): String {
            return (number.toInt() + step).toString()
        }
    }
}