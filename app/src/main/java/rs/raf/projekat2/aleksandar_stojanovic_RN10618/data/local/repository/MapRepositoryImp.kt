package rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.repository

import android.content.Intent
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.datasources.MapDao
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.datasources.shared.MapDataSource
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.Map
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.MapEntity

class MapRepositoryImp(private val mapDao: MapDao,private val sharedDataSource: MapDataSource) : MapRepository {

    private val noteMap = {
            entities: List<MapEntity> -> entities.map { Map(it.id, it.title, it.note, it.latitude,it.longditude,it.date) }
    }

    override fun insert(mapEntity: MapEntity): Completable {
        return mapDao.insertMap(mapEntity)
    }

    override fun getAll(): Observable<List<Map>> {
        return mapDao.getAll()
    }

    override fun deleteAll(): Completable {
        return mapDao.deleteAll()
    }

    override fun setMapData(map: Map, intent: Intent) {
        sharedDataSource.setMapData(map, intent)
    }

    override fun getBySearch(search: String): Observable<List<Map>> {
        return mapDao
            .getBySearch(search)
            .map {
                noteMap(it)
            }
    }

    override fun sortLocations(): Observable<List<Map>> {
        return mapDao
            .sortLocations()
            .map {
                noteMap(it)
            }
    }

    override fun edit(map: Map): Completable {
        return Completable.fromCallable {
            val oldNote = mapDao.getById(map.id)
            val newNote = oldNote.copy(
                title = map.title,
                note = map.note,
                latitude = map.latitude,
                longditude = map.longditude,
                date = map.date
            )
            mapDao.update(newNote)
        }
    }

}