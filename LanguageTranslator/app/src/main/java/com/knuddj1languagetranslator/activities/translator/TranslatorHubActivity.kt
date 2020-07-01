package com.knuddj1languagetranslator.activities.translator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.knuddj1languagetranslator.R
import com.knuddj1languagetranslator.activities.bases.MenuBaseActivity
import com.knuddj1languagetranslator.enum.DownloadStatus
import com.knuddj1languagetranslator.helpers.translator.TranslationAsyncTask
import com.knuddj1languagetranslator.interfaces.IDataDownloadComplete
import kotlinx.android.synthetic.main.activity_translator_hub.*
import org.json.JSONObject


class TranslatorHubActivity : MenuBaseActivity(), IDataDownloadComplete {

    private var availableLanguages = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translator_hub)
        displayToolbar(true)

        val task = TranslationAsyncTask(this, this@TranslatorHubActivity)
        task.execute(createGetAvailableLangURI(
            getString(R.string.base_get_available_url),
            getString(R.string.api_key)
        ))

        btnBeginTranslate.setOnClickListener(BeginTranslateBtnOnClickListener())
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if (status == DownloadStatus.OK) {
            val jsonData = JSONObject(data).getJSONObject("langs")
            val languageNames = ArrayList<String>()
            for(key in jsonData.keys()) {
                availableLanguages[jsonData[key] as String] = key
                languageNames.add(jsonData[key] as String)
            }

            val adapter = ArrayAdapter<String>(
                applicationContext, R.layout.spinner_item,
                languageNames
            )
            spinnerTo.adapter = adapter
        }
    }

    private fun createGetAvailableLangURI(baseURL: String, apiKey: String): String {
        return Uri.parse(baseURL)
            .buildUpon()
            .appendQueryParameter("key", apiKey)
            .appendQueryParameter("ui", "en")
            .build().toString()
    }

    inner class BeginTranslateBtnOnClickListener: View.OnClickListener{
        override fun onClick(v: View?) {
            val toLang: String = spinnerTo.selectedItem.toString()
            val toLangCC: String = availableLanguages[toLang].toString()


            val intent = Intent(this@TranslatorHubActivity, TranslatorActivity::class.java)
            intent.putExtra("toLangName", toLang)
            intent.putExtra("toLangCC", toLangCC)
            startActivity(intent)
        }
    }
}
