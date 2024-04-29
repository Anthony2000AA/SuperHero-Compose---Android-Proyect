package pe.edu.upc.mark03.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes")//Se define la tabla de la base de datos local
data class HeroEntity(
    @PrimaryKey val id: String,
)
