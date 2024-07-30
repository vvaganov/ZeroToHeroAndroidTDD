package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.Serializable
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {

    private val base = Count.Base(2,4)
    private var uiState:UiState = UiState.Base("0")

    private lateinit var bottom: Button
    private  lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom = findViewById(R.id.incrementButton)
        textView = findViewById(R.id.countTextView)

        bottom.setOnClickListener {
            uiState = base.increment(textView.text.toString())
            uiState.apply(textView, bottom)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("key", uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        uiState = savedInstanceState.getSerializable("key") as UiState
        uiState.apply(textView, bottom)
    }
}



interface Count {

    fun increment(number: String): UiState

    class Base(val step: Int, val max: Int) : Count {

        init {
            if (step < 1) throw IllegalStateException("step should be positive, but was $step")
            if (max < 1) throw IllegalStateException("max should be positive, but was $max")
            if( max < step) throw IllegalStateException("max should be more than step")
        }

        override fun increment(number: String): UiState {

            val digit = number.toInt()
            val result = digit + step
            return if (result + step <= max){
                UiState.Base(result.toString())
            } else {
                UiState.Max(result.toString())
            }
        }
    }
}

interface UiState: Serializable{

    fun apply(textView: TextView, button:Button)


    data class Base(val text:String):UiState {

        override fun apply(textView: TextView, button: Button) {
            textView.text = text
        }
    }

    data class Max(val text: String):UiState {
        override fun apply(textView: TextView, button: Button) {
            textView.text = text
            button.isEnabled = false
        }
    }
}



