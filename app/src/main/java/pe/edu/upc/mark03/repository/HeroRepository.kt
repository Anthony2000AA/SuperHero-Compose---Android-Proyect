package pe.edu.upc.mark03.repository

import android.util.Log
import pe.edu.upc.mark03.model.data.ApiResponse
import pe.edu.upc.mark03.model.data.Hero
import pe.edu.upc.mark03.model.local.HeroDao
import pe.edu.upc.mark03.model.local.HeroEntity
import pe.edu.upc.mark03.model.remote.HeroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroRepository(
    private val heroService: HeroService,
    private val heroDao: HeroDao
) {


    fun insertHero(id: String) {
        heroDao.insert(HeroEntity(id = id))
    }

    fun deleteHero(id: String) {
        heroDao.delete(HeroEntity(id = id))
    }

    fun isFavorite( id: String):Boolean   {
        return ( heroDao.getHeroById(id) != null)
    }


    fun searchHero(name: String, callback: (List<Hero>) -> Unit) {
        val searchHero =
            heroService.searchHero(name = name)//lo pongo =name porque el nombre del parametro es igual al nombre de la variable, token no porque ya tiene un valor por defecto

        searchHero.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val heroes = response.body()?.heroes ?: emptyList()

                    for (hero in heroes) {
                        hero.isFavorite = isFavorite(hero.id)
                    }

                    callback(heroes)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                t.message?.let {
                    Log.d("HeroRepository", it)
                }
            }
        })
    }


    fun searchHeroById(id: String, callback: (Hero) -> Unit) {
        val searchHeroById = heroService.getHeroById(id = id)

        searchHeroById.enqueue(object : Callback<Hero> {
            override fun onResponse(call: Call<Hero>, response: Response<Hero>) {
                if (response.isSuccessful) {
                    val hero = response.body() as Hero
                    callback(hero)
                }
            }

            override fun onFailure(call: Call<Hero>, t: Throwable) {
                t.message?.let {
                    Log.d("HeroRepository", it)
                }
            }
        })
    }


}