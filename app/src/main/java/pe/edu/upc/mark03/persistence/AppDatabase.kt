package pe.edu.upc.mark03.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.upc.mark03.model.local.HeroDao
import pe.edu.upc.mark03.model.local.HeroEntity


@Database(entities = [HeroEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {//abstract para que no se pueda instanciar
    abstract fun heroDao(): HeroDao


}