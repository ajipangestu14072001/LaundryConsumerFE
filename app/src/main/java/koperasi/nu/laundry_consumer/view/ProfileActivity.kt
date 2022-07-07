package koperasi.nu.laundry_consumer.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import koperasi.nu.laundry_consumer.R
import koperasi.nu.laundry_consumer.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private var binding : ActivityProfileBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view : View = binding!!.root
        setTheme(R.style.Theme_LAUNDRY_CONSUMER)
        setContentView(view)

        binding!!.card1.setOnClickListener {
            val intent = Intent(applicationContext, AccountActivity::class.java)
            startActivity(intent)
        }

        binding!!.card2.setOnClickListener {
            val intent = Intent(applicationContext, BantuanActivity::class.java)
            startActivity(intent)
        }

        binding!!.card3.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            val sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
            sharedPreferences.edit().remove("token").apply();
            startActivity(intent)

        }
    }
}