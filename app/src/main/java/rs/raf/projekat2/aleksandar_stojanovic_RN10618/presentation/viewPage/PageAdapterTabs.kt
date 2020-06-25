package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.viewPage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.fragments.FragmentLista
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.fragments.FragmentMapa

class PageAdapterTabs(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object {
        private const val TAB_COUNT = 2
        const val TAB_MAPA = 0
        const val TAB_LISTA = 1
    }


    override fun getItem(position: Int): Fragment {
        return when(position) {
            TAB_MAPA -> FragmentMapa()
            else -> FragmentLista()
        }
    }

    override fun getCount(): Int {
        return TAB_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            TAB_MAPA -> "Mapa"
            else -> "Lista"
        }
    }

}