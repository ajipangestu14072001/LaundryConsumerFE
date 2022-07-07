package koperasi.nu.laundry_consumer.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import koperasi.nu.laundry_consumer.R
import koperasi.nu.laundry_consumer.adapter.HistoryAdapter
import koperasi.nu.laundry_consumer.databinding.ActivityHistoryBinding
import koperasi.nu.laundry_consumer.model.History
import koperasi.nu.laundry_consumer.model.ResponseCookie
import koperasi.nu.laundry_consumer.model.Transaksi
import koperasi.nu.laundry_consumer.services.APIClient
import koperasi.nu.laundry_consumer.services.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HistoryActivity : AppCompatActivity(), HistoryAdapter.HistoryAdapterCallback {
    private var binding : ActivityHistoryBinding? = null
    private var dataArrayList: List<Transaksi>? = null
    private var historyActivity: HistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_LAUNDRY_CONSUMER)
        val view : View = binding!!.root
        setContentView(view)
        val sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
        val cookie = sharedPreferences.getString("token", "")
        val apiInterface = APIClient().getClient(applicationContext).create(APIInterface::class.java)
        val call: Call<History?>? =
            apiInterface.getAllTransaksi("Bearer $cookie")
        call?.enqueue(object : Callback<History?> {
            override fun onResponse(call: Call<History?>, response: Response<History?>) {
                if (response.isSuccessful) {
                    dataArrayList = response.body()!!.data
                    for (i in dataArrayList?.indices!!) {
                        historyActivity =
                            HistoryAdapter(this@HistoryActivity,
                                dataArrayList!! as MutableList<Transaksi>, this@HistoryActivity)
                        val layoutManager = GridLayoutManager(applicationContext, 1)
                        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                return if (position == 0) 1 else 1
                            }
                        }
                        binding!!.rvHistory.layoutManager = layoutManager
                        binding!!.rvHistory.adapter = historyActivity

                    }
                }
            }

            override fun onFailure(call: Call<History?>, t: Throwable) {
                call.cancel()
            }
        })
    }

    override fun onDelete(modelLaundry: Transaksi?) {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        val sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
        val cookie = sharedPreferences.getString("token", "")
        alertDialogBuilder.setMessage("Hapus riwayat ini?")
        alertDialogBuilder.setPositiveButton("Ya, Hapus") { _, _ ->
            val apiInterface =
                APIClient().getClient(applicationContext).create(APIInterface::class.java)
            apiInterface.deleteTransaksi("Bearer $cookie", modelLaundry?.idTransaksi)!!
                .enqueue(object : Callback<ResponseCookie?> {
                    override fun onResponse(
                        call: Call<ResponseCookie?>,
                        response: Response<ResponseCookie?>
                    ) {
                        if (response.body() != null) {
                            Toast.makeText(
                                this@HistoryActivity,
                                response.body()!!.message,
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }

                    override fun onFailure(call: Call<ResponseCookie?>, t: Throwable) {
                        Log.i("Ini Log", t.message!!)
                    }
                })
            Toast.makeText(
                this@HistoryActivity,
                "Data yang dipilih sudah dihapus",
                Toast.LENGTH_SHORT
            ).show()
        }

        alertDialogBuilder.setNegativeButton("Batal") { dialogInterface, i -> dialogInterface.cancel() }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}