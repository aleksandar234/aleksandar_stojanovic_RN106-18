package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.contract

import android.content.Intent
import androidx.lifecycle.LiveData
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.Map
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.MapEntity
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.states.MapState

interface MainContract {

    interface ViewModel {


        val mapState: LiveData<MapState>

        fun insertMap(mapEntity: MapEntity)
        fun getAllMaps()
        fun deleteAllMaps()
        fun setMapData(map : Map , intent : Intent)
        fun searchLocation(search : String)
//        fun getMaps(sourt : Boolean = false)
        fun editMap(map : Map)

    }

}