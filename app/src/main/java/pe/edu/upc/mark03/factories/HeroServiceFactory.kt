package pe.edu.upc.mark03.factories

import pe.edu.upc.mark03.model.remote.HeroService

class HeroServiceFactory {//Las clases de tipo factory son clases que se encargan de crear instancias de otras clases/objetos

    companion object{
        fun getHeroService(): HeroService {

            val retrofit=RetrofitFactory.getRetrofit()
            return retrofit.create(HeroService::class.java)
        }
    }
}