package com.vctapps.iddogchallenge.dog.di

import com.vctapps.iddogchallenge.core.data.IDdogApi
import com.vctapps.iddogchallenge.dog.data.DogsRepository
import com.vctapps.iddogchallenge.dog.data.DogsRepositoryImpl
import com.vctapps.iddogchallenge.dog.list.presenter.DogListPresenter
import com.vctapps.iddogchallenge.dog.list.presenter.DogListPresenterImpl
import com.vctapps.iddogchallenge.dog.list.view.DogListView
import dagger.Module
import dagger.Provides

@Module
class DogModules{

    @Provides
    fun providesDogListPresenter(view: DogListView,
                                 repository: DogsRepository): DogListPresenter =
            DogListPresenterImpl(view, repository)

    @Provides
    fun providesDogsRepository(api: IDdogApi): DogsRepository =
            DogsRepositoryImpl(api)

}