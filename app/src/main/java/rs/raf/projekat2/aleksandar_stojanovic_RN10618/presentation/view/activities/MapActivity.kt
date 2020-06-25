package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_editovanja.*
import kotlinx.android.synthetic.main.fragment_editovanja.btnOdustani
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.R
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.Map
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.contract.MainContract
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.viewmodels.MainViewModel

class MapActivity : AppCompatActivity(R.layout.fragment_editovanja), GoogleMap.OnMarkerClickListener,OnMapReadyCallback {

    lateinit var mMap : GoogleMap
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val lokacija : Map = this.intent.getParcelableExtra("mapa") as Map
        initListeners(lokacija)


    }

    private fun initListeners(lokacija : Map) {
        btnOdustani.setOnClickListener {
            finish()
        }
        btnEdituj.setOnClickListener {
            val title = titleEd.text.toString()
            val note = noteEd.text.toString()
            if(lokacija != null ) editLocation(title,note,lokacija)
            finish()
        }
    }

    private fun editLocation(title:String , note:String,lokacija: Map){
        val newMap = Map(lokacija!!.id,title,note,lokacija.latitude,lokacija.longditude,lokacija.date)
        mainViewModel.editMap(newMap)
    }

    override fun onMapReady(googleMap : GoogleMap) {
        mMap = googleMap

        googleMap.getUiSettings().setZoomControlsEnabled(true)
        googleMap.setOnMarkerClickListener(this)

        val mapa : Map = this.intent.getParcelableExtra("mapa") as Map

        titleEd.setText(mapa.title)
        noteEd.setText(mapa.note)
        datumZakucano.setText("Datum : ${mapa.date}")

        val lat = mapa.latitude
        val long = mapa.longditude

        val location = LatLng(lat,long)
        mMap.addMarker(MarkerOptions().position(location).title(mapa.title))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,14f))
    }


    override fun onMarkerClick(p0: Marker?) = false

}