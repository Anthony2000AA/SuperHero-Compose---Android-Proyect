package pe.edu.upc.mark03.model.remote

import pe.edu.upc.mark03.model.data.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface HeroService {
    @GET("10157703717092094/search/spider")
    fun searchHero(): Call<ApiResponse>
}