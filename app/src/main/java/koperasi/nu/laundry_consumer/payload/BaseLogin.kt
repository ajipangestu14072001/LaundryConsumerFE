package koperasi.nu.laundry_consumer.payload

import com.google.gson.annotations.SerializedName

data class BaseLogin(
    @SerializedName("data")
    val `data`: List<ResponseLogin>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)