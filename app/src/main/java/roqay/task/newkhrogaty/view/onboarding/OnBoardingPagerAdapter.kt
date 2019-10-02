package roqay.task.newkhrogaty.view.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import roqay.task.newkhrogaty.view.BlankFragment
import roqay.task.newkhrogaty.view.onboarding.onboardingFragments.FirstOnBoardingFragment
import roqay.task.newkhrogaty.view.onboarding.onboardingFragments.SecondOnBoardingFragment
import roqay.task.newkhrogaty.view.onboarding.onboardingFragments.ThirdOnBoardingFragment

class OnBoardingPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FirstOnBoardingFragment()
            1 -> SecondOnBoardingFragment()
            2 -> ThirdOnBoardingFragment()
            else -> BlankFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}