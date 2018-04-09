package com.vctapps.iddogchallenge.dog.list.presenter

import com.vctapps.iddogchallenge.core.presentation.BasePresenter

interface DogListPresenter: BasePresenter{

    fun loadList(category: String)

}