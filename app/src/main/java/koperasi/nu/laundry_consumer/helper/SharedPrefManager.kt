package koperasi.nu.laundry_consumer.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {
    var sp: SharedPreferences = context.getSharedPreferences(SP_EDO_APP, Context.MODE_PRIVATE)
    var spEditor: SharedPreferences.Editor = sp.edit()
    fun saveSPBoolean(keySP: String?, value: Boolean) {
        spEditor.putBoolean(keySP, value)
        spEditor.commit()
    }

    val sPSudahLogin: Boolean
        get() = sp.getBoolean(SP_SUDAH_LOGIN, false)

    companion object {
        const val SP_EDO_APP = "Laundry"
        const val SP_SUDAH_LOGIN = "IsLogin"
    }

}