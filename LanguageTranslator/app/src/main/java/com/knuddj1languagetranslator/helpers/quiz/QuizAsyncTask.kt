package com.knuddj1languagetranslator.helpers.quiz

import android.content.Context
import android.os.AsyncTask
import com.knuddj1languagetranslator.interfaces.IDataAvailable
import org.json.JSONArray
import java.io.InputStream
import java.lang.Exception

class QuizAsyncTask(private val listener: IDataAvailable, private var context: Context)
    :AsyncTask<String, Void, ArrayList<Quiz>>(){

    override fun onPostExecute(result: ArrayList<Quiz>) {
        super.onPostExecute(result)
        listener.onDataAvailable(result)
    }

    override fun doInBackground(vararg filepath: String): ArrayList<Quiz> {
        val quizset = ArrayList<Quiz>()
        try{
            val quizData = JSONArray(readJSONFromAsset(filepath[0]))

            for(quizItem: Int in 0 until quizData.length()){
                val quizObj = quizData.getJSONObject(quizItem)
                val quizName: String = quizObj.getString("quizTitle")
                val quizQuestions: JSONArray = quizObj.getJSONArray("questions")
                val questions = ArrayList<QuizQuestion>()

                for(quizQuestionItem: Int in 0 until quizQuestions.length()) {
                    val questionObj = quizQuestions.getJSONObject(quizQuestionItem)
                    val questionText: String = questionObj.getString("questionText")
                    val questionAnswerIndex: Int = questionObj.getInt("correctAnswerIndex")
                    val questionChoicesJson: JSONArray = questionObj.getJSONArray("questionChoices")
                    val questionChoices = ArrayList<String>()

                    for(questionChoiceItem: Int in 0 until questionChoicesJson.length()){
                        questionChoices.add(questionChoicesJson.getString(questionChoiceItem))
                    }

                    questions.add(QuizQuestion("https://cdn.shopify.com/s/files/1/0533/2089/files/placeholder-images-image_large.png?format=jpg&quality=90&v=1530129081", questionText, questionChoices, questionAnswerIndex))
                }

                quizset.add(Quiz(quizName, questions))
            }
        }catch (e: Exception){
            cancel(true)
            listener.onError(e)
        }
        return quizset
    }

    fun readJSONFromAsset(filepath: String): String {
        val  inputStream: InputStream = context.assets.open(filepath)
        return inputStream.bufferedReader().use{it.readText()}
    }
}