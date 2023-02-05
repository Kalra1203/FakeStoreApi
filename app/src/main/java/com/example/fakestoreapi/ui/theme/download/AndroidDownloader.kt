package com.example.fakestoreapi.ui.theme.download

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri


class AndroidDownloader(
    private val context: Context): Downloader {

    private val downloadManager= context.getSystemService(DownloadManager::class.java)
    override fun downloadFile(url: String): Long {

        val request= DownloadManager.Request(url.toUri())
            .setMimeType("image/jpg")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE and DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "FakeImages" )
        return downloadManager.enqueue(request)

    }
}
