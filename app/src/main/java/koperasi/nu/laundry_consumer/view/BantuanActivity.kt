package koperasi.nu.laundry_consumer.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import koperasi.nu.laundry_consumer.R
import koperasi.nu.laundry_consumer.databinding.ActivityBantuanBinding

class BantuanActivity : AppCompatActivity() {
    private var binding : ActivityBantuanBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBantuanBinding.inflate(layoutInflater)
        val view : View = binding!!.root
        setTheme(R.style.Theme_LAUNDRY_CONSUMER)
        setContentView(view)
        binding!!.emailBantuan.setOnClickListener {
            val subject = "Hubungi Furniture Rotan"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "dabelempat14072001@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            startActivity(intent)
        }

        binding!!.waBantuan.setOnClickListener{
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.type = "text/plain"
            val phoneNumberWithCountryCode = "+6281345602416"
            val message = "Halo saya ingin Menghubungi Furniture Rotan"
            val apiWa =  "https://api.whatsapp.com/send?phone=%s&text=%s"
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            String.format(
                                apiWa,
                                phoneNumberWithCountryCode,
                                message
                            )
                        )
                    )
                )
            } else {
                Toast.makeText(this, "Install WhatsApp Terlebih Dahulu", Toast.LENGTH_SHORT).show()
                val appPackageName = "com.whatsapp"
                try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
                } catch (anfe: ActivityNotFoundException) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
                }
            }
        }
        binding!!.phoneBantuan.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + "081345602416")
            startActivity(intent)
        }
    }
}