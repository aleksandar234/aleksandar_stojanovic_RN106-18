package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_lokacija.*
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.R
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.viewPage.PageAdapterTabs

class FragmentLokacije : Fragment(R.layout.fragment_lokacija) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        initTabs()
    }

    private fun initTabs() {
        viewPagerTab.adapter = PageAdapterTabs(childFragmentManager)
        tabLayout.setupWithViewPager(viewPagerTab)
    }

}