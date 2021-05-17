package com.test.marvel.data.server.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
        @SerializedName("user") val user : UserData?,
        @SerializedName("access_token") val access_token : String?,
        @SerializedName("message") val message : String?
)

data class UserData (
        @SerializedName("id") val id : Int?,
        @SerializedName("name") val name : String?,
        @SerializedName("surname") val surname : String?,
        @SerializedName("email") val email : String?,
        @SerializedName("email_verified_at") val email_verified_at : String?,
        @SerializedName("dni") val dni : String?,
        @SerializedName("phone") val phone : Int?,
        @SerializedName("address") val address : String?,
        @SerializedName("user_id") val user_id : Int?,
        @SerializedName("patient_id") val patient_id : Int?,
        @SerializedName("doctor_id") val doctor_id : Int?,
        @SerializedName("doctor_name") val doctor_name : String?,
        @SerializedName("created_at") val created_at : String?,
        @SerializedName("updated_at") val updated_at : String?
)