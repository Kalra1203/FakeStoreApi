package com.example.fakestoreapi.ui.theme.download

interface Downloader {
    fun downloadFile(url: String): Long
}