package com.example.networksdk

import com.google.gson.Gson
import java.nio.charset.StandardCharsets

internal object HttpUtils {
    fun asString(data: ByteArray?): String =
        if (data == null || data.isEmpty()) "" else String(data, StandardCharsets.UTF_8)
}


inline fun <reified T : Any> deserializeFromJson(json: String): T? {
    return try {
        Gson().fromJson(json, T::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}