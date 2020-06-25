package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.viewmodels

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.Map
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.MapEntity
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.repository.MapRepository
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.contract.MainContract
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.states.MapState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainViewModel(private val mapRepository: MapRepository) : ViewModel() , MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val mapState: MutableLiveData<MapState> = MutableLiveData()
    private val publishSubject: PublishSubject<String> = PublishSubject.create()



    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .switchMap {
                mapRepository
                    .getBySearch(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    mapState.value = MapState.Success(it)
                },
                {
                    mapState.value = MapState.Error("Error happened while fetching data from database")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertMap(mapEntity: MapEntity) {
        val subscription = mapRepository
            .insert(mapEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("COMPLETE")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllMaps() {
        val subscription = mapRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mapState.value = MapState.Success(it)
                },
                {
                    mapState.value = MapState.Error("Error happened while fetching data from database")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteAllMaps() {
        val subscription = mapRepository
            .deleteAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("DELETED")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun setMapData(map: Map, intent: Intent) {
        mapRepository.setMapData(map, intent)

    }

    override fun searchLocation(search: String) {
        publishSubject.onNext(search)
    }

//    override fun getMaps(sourt: Boolean) {
//        if (sourt) {
//            val subscription = mapRepository
//                .sortLocations()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    {
//                        mapState.value = MapState.Success(it)
//                    },
//                    {
//                        mapState.value = MapState.Error("Error happened while fetching data from database")
//                        Timber.e(it)
//                    }
//                )
//            subscriptions.add(subscription)
//
//        } else {
//            getAllMaps()
//        }
//    }

    override fun editMap(map: Map) {
        val subscription = mapRepository
            .edit(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("UPDATED")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        subscriptions.clear()
        super.onCleared()
    }

}