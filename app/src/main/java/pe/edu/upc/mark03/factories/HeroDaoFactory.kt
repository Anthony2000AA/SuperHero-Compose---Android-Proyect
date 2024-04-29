package pe.edu.upc.mark03.factories

import pe.edu.upc.mark03.MyApplication
import pe.edu.upc.mark03.model.local.HeroDao
import pe.edu.upc.mark03.persistence.AppDatabase

class HeroDaoFactory  private constructor(){

    companion object {
        fun getHeroDao(): HeroDao {
            return AppDatabase.getInstance(MyApplication.getContext()).heroDao()
        }
    }
}