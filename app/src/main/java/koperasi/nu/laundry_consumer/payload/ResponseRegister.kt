package koperasi.nu.laundry_consumer.payload

import com.google.gson.annotations.SerializedName

data class ResponseRegister (
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("noTelp")
    val noTelp: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("roles")
    val roles: List<Role>,
    @SerializedName("username")
    val username: String
)