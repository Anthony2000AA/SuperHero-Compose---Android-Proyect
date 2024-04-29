package pe.edu.upc.mark03.factories

import pe.edu.upc.mark03.repository.HeroRepository

class HeroRepositoryFactory private constructor() {

    companion object {

        private var heroRepository: HeroRepository? = null
        fun getHeroRepository(): HeroRepository {

            if (heroRepository == null) {
                heroRepository = HeroRepository(
                                    heroService = HeroServiceFactory.getHeroService(),
                                    heroDao = HeroDaoFactory.getHeroDao())
            }
            return heroRepository as HeroRepository
        }
    }
}