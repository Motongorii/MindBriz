package com.example.mindbriz

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class SigninActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var bSignIn:Button
    lateinit var edtemail: EditText
    lateinit var edtpassword: EditText
    lateinit var progress: ProgressDialog
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val tvSignUp = findViewById<TextView>(R.id.tvSignUp)
        tvForgotPassword.setOnClickListener(this)
        tvSignUp.setOnClickListener(this)


        edtemail = findViewById(R.id.etEmail)
        edtpassword = findViewById(R.id.etPassword)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this,)
        progress.setTitle("Loading")
        progress.setMessage("Please wait ...")

        bSignIn.setOnClickListener {
            var email = edtemail.text.toString().trim()
            var password = edtpassword.text.toString().trim()

            if (email.isEmpty()){
                edtemail.setError("Please fill this input")
                edtemail.requestFocus()
            }else if(password.isEmpty()){
                edtpassword.setError("Please fill the input")
                edtpassword.requestFocus()
            }else{
                // Proceed to register  the user
                progress.show()
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    progress.dismiss()
                    if (it.isSuccessful){
                        Toast.makeText(this, "Registration successful",
                            Toast.LENGTH_SHORT).show()
                        mAuth.signOut()
                        startActivity(Intent(this@SigninActivity,MainActivity::class.java))
                        finish()

                    }else{
                        displaymessage("Error", it.exception!!.message.toString())
                    }
                }
            }


        }


    }

    private fun displaymessage(title:String, message:String) {
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok",null)
        alertDialog.create().show()
    }

    override fun onClick(view: View) {
        when (view.id) {
                    R.id.tvForgotPassword -> startActivity(
                Intent(this@SigninActivity, ForgotPasswordActivity::class.java))

            R.id.tvSignUp -> startActivity(Intent(this@SigninActivity, RegisterActivity::class.java))

        }
    }

    companion object {
        var LOG_TAG = "MindBriz"
    }
}