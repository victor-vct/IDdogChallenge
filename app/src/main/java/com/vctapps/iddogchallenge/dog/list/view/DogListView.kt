package com.vctapps.iddogchallenge.dog.list.view

import com.vctapps.iddogchallenge.core.presentation.BaseView

interface DogListView: BaseView {

    fun showListDogs(urls: MutableList<String>)

}