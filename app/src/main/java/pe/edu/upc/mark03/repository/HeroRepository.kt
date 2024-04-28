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
    fun searchHero(name:String,callback:(List<Hero>)->Unit){
        val searchHero= heroService.searchHero(name=name)//lo pongo =name porque el nombre del parametro es igual al nombre de la variable, token no porque ya tiene un valor por defecto
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