package com.example.a40qula


import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserInfo(
    val name: String = "",
    val mobile: String? = "",
    val hobby: String = ""
)