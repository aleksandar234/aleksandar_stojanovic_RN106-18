package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.states

import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.Map
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.MapEntity

sealed class MapState {

    object Loading : MapState()
    data class Success(val maps: List<Map>):MapState()
    data class Error(val message : String):MapState()

}