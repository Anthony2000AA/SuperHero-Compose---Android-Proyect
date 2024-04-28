package pe.edu.upc.mark03.model.remote

import pe.edu.upc.mark03.model.data.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroService {
    @GET("{token}/search/{name}")
    fun searchHero(
        @Path("token") token:String="10157703717092094",
        @Path("name") name:String
    ): Call<ApiResponse>
}


// @Path permite definir una variable que se reemplazará en la URL de la solicitud.
// @Query permite definir un parámetro de consulta que se agregará a la URL de la solicitud.