package com.example.market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegistrationActivity : AppCompatActivity() {

    lateinit var  emailEditText : TextInputEditText
    lateinit var  passwordEditText : TextInputEditText
    lateinit var registrationButton : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        supportActionBar?.hide()

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        registrationButton = findViewById(R.id.registrationButton)


        registrationButton.setOnClickListener() {

            if (emailEditText.text!!.isNotEmpty() && passwordEditText.text!!.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
                    .addOnCompleteListener(
                        OnCompleteListener { task ->

                            if (task.isSuccessful) {
                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                emailEditText!!.text!!.clear()
                                passwordEditText!!.text!!.clear()
                                Toast.makeText(this, "registration completed successfully", Toast.LENGTH_SHORT).show()

                            }
                        }
                    )
                    .addOnFailureListener({
                        Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                    })
            }
            else{
                Toast.makeText(this, "something went wrong ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}