package koperasi.nu.laundry_consumer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import koperasi.nu.laundry_consumer.R
import koperasi.nu.laundry_consumer.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {
    private var binding : ActivityAccountBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        val view = binding!!.root
        setTheme(R.style.Theme_LAUNDRY_CONSUMER)
        setContentView(view)
        setSupportActionBar(binding!!.toolbar)
        val sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        binding!!.nameAccount.text = username
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}