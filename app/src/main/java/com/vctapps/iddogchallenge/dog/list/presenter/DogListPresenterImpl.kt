package com.vctapps.iddogchallenge.dog.list.presenter

import com.vctapps.iddogchallenge.dog.data.DogsRepository
import com.vctapps.iddogchallenge.dog.list.view.DogListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DogListPresenterImpl(private val view: DogListView,
                           private val repository: DogsRepository): DogListPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun onStart() {
        view.showLoading()
    }

    override fun loadList(category: String) {
        compositeDisposable.add(
                repository.getUrlAddressDogs(category)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({urls ->
                            view.showListDogs(urls)
                            view.hideLoading()
                        }, { error ->
                            Timber.e(error)
                            view.hideLoading()
                            view.showError()
                        }, {
                            Timber.d("Nao foi possivel recuperar os dogs")
                            view.hideError()
                        })
        )
    }

    override fun onFinish() {
        compositeDisposable.dispose()
    }
}