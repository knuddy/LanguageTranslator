package com.knuddj1languagetranslator.interfaces

import com.knuddj1languagetranslator.enum.DownloadStatus

interface IDataDownloadComplete {
    fun onDownloadComplete(data: String, status: DownloadStatus)
}