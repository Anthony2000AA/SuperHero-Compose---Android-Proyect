package pe.edu.upc.mark03.repository

import pe.edu.upc.mark03.factories.HeroServiceFactory
import pe.edu.upc.mark03.model.data.ApiResponse
import pe.edu.upc.mark03.model.data.Hero
import pe.edu.upc.mark03.model.remote.HeroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroRepository(
    val heroService: HeroService = HeroServiceFactory.getHeroService()
) {
    fun searchHero(callback:(List<Hero>)->Unit){
        val searchHero= heroService.searchHero()
        searchHero.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()?.heroes?: emptyList()
                    callback(apiResponse)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                t.message?.let {
                    println(it)
                }
            }
        })
    }



}