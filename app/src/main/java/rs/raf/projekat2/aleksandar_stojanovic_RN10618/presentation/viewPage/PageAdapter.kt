package rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.viewPage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.fragments.FragmentEdit
import rs.raf.projekat2.aleksandar_stojanovic_RN10618.presentation.view.fragments.FragmentLokacije
import timber.log.Timber

class PageAdapter(fragmentManager: FragmentManager) :FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object{
        private const val ITEM_COUNT = 2
        const val FRAGMENT_EDITOVANJA = 0
        const val FRAGMENT_LOKACIJE = 1
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            FRAGMENT_EDITOVANJA -> FragmentEdit()
            else -> FragmentLokacije()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            FRAGMENT_EDITOVANJA -> "Edit"
            else -> "Locations"
        }
    }

}