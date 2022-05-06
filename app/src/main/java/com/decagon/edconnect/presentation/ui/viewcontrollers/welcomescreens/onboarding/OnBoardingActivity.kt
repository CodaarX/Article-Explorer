package com.decagon.edconnect.presentation.ui.viewcontrollers.welcomescreens.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.decagon.edconnect.R
import com.decagon.edconnect.databinding.ActivityOnboardingBinding
import com.decagon.edconnect.presentation.ui.viewcontrollers.authentication.AuthenticationActivity
import com.decagon.edconnect.presentation.ui.viewcontrollers.welcomescreens.onboarding.onBoardingAdapter.OnBoardingAdapter
import com.decagon.edconnect.utils.animations.Animator
import com.decagon.edconnect.utils.animations.DepthPageTransformer
import com.decagon.edconnect.utils.hideView
import com.decagon.edconnect.utils.showToast
import com.decagon.edconnect.utils.showView

class OnBoardingActivity : AppCompatActivity() {

    private val binding get() = _binding!!
    private var _binding: ActivityOnboardingBinding? = null
    private var pagePosition = 0
    private lateinit var nextButton : CardView
    private lateinit var skipButton:  CardView
    private lateinit var viewPager : ViewPager2
    private lateinit var prevButton : CardView
    private lateinit var goButton : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_EdConnectMain)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        _binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nextButton = binding.onboardingNextButton
        prevButton = binding.onboardingPrevButton
        skipButton = binding.onboardingSkipButton
        viewPager = binding.onBoardingViewPagers
        goButton = binding.goToAppBtn


        setupViews()
        setListeners()
    }

    private fun setListeners(){


        nextButton.setOnClickListener {
            when (pagePosition) {
                0 -> { viewPager.currentItem = 1 }
                1 -> { viewPager.currentItem = 2 }
                2 -> { viewPager.currentItem = 3 }
                3 -> {
                    startActivity(Intent(this@OnBoardingActivity, AuthenticationActivity::class.java))
                    Animator.animateActivityFadeContext(this@OnBoardingActivity)
                }
            }
        }

        prevButton.setOnClickListener {

            when (pagePosition) {
                1 -> { viewPager.currentItem = 0 }
                2 -> { viewPager.currentItem = 1 }
                3 -> { viewPager.currentItem = 2 }
            }
        }

        skipButton.setOnClickListener {
            val intent = Intent(this@OnBoardingActivity, AuthenticationActivity::class.java)
            startActivity(intent)
            Animator.animateActivityFadeContext(this@OnBoardingActivity)
        }

    }
    private fun setupViews() {

        val adapter = OnBoardingAdapter(supportFragmentManager, lifecycle)

        viewPager.apply {
            this.adapter = adapter
            this.setPageTransformer(DepthPageTransformer)
        }


        viewPager.registerOnPageChangeCallback(

            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {

                    super.onPageSelected(position)
                    pagePosition = position

                    when (position) {
                        0 -> { prevButton.hideView() }
                        3 -> { goButton.text = "Enter" }
                        else -> {
                            goButton.text = "next"
                            prevButton.showView()
                        }
                    }
                }
            }
        )

    }

    override fun onBackPressed() {
        if (binding.onBoardingViewPagers.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            binding.onBoardingViewPagers.currentItem = binding.onBoardingViewPagers.currentItem - 1
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}