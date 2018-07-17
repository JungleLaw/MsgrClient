package cn.law.im.client.utils

import android.content.Context
import android.content.SharedPreferences
import cn.law.im.client.App

class PrefrencesUtils {

    companion object {
        private val instance: SharedPreferences = App.instance.applicationContext.getSharedPreferences("im", Context.MODE_PRIVATE)
        fun put(key: String, value: Any) {
            val editor = instance.edit()
            when (value) {
                is String -> {
                    editor.putString(key, value.toString())
                }
                is Int -> {
                    editor.putInt(key, value.toInt())
                }
                is Long -> {
                    editor.putLong(key, value.toLong())
                }
                is Float -> {
                    editor.putFloat(key, value.toFloat())
                }
                is Boolean -> {
                    editor.putBoolean(key, value)
                }
            }
            editor.apply()
        }

        fun getString(key: String): String? {
            return instance.getString(key, null)
        }

        fun getBoolean(key: String): Boolean {
            return instance.getBoolean(key, false)
        }

        fun getLong(key: String): Long {
            return instance.getLong(key, -1)
        }

        fun getInt(key: String): Int {
            return instance.getInt(key, -1)
        }

        fun getFloat(key: String): Float {
            return instance.getFloat(key, -1f)
        }
    }
}