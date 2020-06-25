package rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.datasources.shared

import android.content.Intent
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.Map

interface MapDataSource {

    fun setMapData(map: Map, intent: Intent)
}