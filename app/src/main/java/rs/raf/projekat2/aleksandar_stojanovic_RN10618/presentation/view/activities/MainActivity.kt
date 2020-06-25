package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.R
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.Map
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.data.local.models.MapEntity
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.contract.MainContract
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.viewPage.PageAdapter
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    private val mainViewModel : MainContract.ViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initViewpager()
        initNavigation()
    }

    private fun initViewpager() {
        viewPager.adapter = PageAdapter(supportFragmentManager)
    }

    private fun initNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.editNav -> {
                    viewPager.setCurrentItem(PageAdapter.FRAGMENT_EDITOVANJA,false)
                }
                R.id.locationNav -> {
                    viewPager.setCurrentItem(PageAdapter.FRAGMENT_LOKACIJE,false)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

}