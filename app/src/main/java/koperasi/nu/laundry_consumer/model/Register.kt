package koperasi.nu.laundry_consumer.model

import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("email")
    val email: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("telepon")
    val telepon: String,
    @SerializedName("password")
    val password: String
)