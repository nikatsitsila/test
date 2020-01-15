package com.example.testproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_update_pass.*

class UpdatePassActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pass)

        auth = FirebaseAuth.getInstance()


        confirmBtn.setOnClickListener {

            val password: String = inputNewPassword.text.toString()

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "გთხოვთ შეავსოთ მოცემული ველი", Toast.LENGTH_SHORT).show()

            } else {
                auth.currentUser?.updatePassword(password)
                    ?.addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(this, "პაროლი შეიცვალა", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, UserActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {

                            Toast.makeText(this, "პაროლის შეცვლა ვერ მოხერხდა", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
            }
        }



        Back2Btn.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}
