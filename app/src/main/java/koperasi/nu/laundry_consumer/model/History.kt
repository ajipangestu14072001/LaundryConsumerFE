package koperasi.nu.laundry_consumer.model

data class History(
    val `data`: List<Transaksi>,
    val message: String,
    val status: Int
)