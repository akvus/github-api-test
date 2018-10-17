package com.waysnpaths.mygithub.data.parser

import com.waysnpaths.mygithub.domain.JsonParser
import com.waysnpaths.mygithub.domain.model.Repository
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ReposJsonParser : JsonParser<List<Repository>> {
    @Throws(JSONException::class)
    override fun parse(input: String): List<Repository> {
        val reposList = mutableListOf<Repository>()

        val reposJsonArray = JSONArray(input)
        for (i in 0 until reposJsonArray.length()) {
            reposList.add(parse(reposJsonArray.getJSONObject(i)))
        }

        return reposList
    }

    @Throws(JSONException::class)
    private fun parse(repoJsonObject: JSONObject) : Repository {
        return Repository(
                repoJsonObject.getLong("id"), // keys should be const
                repoJsonObject.getString("name"),
                repoJsonObject.getString("description")
        )
    }
}