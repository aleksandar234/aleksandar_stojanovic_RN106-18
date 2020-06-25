package rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "maps")
data class MapEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var title: String,
    var note: String,
    var latitude: Double,
    var longditude: Double,
    var date : String
)