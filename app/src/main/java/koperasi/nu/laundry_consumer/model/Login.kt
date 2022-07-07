package koperasi.nu.laundry_consumer.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("usernameOrEmail") val usernameOrEmail: String?,

    @SerializedName("password") val password: String?
)