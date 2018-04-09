package com.vctapps.iddogchallenge.dashboard.presentation.view

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vctapps.iddogchallenge.core.domain.DogCategory
import com.vctapps.iddogchallenge.dog.list.view.DogListFragment

class DashboardAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int) =
            DogListFragment.getInstance(DogCategory.categories[position])

    override fun getCount() = DogCategory.categories.size

    override fun getPageTitle(position: Int) = DogCategory.categories[position]

}