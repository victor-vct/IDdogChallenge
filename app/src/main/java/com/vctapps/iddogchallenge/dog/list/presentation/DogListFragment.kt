package com.vctapps.iddogchallenge.dog.list.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vctapps.iddogchallenge.R
import com.vctapps.iddogchallenge.core.domain.DogCategory

class DogListFragment : Fragment() {

    companion object {

        fun getInstance(dogCategory: String): DogListFragment{
            val fragment = DogListFragment()

            fragment.setCategory(dogCategory)

            return fragment
        }

    }

    private var dogCategory = DogCategory.categories[0]

    fun setCategory(dogCategory: String){
        this.dogCategory = dogCategory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dog_list, container, false)
    }

}
