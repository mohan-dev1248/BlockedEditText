package com.asura.blockededittext

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.asura.blockededittext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "BlockedEditText"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.editText1.addTextChangedListener(GenericTextWatcher(binding.editText2, binding.editText1))

        binding.editText2.addTextChangedListener(GenericTextWatcher(binding.editText3, binding.editText1))
        binding.editText2.setOnKeyListener(GenericKeyListener(binding.editText1))
        binding.editText3.addTextChangedListener(GenericTextWatcher(binding.editText4, binding.editText2))
        binding.editText3.setOnKeyListener(GenericKeyListener(binding.editText2))
        binding.editText4.addTextChangedListener(GenericTextWatcher(binding.editText5, binding.editText3))
        binding.editText4.setOnKeyListener(GenericKeyListener(binding.editText3))
        binding.editText5.addTextChangedListener(GenericTextWatcher(binding.editText6, binding.editText4))
        binding.editText5.setOnKeyListener(GenericKeyListener(binding.editText4))
        binding.editText6.addTextChangedListener(GenericTextWatcher(binding.editText6, binding.editText5))
        binding.editText6.setOnKeyListener(GenericKeyListener(binding.editText5))
    }

    inner class GenericTextWatcher(private val etNext: EditText, private val etPrev: EditText) :
        TextWatcher {
        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            if (text.length == 1) etNext.requestFocus() else if (text.isEmpty()) etPrev.requestFocus()
        }

        override fun beforeTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int
        ) {
        }

        override fun onTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int
        ) {
        }
    }

    inner class GenericKeyListener(
        private val etPrevious: EditText
    ): View.OnKeyListener{

        override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
            if(v is EditText){
                if(v.text.toString().isNullOrEmpty() &&  keyCode == KeyEvent.KEYCODE_DEL
                    && event?.action == KeyEvent.ACTION_DOWN){
                    etPrevious.setText("")
                    etPrevious.requestFocus()
                }
            }
            return false
        }
    }
}
