package com.example.mindbriz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val bSignUp = findViewById<Button>(R.id.bSignUp)
        val tvSignIn = findViewById<TextView>(R.id.tvSignIn)
        bSignUp.setOnClickListener(this)
        tvSignIn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.bSignUp -> Log.i(SigninActivity.LOG_TAG, "Sign up clicked.")
            R.id.tvSignIn -> finish()
        }
    }
}