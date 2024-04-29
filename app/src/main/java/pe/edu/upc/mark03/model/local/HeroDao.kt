package pe.edu.upc.mark03.model.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface HeroDao {

    @Insert
    fun insert(heroEntity: HeroEntity)

    @Delete
    fun delete(heroEntity: HeroEntity)


    @Query("SELECT * FROM heroes")
    fun getAllHeroes(): List<HeroEntity>


    @Query("SELECT * FROM heroes WHERE id = :id")
    fun getHeroById(id: String): HeroEntity?
}