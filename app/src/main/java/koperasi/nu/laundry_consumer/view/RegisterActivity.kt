package koperasi.nu.laundry_consumer.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import koperasi.nu.laundry_consumer.R
import koperasi.nu.laundry_consumer.databinding.ActivityRegisterBinding
import koperasi.nu.laundry_consumer.model.Register
import koperasi.nu.laundry_consumer.payload.BaseRegister
import koperasi.nu.laundry_consumer.payload.ResponseRegister
import koperasi.nu.laundry_consumer.services.APIClient
import koperasi.nu.laundry_consumer.services.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private var binding : ActivityRegisterBinding? = null
    private var dataArrayList: List<ResponseRegister>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view : View = binding!!.root
        setTheme(R.style.Theme_LAUNDRY_CONSUMER)
        setContentView(view)
        val apiInterface =
            APIClient().getClient(applicationContext).create(APIInterface::class.java)
        binding!!.rootLayout.toLogin.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding!!.rootLayout.cirLoginButton.setOnClickListener {
            val register = Register(binding!!.rootLayout.email.text.toString(),binding!!.rootLayout.nama.text.toString(), binding!!.rootLayout.username.text.toString(), binding!!.rootLayout.telp.text.toString(), binding!!.rootLayout.password.text.toString())
            val call: Call<BaseRegister?>? = apiInterface.getRegisterUser(register)
            call?.enqueue(object : Callback<BaseRegister?> {
                override fun onResponse(
                    call: Call<BaseRegister?>,
                    response: Response<BaseRegister?>
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
                override fun onFailure(call: Call<BaseRegister?>, t: Throwable) {
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