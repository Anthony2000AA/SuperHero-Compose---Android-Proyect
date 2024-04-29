package pe.edu.upc.mark03.factories

import pe.edu.upc.mark03.model.remote.HeroService

class HeroServiceFactory private constructor(){//Las clases de tipo factory son clases que se encargan de crear instancias de otras clases/objetos

    companion object {

        private var heroService: HeroService? = null
        fun getHeroService(): HeroService {
            if (heroService == null) {
                heroService = RetrofitFactory.getRetrofit().create(HeroService::class.java)
            }

            return heroService as HeroService
        }
    }
}