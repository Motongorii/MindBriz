package com.example.mindbriz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SigninActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val bSignIn = findViewById<Button>(R.id.bSignIn)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)
        bSignIn.setOnClickListener(this)
        tvForgotPassword.setOnClickListener(this)
        tvSignUp.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.bSignIn -> Log.i(SigninActivity.Companion.LOG_TAG, "Sign in clicked.")
            R.id.tvForgotPassword -> startActivity(
                Intent(this@SigninActivity, ForgotPasswordActivity::class.java))

            R.id.tvSignUp -> startActivity(Intent(this@SigninActivity, RegisterActivity::class.java))
        }
    }

    companion object {
        var LOG_TAG = "MindBriz"
    }
}