package koperasi.nu.laundry_consumer.helper


import android.text.format.DateFormat
import java.text.DecimalFormat;
import java.util.*


class FunctionHelper {
    fun rupiahFormat(price: Int): String {
        val formatter = DecimalFormat("#,###")
        return "Rp " + formatter.format(price).replace(",", ".")
    }

    fun getToday(): String {
        val date: Date = Calendar.getInstance().time
        return DateFormat.format("d MMMM yyyy", date).toString()
    }
}