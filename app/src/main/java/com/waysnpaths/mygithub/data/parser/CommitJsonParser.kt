package com.waysnpaths.mygithub.data.parser

import com.waysnpaths.mygithub.domain.JsonParser
import com.waysnpaths.mygithub.domain.model.Commit
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class CommitJsonParser : JsonParser<List<Commit>> {
    override fun parse(input: String): List<Commit> {
        val commitsList = mutableListOf<Commit>()

        val reposJsonArray = JSONArray(input)
        for (i in 0 until reposJsonArray.length()) {
            commitsList.add(parse(reposJsonArray.getJSONObject(i)))
        }

        return commitsList
    }

    @Throws(JSONException::class)
    private fun parse(repoJsonObject: JSONObject) : Commit {
        // todo there should be a whole structure of models...
        // todo Gson is lovely, JSONObjects aren't the prettiest
        return Commit(
                repoJsonObject.getJSONObject("commit")
                        .getJSONObject("author")
                        .getString("date") // todo const
        )
    }
}