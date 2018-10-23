package com.waysnpaths.mygithub.ui.main

import android.os.AsyncTask
import java.io.IOException

class GetStringRequestAsyncTask<T>(
        private val onBackground: () -> T,
        private val onSuccess: (result: T?) -> Unit,
        private val onError: (error: Throwable) -> Unit
) : AsyncTask<String, Any, T>() {

    override fun doInBackground(vararg params: String): T? {
        return try {
            Thread.sleep(500)
            onBackground()
        } catch (e: IOException) {
            onError(e)
            null
        }
    }

    override fun onPostExecute(result: T?) {
        onSuccess(result)
    }
}