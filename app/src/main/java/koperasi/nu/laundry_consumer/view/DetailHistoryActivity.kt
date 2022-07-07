package koperasi.nu.laundry_consumer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import koperasi.nu.laundry_consumer.databinding.ActivityDetailHistoryBinding

class DetailHistoryActivity : AppCompatActivity() {
    private var binding : ActivityDetailHistoryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)
    }
}