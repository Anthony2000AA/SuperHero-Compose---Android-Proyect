package pe.edu.upc.mark03.factories

import pe.edu.upc.mark03.network.ApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory private constructor() {//este metodo es como static en java, me permite acceder a el sin instanciar la clase
                        //Y crear una instancia de retrofit


    companion object{

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}