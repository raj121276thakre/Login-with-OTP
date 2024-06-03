package com.rajapps.otplogin

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.rajapps.otplogin.databinding.ProgressDialogBinding

object Utils {


    private var dialog: AlertDialog? = null
    fun showDialog(context: Context, message: String) {
        val progress = ProgressDialogBinding.inflate(LayoutInflater.from(context))
        progress.tvMessage.text = message
        dialog = AlertDialog.Builder(context).setView(progress.root).setCancelable(false).create()
        dialog!!.show()
    }

    fun hideDialog() {
        dialog?.dismiss()
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    fun getCorrectUserPhoneNumberSize(
        context: Context,
        textInputEditText: TextInputEditText,
        button: MaterialButton
    ) {

        textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(number: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val len = number?.length

                if (len == 10) {
                    button.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.green
                        )
                    )
                } else {
                    button.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.grayish_blue
                        )
                    )
                }


            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }


    fun getCorrectOTPSize(
        context: Context,
        textInputEditText: TextInputEditText,
        button: MaterialButton
    ) {

        textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(number: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val len = number?.length

                if (len == 6) {
                    button.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.green
                        )
                    )
                } else {
                    button.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.grayish_blue
                        )
                    )
                }


            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }


}