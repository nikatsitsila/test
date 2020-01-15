package com.example.testproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        LoginBtn.setOnClickListener {
            val e: String = EmailBtn.text.toString()
            val p: String = PasswordBtn.text.toString()

            if (TextUtils.isEmpty(e) || TextUtils.isEmpty(p)) {
                Toast.makeText(this, "ველი ცარიელია", Toast.LENGTH_LONG).show()
            } else {


                auth.signInWithEmailAndPassword(e, p)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "ავტორიზაცია წარმატებულია",
                                Toast.LENGTH_LONG
                            ).show()
                            val intent = Intent(this, UserActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(this, "მომხმარებლის პაროლი ან ლოგინი არასწორია!", Toast.LENGTH_LONG).show()
                        }


                    })

            }
        }

        RegistrationBtn.setOnClickListener {

            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            finish()

        }

        ForgotPasswordBtn.setOnClickListener {

            val intent = Intent(this, ReNewPassActivity::class.java)
            startActivity(intent)
            finish()
        }




    }
}
