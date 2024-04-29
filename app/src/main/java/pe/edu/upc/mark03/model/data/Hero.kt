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
    val id: String,
    val name: String,
    val biography: Biography,
    val image: Image,

    @SerializedName("powerstats")
    val powerStats: PowerStats
)


data class Biography(
    @SerializedName("full-name")
    val fullName: String,

    @SerializedName("place-of-birth")
    val placeOfBirth: String,

    val publisher: String
)


data class Image(
    val url: String
)


data class PowerStats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
)