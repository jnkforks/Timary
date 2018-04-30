package com.github.ojh102.timary.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.view.WindowManager
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseActivity
import com.github.ojh102.timary.databinding.ActivityMainBinding
import com.github.ojh102.timary.ui.main.archieve.ArchiveFragment
import com.github.ojh102.timary.ui.main.home.HomeFragment
import com.github.ojh102.timary.ui.main.setting.SettingFragment
import com.github.ojh102.timary.util.extension.addFragment
import com.github.ojh102.timary.util.extension.hideFragment
import com.github.ojh102.timary.util.extension.showFragment
import com.github.ojh102.timary.util.resources.TimaryResourcesUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainContract.MainViewModel>(),
        BottomNavigationView.OnNavigationItemSelectedListener {

    override fun getLayoutRes() = R.layout.activity_main
    override fun getModelClass() = MainContract.MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setBackgroundDrawableResource(R.drawable.background_gradient_left_to_right)

        initializeBottomNavigation()
        initializeFragments()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        hideFragment(HomeFragment.TAG)
        hideFragment(ArchiveFragment.TAG)
        hideFragment(SettingFragment.TAG)

        return when (item.itemId) {
            R.id.navigation_home -> {
                showFragment(HomeFragment.TAG)
                true
            }
            R.id.navigation_archive -> {
                showFragment(ArchiveFragment.TAG)
                true
            }
            R.id.navigation_setting -> {
                showFragment(SettingFragment.TAG)
                true
            }
            else -> {
                false
            }
        }
    }

    private fun initializeBottomNavigation() {
        navigation.enableAnimation(false)
        navigation.enableShiftingMode(false)
        navigation.enableItemShiftingMode(false)
        navigation.elevation = 0f
        navigation.setTextVisibility(false)

        navigation.itemIconTintList = null
        navigation.itemHeight = TimaryResourcesUtil.dpToPixel(56f)
        navigation.setIconsMarginTop(TimaryResourcesUtil.dpToPixel(16f))

        navigation.onNavigationItemSelectedListener = this
    }

    private fun initializeFragments() {
        addFragment(R.id.container_fragment, HomeFragment.newInstance(), HomeFragment.TAG)
        addFragment(R.id.container_fragment, ArchiveFragment.newInstance(), ArchiveFragment.TAG)
        addFragment(R.id.container_fragment, SettingFragment.newInstance(), SettingFragment.TAG)

        hideFragment(ArchiveFragment.TAG)
        hideFragment(SettingFragment.TAG)
    }

}