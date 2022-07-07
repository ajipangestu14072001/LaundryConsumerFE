package koperasi.nu.laundry_consumer.payload

data class BaseLoginAdmin(
    val email: String,
    val nama: String,
    val password: String,
    val role: List<String>,
    val telepon: String,
    val username: String
)