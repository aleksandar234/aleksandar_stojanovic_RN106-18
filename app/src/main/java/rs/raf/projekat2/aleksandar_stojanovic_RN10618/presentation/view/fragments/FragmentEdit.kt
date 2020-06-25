package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.fragments

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.layout_item_map.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.R
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.MapEntity
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.contract.MainContract
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.viewmodels.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class FragmentEdit : Fragment(R.layout.fragment_main),OnMapReadyCallback ,GoogleMap.OnMarkerClickListener{

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation : Location
    private val mainViewModel : MainContract.ViewModel by viewModel<MainViewModel>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.requireActivity())
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.getUiSettings().setZoomControlsEnabled(true)
        map.setOnMarkerClickListener(this)
        setUpMap()

    }

    private fun setUpMap(){

        if(ActivityCompat.checkSelfPermission(this.requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.requireActivity(),arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),LOCATION_PERMISSION_REQUEST_CODE)
            return
        }


        map.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this.requireActivity()) { location ->
            if(location != null) {
                lastLocation = location
                val currentLatLng = LatLng(lastLocation.latitude, lastLocation.longitude)
                lastLocation.latitude = currentLatLng.latitude
                lastLocation.longitude = currentLatLng.longitude
                placeMarkerOnMap(currentLatLng)
                map.addMarker(MarkerOptions().position(currentLatLng).title("My Location"))
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }

        initObserver()

    }

    private fun initObserver() {
        btnSacuvaj.setOnClickListener {
            var naslov = Tftitle.text.toString()
            var komentar = Tfnote.text.toString()
            val sdf = SimpleDateFormat("dd/MM/yyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            map.clear()
            setUpMap()
            mainViewModel.insertMap(
                MapEntity(
                    0,
                    naslov,
                    komentar,
                    lastLocation.latitude,
                    lastLocation.longitude,
                    currentDate
                )
            )
            mainViewModel.getAllMaps()
        }
        btnOdustani.setOnClickListener {
            Tftitle.text.clear()
            Tfnote.text.clear()
            //mainViewModel.deleteAllMaps()
        }
    }


    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        map.addMarker(markerOptions)
    }

    override fun onMarkerClick(p0: Marker?) = false



}