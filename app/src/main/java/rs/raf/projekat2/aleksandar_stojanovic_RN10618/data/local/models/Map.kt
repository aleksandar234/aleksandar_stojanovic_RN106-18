package rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Map(
    var id : Long,
    var title : String,
    var note : String,
    var latitude : Double,
    var longditude : Double,
    var date : String
) : Parcelable