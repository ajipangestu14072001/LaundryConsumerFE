package koperasi.nu.laundry_consumer.model

import com.google.gson.annotations.SerializedName

data class ResponseCookie(

    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val `data`: String?

)