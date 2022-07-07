package koperasi.nu.laundry_consumer.payload

import koperasi.nu.laundry_consumer.model.Layanan

    data class BaseLayanan(
    val `data`: List<Layanan>,
    val message: String,
    val status: Int
)