package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.layout_item_map.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.R
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.Map
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.MapEntity
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.contract.MainContract
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.states.MapState
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.viewmodels.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class FragmentMapa : Fragment(R.layout.fragment_mapa), GoogleMap.OnMarkerClickListener {

    private val mainViewModel : MainContract.ViewModel by sharedViewModel<MainViewModel>()


    lateinit var mapFragment : SupportMapFragment
    lateinit var googleMap : GoogleMap



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {

            googleMap = it
            googleMap.getUiSettings().setZoomControlsEnabled(true)
            googleMap.setOnMarkerClickListener(this)


        })


        //initInsert()
        initObservers()
    }

    private fun initInsert() {
        val sdf = SimpleDateFormat("dd/MM/yyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        mainViewModel.insertMap(
            MapEntity(
                0,
                "Skola",
                "Osnovna",
                44.7521,
                20.4184,
                currentDate
            )
        )
        mainViewModel.insertMap(
            MapEntity(
                1,
                "Teretana",
                "Brdo",
                44.8049,
                20.4635,
                currentDate
            )
        )
        mainViewModel.insertMap(
            MapEntity(
                2,
                "Sladoled",
                "Dobar",
                44.7502,
                20.4028,
                currentDate
            )
        )
    }

    private fun initObservers() {
        mainViewModel.mapState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        mainViewModel.getAllMaps()
    }

    private fun renderState(state : MapState){
        when(state){
            is MapState.Success -> {
                showLoadingState(false)
                iscrtavanje(state.maps)
            }
        }
    }

    private fun iscrtavanje(maps: List<Map>) {
        var num = 1
        for(map in maps) {
            val location = LatLng(map.latitude,map.longditude)
            googleMap.addMarker(MarkerOptions().position(location).title(map.title))
            if(num == 1) googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,14f))
            num++
        }
    }

    private fun showLoadingState(loading : Boolean){

    }

    override fun onMarkerClick(p0: Marker?) = false

}