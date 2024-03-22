package com.example.globoplay.core.data.remote.model.response


import com.example.globoplay.core.data.remote.model.Cast
import com.example.globoplay.core.data.remote.model.Crew
import com.google.gson.annotations.SerializedName

data class MovieCreditsResponse(
    @SerializedName("cast")
    val cast: List<Cast?>?,
    @SerializedName("crew")
    val crew: List<Crew?>?,
    @SerializedName("id")
    val id: Int?
)