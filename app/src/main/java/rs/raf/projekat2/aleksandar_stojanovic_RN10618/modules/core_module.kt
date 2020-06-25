package rs.raf.projekat2.aleksandar_stojanovic_RN10618.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.db.MapsDatabase
import java.util.*

val coreModule = module {

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(androidApplication().packageName, Context.MODE_PRIVATE)
    }

    single { Room.databaseBuilder(androidContext(), MapsDatabase::class.java, "MapDb")
        .fallbackToDestructiveMigration()
        .build() }

    single { createMoshi() }

}

fun createMoshi(): Moshi {
    return Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build()
}
