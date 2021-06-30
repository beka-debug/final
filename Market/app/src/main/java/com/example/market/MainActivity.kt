package com.example.market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_log_in.*

class MainActivity : AppCompatActivity() {

    lateinit var emaileditText : TextInputEditText
    lateinit var passwordEditText : TextInputEditText




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        supportActionBar?.hide()

        emaileditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

    }

    fun OnLoginButtonClick(view:View){
        
        if(emaileditText.text!!.isNotEmpty()&&passwordEditText.text!!.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                emaileditText.text.toString(),
                passwordEditText.text.toString()
            )
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainNavigationActivity::class.java)
                        startActivity(intent)
                    } else
                        Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT)
                            .show()
                }
        }
        else{
            Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT)
                .show()
        }

    }

    fun onRegistrationClick(view:View){
        val intent = Intent(this , RegistrationActivity::class.java)
        startActivity(intent)
    }


}