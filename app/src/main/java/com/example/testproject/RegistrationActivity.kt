package com.example.testproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        auth = FirebaseAuth.getInstance()

        RegistrBtn.setOnClickListener {
            val email: String = Email3Btn.text.toString()
            val password: String = Password3Btn.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "შეავსეთ ცარიელი ველები", Toast.LENGTH_LONG).show()

            } else {

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(this, "წარმატებულად შეიქმნა", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, UserActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(this, "რაღაც არასწორია", Toast.LENGTH_LONG).show()
                        }

                    })
            }

        }

        back3btn.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
