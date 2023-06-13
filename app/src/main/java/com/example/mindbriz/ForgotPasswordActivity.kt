package com.example.mindbriz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        val bRecoveredPassword = findViewById<Button>(R.id.bRecoverPassword)
        val tvSignIn = findViewById<TextView>(R.id.tvSignIn)
        bRecoveredPassword.setOnClickListener(this)
        tvSignIn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.bRecoverPassword -> Log.i(SigninActivity.LOG_TAG, "Recover password clicked.")
            R.id.tvSignIn -> finish()
        }
    }
}