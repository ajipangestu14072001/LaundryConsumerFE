package koperasi.nu.laundry_consumer.view

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import koperasi.nu.laundry_consumer.R
import koperasi.nu.laundry_consumer.adapter.PaymentAdapter
import koperasi.nu.laundry_consumer.adapter.RecyclerViewAdapter
import koperasi.nu.laundry_consumer.callback.FetchRecyclerViewItems
import koperasi.nu.laundry_consumer.databinding.ActivityLaundryBinding
import koperasi.nu.laundry_consumer.model.*
import koperasi.nu.laundry_consumer.payload.BaseLayanan
import koperasi.nu.laundry_consumer.services.APIClient
import koperasi.nu.laundry_consumer.services.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LaundryActivity : AppCompatActivity(), FetchRecyclerViewItems {
    private var binding: ActivityLaundryBinding? = null
    private var dataArrayList: List<Layanan>? = null
    private var dataArrayList2: List<Transaksi>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private var bottomSheetDialog: BottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaundryBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setTheme(R.style.Theme_LAUNDRY_CONSUMER)
        setContentView(view)
        val bundle: Bundle = intent.extras!!
        val kategori = bundle.getString("kategori")
        val sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
        val cookie = sharedPreferences.getString("token", "")
         val apiInterface = APIClient().getClient(applicationContext).create(APIInterface::class.java)
        val call: Call<BaseLayanan?>? =
            apiInterface.getAllProductByKategori("Bearer $cookie", kategori)
        call?.enqueue(object : Callback<BaseLayanan?> {
            override fun onResponse(call: Call<BaseLayanan?>, response: Response<BaseLayanan?>) {
                if (response.isSuccessful) {
                    dataArrayList = response.body()!!.data
                    for (i in dataArrayList?.indices!!) {
                        recyclerViewAdapter =
                            RecyclerViewAdapter(dataArrayList!!, this@LaundryActivity)
                        val layoutManager = GridLayoutManager(applicationContext, 1)
                        recyclerViewAdapter!!.listener = this@LaundryActivity
                        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                return if (position == 0) 1 else 1
                            }
                        }
                        binding!!.rvProduct.layoutManager = layoutManager
                        binding!!.rvProduct.adapter = recyclerViewAdapter

                    }
                }
            }

            override fun onFailure(call: Call<BaseLayanan?>, t: Throwable) {
                call.cancel()
            }
        })

    }

    override fun onItemClicked(view: View, total: Double, layanan : Layanan) {
        if (bottomSheetDialog == null){
            bottomSheetDialog = BottomSheetDialog(this@LaundryActivity);
            val v = layoutInflater.inflate(R.layout.bottom_sheet_dialog,null)
            setRecyclerViewItem(v)
            bottomSheetDialog?.setContentView(v)
            val frameLayout = bottomSheetDialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            if(frameLayout != null){
                val bottomSheetBehavior = BottomSheetBehavior.from(frameLayout)
                bottomSheetBehavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
            val detail = v.findViewById<Button>(R.id.cirLoginButton)
            detail.setOnClickListener {
//                val intent = Intent(this@LaundryActivity, DetailHistoryActivity::class.java)
//                startActivity(intent)
//                finish()
                val sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
                val cookie = sharedPreferences.getString("token", "")
                val apiInterface =
                    APIClient().getClient(applicationContext).create(APIInterface::class.java)
                val register = TransaksiHistory(2, "true", "2022-07-05T04:40:16.000+00:00","2022-07-05T04:40:16.000+00:00", "2022-07-05T04:40:16.000+00:00",10000)
                val call: Call<History?>? = apiInterface.addTransaksi(register,"Bearer $cookie")
                call?.enqueue(object : Callback<History?> {
                    override fun onResponse(
                        call: Call<History?>,
                        response: Response<History?>
                    ) {
                        dataArrayList2 = response.body()!!.data
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
                    override fun onFailure(call: Call<History?>, t: Throwable) {
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
        bottomSheetDialog?.show()

    }

    private fun setRecyclerViewItem(v: View) {
        val bottomRecyclerView = v.findViewById<RecyclerView>(R.id.bottomRv)
        val artists: MutableList<Payment> = ArrayList()
        artists.add(
            Payment(
                R.drawable.tokopedia,
                "BCA"
            )
        )
        artists.add(
            Payment(
                R.drawable.tokopedia,
                "Mandiri"
            )
        )
        artists.add(
            Payment(
                R.drawable.tokopedia,
                "BRI"
            )
        )
        artists.add(
            Payment(
                R.drawable.tokopedia, "BNI")
        )
        artists.add(
            Payment(
                R.drawable.tokopedia,
                "Cash"
            )
        )
        val recyclerViewAdapter = PaymentAdapter(artists, this)
        bottomRecyclerView.adapter = recyclerViewAdapter
    }


}