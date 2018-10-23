package com.waysnpaths.mygithub.ui.main

import android.os.AsyncTask
import com.waysnpaths.mygithub.data.networking.Networking
import java.io.IOException

class GetStringRequestAsyncTask(
        private val networking: Networking,
        private val onSuccess: (result: String) -> Unit,
        private val onError: (error: Throwable) -> Unit
) : AsyncTask<String, Any, String>() {

    override fun doInBackground(vararg params: String): String {
        return try {
            Thread.sleep(500)
            networking.get(params[0])
        } catch (e: IOException) {
            onError(e)
            "[]"
        }
    }

    override fun onPostExecute(result: String) {
        onSuccess(result)
    }
}