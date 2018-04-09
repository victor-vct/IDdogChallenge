package com.vctapps.iddogchallenge.dog.di

import com.vctapps.iddogchallenge.dog.list.view.DogListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [
    DogModules::class,
    DogModulesView::class]
)
interface DogComponent: AndroidInjector<DogListFragment> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<DogListFragment>()

}