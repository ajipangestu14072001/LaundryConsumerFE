package koperasi.nu.laundry_consumer.payload

import com.google.gson.annotations.SerializedName

data class BaseRegister(
    @SerializedName("data")
    val `data`: List<ResponseRegister>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

