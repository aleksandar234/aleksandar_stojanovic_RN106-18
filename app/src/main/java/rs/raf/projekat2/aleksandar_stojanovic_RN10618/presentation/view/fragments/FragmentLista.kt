package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_liste.*
import kotlinx.android.synthetic.main.layout_item_map.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.R
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.contract.MainContract
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.activities.MapActivity
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.decoration.SpacingDecoration
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.recycler.adapters.MapAdapter
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.states.MapState
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.viewmodels.MainViewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class FragmentLista : Fragment(R.layout.fragment_liste) {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private lateinit var adapter: MapAdapter

//    private var sourt = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        listRv.layoutManager = LinearLayoutManager(context)
        adapter = MapAdapter(
            {
                val intent = Intent(context, MapActivity::class.java)
                intent.putExtra("mapa" , it)
                mainViewModel.setMapData(it,intent)
                startActivity(intent)
            }
        )

        val spacingDecoration = SpacingDecoration(20)
        listRv.addItemDecoration(spacingDecoration)
        listRv.adapter = adapter
    }

    private fun initListeners() {
        searchEt.doAfterTextChanged {
            val search = it.toString()
            mainViewModel.searchLocation(search)
        }
//        sortBtn.setOnClickListener {
//            sourt = !sourt
//            mainViewModel.getMaps(sourt)
//        }
    }

    private fun initObservers() {
        mainViewModel.mapState.observe(viewLifecycleOwner, Observer {
            Timber.e("Note - $it")
            renderState(it)
        })
        mainViewModel.getAllMaps()
    }

    private fun renderState(state: MapState) {
        when (state) {
            is MapState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.maps)
            }
            is MapState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is MapState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
//        TODO implement loading state
    }
}