package com.rajapps.otplogin.auth

data class UserModel(
    val userName: String? = "",
    val userPhoneNumber: String? = "",
    val village: String? = "",
    val state: String? = "",
    val city: String? = "",
    val pinCode: String? = ""
)