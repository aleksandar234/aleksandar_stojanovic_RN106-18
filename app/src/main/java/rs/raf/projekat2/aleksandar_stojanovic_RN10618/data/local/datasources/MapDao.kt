package rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.datasources

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.Map
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.MapEntity

@Dao
abstract class MapDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMap(mapEntity: MapEntity): Completable

    @Query("SELECT * FROM maps")
    abstract fun getAll(): Observable<List<Map>>

    @Query("DELETE FROM maps")
    abstract fun deleteAll(): Completable

    @Query("SELECT * FROM maps WHERE title LIKE :search || '%' or note LIKE :search || '%'")
    abstract fun getBySearch(search: String): Observable<List<MapEntity>>

    @Query("SELECT * FROM maps WHERE title == :sort")
    abstract fun sortLocations(sort: Boolean = false): Observable<List<MapEntity>>

    @Query("SELECT * FROM maps WHERE id == :id")
    abstract fun getById(id: Long): MapEntity

    @Update
    abstract fun update(noteEntity: MapEntity)
}