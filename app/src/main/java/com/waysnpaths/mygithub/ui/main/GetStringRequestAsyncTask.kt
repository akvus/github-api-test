package com.waysnpaths.mygithub.ui.main

import android.os.AsyncTask
import com.waysnpaths.mygithub.data.networking.Networking
import java.io.IOException


// I'd be much more comfortable with RxJava instead, I forgetting how to use AsyncTasks :)
class GetStringRequestAsyncTask(
        private val networking: Networking,
        private val onSuccess: (result: String) -> Unit,
        private val onError: (error: Throwable) -> Unit // todo
) : AsyncTask<String, Any, String>() {

    override fun doInBackground(vararg params: String): String {
        try {
            return networking.get(params[0])
        } catch (e: IOException) {
            // todo handle, notify user etc
            // todo API rate limit exceeded etc
            return "[]"
        }
    }

    override fun onPostExecute(result: String) {
        onSuccess(result)
    }
}