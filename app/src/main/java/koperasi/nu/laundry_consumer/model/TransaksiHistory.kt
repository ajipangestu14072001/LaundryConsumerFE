package koperasi.nu.laundry_consumer.model

data class TransaksiHistory(
    val jumlahBarang: Int,
    val status: String,
    val tanggalAmbil: String,
    val tanggalKeluar: String,
    val tanggalMasuk: String,
    val totalHarga: Int
)