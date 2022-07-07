package koperasi.nu.laundry_consumer.model

import koperasi.nu.laundry_consumer.payload.Role

data class Karyawan(
    val email: String,
    val id: Int,
    val nama: String,
    val password: String,
    val roles: List<Role>,
    val username: String
)