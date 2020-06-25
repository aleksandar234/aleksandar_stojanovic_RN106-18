package rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.datasources.MapDao
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.MapEntity

@Database(
    entities = [MapEntity::class],
    version = 2,
    exportSchema =false)

abstract class MapsDatabase : RoomDatabase() {
    abstract fun getMapDao() : MapDao
}