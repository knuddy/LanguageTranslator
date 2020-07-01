package com.knuddj1languagetranslator.helpers.translator

import android.content.Context
import android.os.AsyncTask
import com.knuddj1languagetranslator.enum.DownloadStatus
import com.knuddj1languagetranslator.interfaces.IDataDownloadComplete
import java.lang.Exception
import java.net.URL

class TranslationAsyncTask(private val listener: IDataDownloadComplete, context: Context) :
    AsyncTask<String, Void, String>() {
    private var downloadStatus = DownloadStatus.NONE
    private var progressSpinner = ProgressSpinner(context)

    override fun onPreExecute() {
        progressSpinner.show()
    }

    override fun onPostExecute(result: String) {
        progressSpinner.dismiss()
        listener.onDownloadComplete(result, downloadStatus)
    }

    override fun doInBackground(vararg url: String?): String {
        var data = ""
        try {
            downloadStatus = DownloadStatus.OK
            data = downloadJSON(url[0])
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return data
    }

    private fun downloadJSON(urlPath: String?): String {
        return URL(urlPath).readText()
    }
}