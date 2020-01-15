package com.example.testproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_re_new_pass.*

class ReNewPassActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_re_new_pass)


        auth = FirebaseAuth.getInstance()




        Back3Btn.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        GetLinkBtn.setOnClickListener {
            val email: String = inputEmail.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "მიუთითეთ მეილი და არაფერი მეტი", Toast.LENGTH_LONG).show()

            } else {

                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "წარმატებით გაიგზავნა", Toast.LENGTH_LONG).show()

                            val  intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(this, "გაგზავნა ვერ მოხერხდა", Toast.LENGTH_LONG).show()
                        }
                    })
            }
        }
    }
}
