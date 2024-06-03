package com.rajapps.otplogin.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.rajapps.otplogin.MainActivity
import com.rajapps.otplogin.R
import com.rajapps.otplogin.Utils
import com.rajapps.otplogin.databinding.ActivityOtpactivityBinding

class OTPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        Utils.getCorrectOTPSize(this@OTPActivity, binding.userOTP, binding.verifyOtpBtn)

        binding.verifyOtpBtn.setOnClickListener {
            if (binding.userOTP.text!!.isEmpty()) {
                Utils.showToast(this, "Please enter otp")
            } else {
                verifyUser(binding.userOTP.text.toString())
            }

        }

    }

    private fun verifyUser(otp: String) {
        Utils.showDialog(this@OTPActivity, "Verifying please wait")
        val credential =
            PhoneAuthProvider.getCredential(intent.getStringExtra("verificationId")!!, otp)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Utils.hideDialog()

                    var userNumber = intent.getStringExtra("number")!!

                    val preferences = this.getSharedPreferences("user", MODE_PRIVATE)
                    val editor = preferences.edit()
                    editor.putString("number", userNumber)
                    editor.apply()

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()


                } else {
                    Utils.hideDialog()

                    Utils.showToast(this, "Something went wrong")
                }
            }
    }





}