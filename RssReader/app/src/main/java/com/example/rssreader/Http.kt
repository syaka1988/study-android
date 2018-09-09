package com.example.rssreader

import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

fun httpGet(url: String): InputStream? {

    val con = URL(url).openConnection() as HttpURLConnection

    con.apply {
        requestMethod = "GET"
        connectTimeout = 3000
        readTimeout = 5000
        instanceFollowRedirects = true
    }

    // エミュレータでインターネットに接続するためにはDNSの設定が必要だった。 https://android.benigumo.com/20170926/android-studio-emulator-config/
    con.connect()

    if (con.responseCode in 200..299) {
        return BufferedInputStream(con.inputStream)
    }

    return null
}