package pe.edu.upc.mark03.model.data

import com.google.gson.annotations.SerializedName


data class ApiResponse(
    val response: String,

    @SerializedName("results-for")
    val resultsFor: String,

    @SerializedName("results")
    val heroes:List<Hero>
)

data class Hero(
    val name: String,
    val biography: Biography,
    val image: Image
)


data class Biography(
    @SerializedName("full-name")
    val fullName: String,
)


data class Image(
    val url: String
)