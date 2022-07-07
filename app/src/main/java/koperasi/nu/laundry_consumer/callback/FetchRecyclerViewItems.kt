package koperasi.nu.laundry_consumer.callback

import android.view.View
import koperasi.nu.laundry_consumer.model.Layanan

interface FetchRecyclerViewItems {
    fun onItemClicked(view: View, total: Double, layanan : Layanan)
}