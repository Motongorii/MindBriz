package com.example.mindbriz

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var progress: ProgressDialog
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val bSignUp = findViewById<Button>(R.id.bSignUp)
        val tvSignIn = findViewById<TextView>(R.id.tvSignIn)
        bSignUp.setOnClickListener(this)
        tvSignIn.setOnClickListener(this)

        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this,)
        progress.setTitle("Loading")
        progress.setMessage("Please wait ...")

        bSignUp.setOnClickListener {

        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.bSignUp -> startActivity(Intent(this@RegisterActivity,MainActivity::class.java))
            R.id.tvSignIn -> finish()
        }
    }
}