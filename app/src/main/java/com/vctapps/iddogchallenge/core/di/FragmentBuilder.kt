package com.vctapps.iddogchallenge.core.di

import android.support.v4.app.Fragment
import com.vctapps.iddogchallenge.dog.di.DogComponent
import com.vctapps.iddogchallenge.dog.list.view.DogListFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @FragmentKey(DogListFragment::class)
    abstract fun bindDogListFragment(builder: DogComponent.Builder): AndroidInjector.Factory<out Fragment>

}