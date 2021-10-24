package com.decagon.edconnect.presentation.ui.viewcontrollers.welcomescreens.onboarding.onBoardingAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.decagon.edconnect.presentation.ui.viewcontrollers.welcomescreens.onboarding.OnBoardingFragmentFour
import com.decagon.edconnect.presentation.ui.viewcontrollers.welcomescreens.onboarding.OnBoardingFragmentOne
import com.decagon.edconnect.presentation.ui.viewcontrollers.welcomescreens.onboarding.OnBoardingFragmentThree
import com.decagon.edconnect.presentation.ui.viewcontrollers.welcomescreens.onboarding.OnBoardingFragmentTwo

class OnBoardingAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val numOfTabs: Int = 4
    override fun getItemCount(): Int { return numOfTabs }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> OnBoardingFragmentTwo()
            2 -> OnBoardingFragmentThree()
            3 -> OnBoardingFragmentFour()
            else -> OnBoardingFragmentOne()
        }
    }

}
