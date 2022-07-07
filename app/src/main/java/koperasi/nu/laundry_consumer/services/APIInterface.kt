package koperasi.nu.laundry_consumer.services

import koperasi.nu.laundry_consumer.model.*
import koperasi.nu.laundry_consumer.payload.BaseLayanan
import koperasi.nu.laundry_consumer.payload.BaseLogin
import koperasi.nu.laundry_consumer.payload.BaseLoginAdmin
import koperasi.nu.laundry_consumer.payload.BaseRegister
import retrofit2.Call
import retrofit2.http.*

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

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("auth/konsumen/signup")
    fun getRegisterKaryawan(@Body responseRegister: BaseLoginAdmin?): Call<BaseRegister?>?


    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("layanan/laundry")
    fun addLayanan(@Body baseLayanan: BaseAddLayanan?,@Header("Authorization") auth: String?): Call<BaseLayanan?>?

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("transaksi/laundry")
    fun addTransaksi(@Body transaksi: TransaksiHistory?,@Header("Authorization") auth: String?): Call<History?>?

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("layanan/laundry/kategori")
    fun getAllProductByKategori(
        @Header("Authorization") auth: String?,
        @Query("kategori") kategori: String?
    ): Call<BaseLayanan?>?

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("transaksi/laundry")
    fun getAllTransaksi(
        @Header("Authorization") auth: String?
    ): Call<History?>?
    @Headers("Accept: application/json", "Content-Type: application/json")
    @DELETE("transaksi/laundry/{idTransaksi}")
    fun deleteTransaksi(
        @Header("Authorization") auth: String?,
        @Path("idTransaksi") itemId: String?
    ): Call<ResponseCookie?>?
}