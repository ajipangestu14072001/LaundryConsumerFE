package koperasi.nu.laundry_consumer.services

import koperasi.nu.laundry_consumer.model.Login
import koperasi.nu.laundry_consumer.model.Register
import koperasi.nu.laundry_consumer.payload.BaseLogin
import koperasi.nu.laundry_consumer.payload.BaseRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {

    //Konsumen
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("auth/konsumen/signin")
    fun getLoginUser(@Body login: Login?): Call<BaseLogin?>?

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("auth/konsumen/signup")
    fun getRegisterUser(@Body responseRegister: Register?): Call<BaseRegister?>?

    //Karyawan
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("auth/karyawan/signin")
    fun getLoginKaryawan(@Body login: Login?): Call<BaseLogin?>?
}