package koperasi.nu.laundry_consumer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import koperasi.nu.laundry_consumer.adapter.SliderAdapter
import koperasi.nu.laundry_consumer.databinding.ActivityMainBinding
import koperasi.nu.laundry_consumer.model.SliderItems
import koperasi.nu.laundry_consumer.view.AdminDashboardActivity
import koperasi.nu.laundry_consumer.view.HistoryActivity
import koperasi.nu.laundry_consumer.view.LaundryActivity
import koperasi.nu.laundry_consumer.view.ProfileActivity
import java.util.ArrayList
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private val imageList: MutableList<SliderItems> = ArrayList()
    private var binding : ActivityMainBinding? = null
    private val handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view : View = binding!!.root
        setTheme(R.style.Theme_LAUNDRY_CONSUMER)
        setContentView(view)
        initData()
        val sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        val roles = sharedPreferences.getString("roles", "")
        Log.i("roles", roles.toString())
        if (roles.equals("[ROLE_ADMIN]")){
            intent = Intent(this, AdminDashboardActivity::class.java)
            startActivity(intent)
        }
        Toast.makeText(applicationContext, roles, Toast.LENGTH_SHORT).show()
        binding!!.txtHistory.text = "$username Periksa Riwayat Pesananmu"
        binding!!.imageProfile.setOnClickListener {
            val intent = Intent(applicationContext, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding!!.cuciBasah.setOnClickListener {
            intent = Intent(this, LaundryActivity::class.java)
            intent.putExtra("kategori", "Basah")
            startActivity(intent)
        }
        binding!!.cucisetrika.setOnClickListener {
            intent = Intent(this, LaundryActivity::class.java)
            intent.putExtra("kategori", "Setrika")
            startActivity(intent)
        }
        binding!!.cuciPremium.setOnClickListener {
            intent = Intent(this, LaundryActivity::class.java)
            intent.putExtra("kategori", "Premium")
            startActivity(intent)
        }
        binding!!.cuciKering.setOnClickListener {
            intent = Intent(this, LaundryActivity::class.java)
            intent.putExtra("kategori", "Kering")
            startActivity(intent)
        }
        binding!!.layoutHistory.setOnClickListener {
            intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initData(){
        for (i in 1..5) {
            imageList.add(SliderItems(R.drawable.intro2))
        }
        val imageAdapter = SliderAdapter(imageList, binding!!.viewPagerImageSlider)
        binding!!.viewPagerImageSlider.adapter = imageAdapter
        binding!!.viewPagerImageSlider.offscreenPageLimit = 3
        binding!!.viewPagerImageSlider.clipChildren = false
        binding!!.viewPagerImageSlider.clipToPadding = false
        binding!!.viewPagerImageSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(65))
        compositePageTransformer.addTransformer { page: View, position: Float ->
            val r = 1 - abs(position)
            page.scaleX = 0.90f + r * 0.25f
        }
        binding!!.viewPagerImageSlider.setPageTransformer(compositePageTransformer)
        binding!!.viewPagerImageSlider.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 5000)
            }
        })
    }
    private val runnable = Runnable {
        binding!!.viewPagerImageSlider.currentItem = binding!!.viewPagerImageSlider.currentItem + 1
    }
}