package pe.edu.upc.mark03.factories

import pe.edu.upc.mark03.MyApplication
import pe.edu.upc.mark03.model.local.HeroDao
import pe.edu.upc.mark03.persistence.AppDatabase

class HeroDaoFactory private constructor(){

    companion object {
        private var heroDao: HeroDao? = null

        fun getHeroDao(): HeroDao {
            if (heroDao == null) {
                heroDao = AppDatabaseFactory.getAppDatabase(MyApplication.getContext()).heroDao()

            }
            return heroDao as HeroDao
        }
    }
}