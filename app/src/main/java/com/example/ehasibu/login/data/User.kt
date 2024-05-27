package com.example.ehasibu.login.data

import com.google.gson.annotations.SerializedName

data class User(@SerializedName ("_id")
                val id :String,
                val businessName: String,
                val firstName:String,
                val lastName: String,
                val otherNames: String,
                val email: String,
                val password: String,
                val confirmPassword: String,
                )
