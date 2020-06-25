package rs.raf.projekat2.aleksandar_stojanovic_RN10618.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.datasources.shared.MapDataSource
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.datasources.shared.MapIntentDataSource
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.db.MapsDatabase
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.repository.MapRepository
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.repository.MapRepositoryImp
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.viewmodels.MainViewModel

val mapModule = module {

    viewModel { MainViewModel(get()) }

    single<MapRepository> {MapRepositoryImp(get(),sharedDataSource = get(named("intent")))}

    single<MapDataSource>(named("intent")) { MapIntentDataSource() }

    single {get<MapsDatabase>().getMapDao() }

}