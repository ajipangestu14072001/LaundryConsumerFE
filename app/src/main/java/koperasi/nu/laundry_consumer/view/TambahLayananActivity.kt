package koperasi.nu.laundry_consumer.view

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import koperasi.nu.laundry_consumer.R
import koperasi.nu.laundry_consumer.databinding.ActivityTambahLayananBinding
import koperasi.nu.laundry_consumer.model.BaseAddLayanan
import koperasi.nu.laundry_consumer.model.Layanan
import koperasi.nu.laundry_consumer.payload.BaseLayanan
import koperasi.nu.laundry_consumer.services.APIClient
import koperasi.nu.laundry_consumer.services.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahLayananActivity : AppCompatActivity() {
    private var binding : ActivityTambahLayananBinding? = null
    private val golongandarah = arrayOf("" ,"DEEPCLEAN", "FASTCLEAN", "RECOLOR", "REGULER")
    private var dataArrayList: List<Layanan>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahLayananBinding.inflate(layoutInflater)
        val view = binding!!.root
        setTheme(R.style.Theme_LAUNDRY_CONSUMER)
        setContentView(view)
        val adapter =
            ArrayAdapter(this@TambahLayananActivity, android.R.layout.simple_list_item_1, golongandarah)
        binding!!.jenis.adapter = adapter

        binding!!.btnLayanan.setOnClickListener {
            val sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
            val cookie = sharedPreferences.getString("token", "")
            val apiInterface =
                APIClient().getClient(applicationContext).create(APIInterface::class.java)
            val register = BaseAddLayanan(binding!!.hargaLayanan.text.toString().toInt(), binding!!.jenis.selectedItem.toString(), binding!!.namaLayanan.text.toString())
            val call: Call<BaseLayanan?>? = apiInterface.addLayanan(register,"Bearer $cookie")
            call?.enqueue(object : Callback<BaseLayanan?> {
                override fun onResponse(
                    call: Call<BaseLayanan?>,
                    response: Response<BaseLayanan?>
                ) {
                    dataArrayList = response.body()!!.data
                    if (dataArrayList != null) {
                        Toast.makeText(
                            applicationContext,
                            response.body()?.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Terjadi Kesalahan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                override fun onFailure(call: Call<BaseLayanan?>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Invalid credentials",
                        Toast.LENGTH_SHORT
                    ).show()
                    call.cancel()
                }
            })
        }
    }
}