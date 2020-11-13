package com.craft.furniture.data.local.pref

import android.content.Context
import android.content.SharedPreferences

object SharedPref {
    private const val DBNAME: String = "UjalaCygnus"
    private const val MODE: Int = Context.MODE_PRIVATE
    lateinit var mPreferences: SharedPreferences

    fun initSharedPreferences(context: Context){
        mPreferences = context.getSharedPreferences(DBNAME, MODE)

    }

    private inline fun SharedPreferences.editMe(operation :(SharedPreferences.Editor) ->Unit){
        val mEditor = edit()
        operation(mEditor)
        mEditor.apply()
    }
    fun setValue(key: String, value: Any?) {
        return when (value) {
            is String -> {
                mPreferences.editMe{
                    it.putString(key, value)
                }
            }

            is Boolean -> {
                mPreferences.editMe {
                    it.putBoolean(key,value)
                }
            }
            is Int -> {
                mPreferences.editMe {
                    it.putInt(key,value)
                }
            }
            is Float -> {
                mPreferences.editMe {
                    it.putFloat(key,value)
                }
            }
            else -> {
                throw UnsupportedOperationException("Not yet implemented.")
            }
        }
    }


    inline fun <reified T:Any> getValue(key: String): T? {
        val result:Any?
        when (T::class) {
            String::class -> {
                result=  mPreferences.getString(key,null)
            }
            Boolean::class -> {
                result= mPreferences.getBoolean(key,false)
            }
            Int::class -> {
                result= mPreferences.getInt(key, -1)
            }
            Float::class -> {
                result= mPreferences.getFloat(key,-0.1f)
            }
            else -> {
                throw java.lang.UnsupportedOperationException("Not yet implemented.")
            }
        }
        return result as T?

    }

    fun clearSharedPref(){
        mPreferences.editMe {
            it.clear()
        }

    }

}