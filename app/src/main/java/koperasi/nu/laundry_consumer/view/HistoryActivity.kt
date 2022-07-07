package koperasi.nu.laundry_consumer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import koperasi.nu.laundry_consumer.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private var binding : ActivityHistoryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        val view : View = binding!!.root
        setContentView(view)
    }
}