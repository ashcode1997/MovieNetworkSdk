package com.example.networksdk

import org.json.JSONArray

interface JSONArrayListener {
    fun onResponse(res: JSONArray?)
    fun onFailure(e: Exception?)
}