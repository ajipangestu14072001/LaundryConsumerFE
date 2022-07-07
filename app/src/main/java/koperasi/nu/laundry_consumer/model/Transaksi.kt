package koperasi.nu.laundry_consumer.model

data class Transaksi(
    val idTransaksi: String,
    val jumlahBarang: Int,
    val karyawan: Karyawan,
    val konsumen: Konsumen,
    val status: String,
    val tanggalAmbil: String,
    val tanggalKeluar: String,
    val tanggalMasuk: String,
    val totalHarga: Int
)