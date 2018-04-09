package com.vctapps.iddogchallenge.dog.di

import com.vctapps.iddogchallenge.dog.list.view.DogListFragment
import com.vctapps.iddogchallenge.dog.list.view.DogListView
import dagger.Binds
import dagger.Module

@Module
abstract class DogModulesView {

    @Binds
    abstract fun bindDogListView(fragment: DogListFragment): DogListView

}