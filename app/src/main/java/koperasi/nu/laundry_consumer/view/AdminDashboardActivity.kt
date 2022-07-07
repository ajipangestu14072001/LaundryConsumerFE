package koperasi.nu.laundry_consumer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import koperasi.nu.laundry_consumer.R
import koperasi.nu.laundry_consumer.databinding.ActivityAdminDashboardBinding

class AdminDashboardActivity : AppCompatActivity() {
    private var binding : ActivityAdminDashboardBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        val view : View = binding!!.root
        setTheme(R.style.Theme_LAUNDRY_CONSUMER)
        setContentView(view)
    }
}