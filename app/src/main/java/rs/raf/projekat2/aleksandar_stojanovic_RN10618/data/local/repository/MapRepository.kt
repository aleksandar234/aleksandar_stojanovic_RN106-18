package rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.repository

import android.content.Intent
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.Map
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.MapEntity

interface MapRepository {

    fun insert(mapEntity: MapEntity): Completable

    fun getAll(): Observable<List<Map>>

    fun deleteAll(): Completable

    fun setMapData(map : Map , intent: Intent)

    fun getBySearch(search : String) : Observable<List<Map>>

    fun sortLocations() : Observable<List<Map>>

    fun edit(map : Map) : Completable

}