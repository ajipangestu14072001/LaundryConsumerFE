package koperasi.nu.laundry_consumer.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import koperasi.nu.laundry_consumer.MainActivity
import koperasi.nu.laundry_consumer.R
import koperasi.nu.laundry_consumer.databinding.ActivityLoginBinding
import koperasi.nu.laundry_consumer.helper.SharedPrefManager
import koperasi.nu.laundry_consumer.model.Login
import koperasi.nu.laundry_consumer.payload.BaseLogin
import koperasi.nu.laundry_consumer.services.APIClient
import koperasi.nu.laundry_consumer.services.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null
    var sharedPrefManager: SharedPrefManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setTheme(R.style.Theme_LAUNDRY_CONSUMER)
        setContentView(view)
        sharedPrefManager = SharedPrefManager(applicationContext)
        binding!!.rootLayout.cirLoginButton.setOnClickListener {
            if (validateUsername() && validatePassword()) {
                val login = Login(
                    binding!!.rootLayout.email.text.toString(),
                    binding!!.rootLayout.password.text.toString()
                )
                val apiInterface =
                    APIClient().getClient(applicationContext).create(APIInterface::class.java)

                val call: Call<BaseLogin?>? = apiInterface.getLoginUser(login)
                call?.enqueue(object : Callback<BaseLogin?> {
                    override fun onResponse(
                        call: Call<BaseLogin?>,
                        response: Response<BaseLogin?>
                    ) {
                        if (response.body()?.data?.get(0)?.token != null) {
                            val resource: BaseLogin? = response.body()
                            Toast.makeText(
                                applicationContext,
                                "Login successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            sharedPrefManager!!.saveSPBoolean(
                                SharedPrefManager.SP_SUDAH_LOGIN,
                                true
                            )
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            val sharedPref = getSharedPreferences("myKey", MODE_PRIVATE)
                            val editor = sharedPref.edit()
                            editor.putString("username", resource?.data?.get(0)?.username)
                            editor.putString("token", resource?.data?.get(0)?.token)
                            editor.putString("roles", resource?.data?.get(0)?.roles.toString())
                            editor.apply()
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Invalid credentials",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<BaseLogin?>, t: Throwable) {
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

//        binding!!.rootLayout.cirLoginButtonKaryawan.setOnClickListener {
//            if (validateUsername() && validatePassword()) {
//
//                val apiInterface =
//                    APIClient().getClient(applicationContext).create(APIInterface::class.java)
//                val login = Login(
//                    binding!!.rootLayout.email.text.toString(),
//                    binding!!.rootLayout.password.text.toString()
//                )
//                val call: Call<BaseLogin?>? = apiInterface.getLoginKaryawan(login)
//                call?.enqueue(object : Callback<BaseLogin?> {
//                    override fun onResponse(
//                        call: Call<BaseLogin?>,
//                        response: Response<BaseLogin?>
//                    ) {
//                        if (response.body()?.data?.get(0)?.token != null) {
//                            val resource: BaseLogin? = response.body()
//                            Toast.makeText(
//                                applicationContext,
//                                "Login successful",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            sharedPrefManager!!.saveSPBoolean(
//                                SharedPrefManager.SP_SUDAH_LOGIN,
//                                true
//                            )
//                            val intent = Intent(applicationContext, MainActivity::class.java)
//                            val sharedPref = getSharedPreferences("myKey", MODE_PRIVATE)
//                            val editor = sharedPref.edit()
//                            editor.putString("username", resource?.data?.get(0)?.username)
//                            editor.putString("token", resource?.data?.get(0)?.token)
//                            editor.apply()
//                            startActivity(intent)
//                            finish()
//                        } else {
//                            Toast.makeText(
//                                applicationContext,
//                                "Invalid credentials",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<BaseLogin?>, t: Throwable) {
//                        Toast.makeText(
//                            applicationContext,
//                            "Invalid credentials",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        call.cancel()
//                    }
//                })
//
//            }
//        }

        binding!!.rootLayout.toSignup.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
            finish()

        }

        binding!!.rootLayout.toSignupAdmin.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            intent.putExtra("admin", "admin")
            startActivity(intent)
            finish()
        }

    }

    private fun validateUsername(): Boolean {
        if (TextUtils.isEmpty(binding!!.rootLayout.email.text.toString())) {
            binding!!.rootLayout.email.error = "username cannot be empty"
            binding!!.rootLayout.email.requestFocus()
            return false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        if (TextUtils.isEmpty(binding!!.rootLayout.password.text.toString())) {
            binding!!.rootLayout.password.error = "password cannot be empty"
            binding!!.rootLayout.password.requestFocus()
            return false
        }
        return true
    }
}