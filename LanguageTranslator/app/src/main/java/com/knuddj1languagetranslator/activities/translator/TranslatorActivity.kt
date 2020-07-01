package com.knuddj1languagetranslator.activities.translator

import android.net.Uri
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.view.View
import com.knuddj1languagetranslator.R
import com.knuddj1languagetranslator.activities.bases.MenuBaseActivity
import com.knuddj1languagetranslator.enum.DownloadStatus
import com.knuddj1languagetranslator.helpers.translator.TranslationAsyncTask
import com.knuddj1languagetranslator.interfaces.IDataDownloadComplete
import kotlinx.android.synthetic.main.activity_translator.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*


class TranslatorActivity : MenuBaseActivity(), IDataDownloadComplete {

    private lateinit var fromLangName: String
    private lateinit var fromLangCC: String
    private lateinit var toLangName: String
    private lateinit var toLangCC: String
    private var translatedText = ""
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translator)
        displayToolbar(true)

        fromLangName = getString(R.string.languageName)
        fromLangCC = getString(R.string.countryCode)
        toLangName = intent.getStringExtra("toLangName") as String
        toLangCC = intent.getStringExtra("toLangCC") as String

        txvFromLangName.text = fromLangName
        txvFromLangCC.text = fromLangCC.toUpperCase()
        txvToLangName.text = toLangName
        txvToLangCC.text = toLangCC.toUpperCase()

        btnTranslate.setOnClickListener(TranslateButtonOnClickListener())
        btnTextToSpeech.setOnClickListener(TTSBtnOnClickListener())
        tts = TextToSpeech(applicationContext, OnInitListener {setupTTS(toLangCC)})

    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if (status == DownloadStatus.OK) {
            val re = Regex("[^A-Za-z0-9 ]")
            translatedText = JSONArray(JSONObject(data).getString("text"))[0].toString()
            translatedText = re.replace(translatedText, "")
            txvTranslated.text = translatedText
        }
    }

    inner class TranslateButtonOnClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            val toTranslate = edtToTranslate.text.toString()
            if (toTranslate.isNotEmpty()){
                val uri = createTranslationURI(
                    getString(R.string.base_translation_url), getString(R.string.api_key),
                    toTranslate, "$fromLangCC-$toLangCC")
                TranslationAsyncTask(this@TranslatorActivity, this@TranslatorActivity).execute(uri)
            }
        }
    }

    inner class TTSBtnOnClickListener : View.OnClickListener{
        override fun onClick(v: View?) {
            if (translatedText.isNotEmpty()){
                tts.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    private fun setupTTS(cc: String){
        var enabled = false

        val allLocales = Locale.getAvailableLocales()
        var localeIndex = -1
        for (index: Int in allLocales.indices) {
            if(allLocales[index].toString() == cc){
                localeIndex = index
                break
            }
        }

        if (localeIndex != -1){
            val locale: Locale = allLocales[localeIndex]
            if (cc in tts.availableLanguages.toString()){
                tts.language = locale
                enabled = true
            }
        }
       btnTextToSpeech.isEnabled = enabled
    }

    private fun createTranslationURI(
        baseURL: String, apiKey: String,
        text: String, lang: String
    ): String {
        return Uri.parse(baseURL)
            .buildUpon()
            .appendQueryParameter("key", apiKey)
            .appendQueryParameter("text", text)
            .appendQueryParameter("lang", lang)
            .build().toString()
    }

}
