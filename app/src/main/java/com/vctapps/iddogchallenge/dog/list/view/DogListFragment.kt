package com.vctapps.iddogchallenge.dog.list.view

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vctapps.iddogchallenge.R
import com.vctapps.iddogchallenge.core.domain.DogCategory
import com.vctapps.iddogchallenge.core.presentation.BaseFragment
import com.vctapps.iddogchallenge.dog.detail.view.DogDetailActivity
import com.vctapps.iddogchallenge.dog.list.presenter.DogListPresenter
import kotlinx.android.synthetic.main.fragment_dog_list.*
import javax.inject.Inject

class DogListFragment : BaseFragment(), DogListView, DogListAdapter.OnClickDog {

    companion object {

        fun getInstance(dogCategory: String): DogListFragment {
            val fragment = DogListFragment()

            fragment.setCategory(dogCategory)

            return fragment
        }

    }

    private var dogCategory = DogCategory.categories[0]

    @Inject
    lateinit var presenter: DogListPresenter

    fun setCategory(dogCategory: String){
        this.dogCategory = dogCategory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dog_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter.onStart()

        presenter.loadList(dogCategory)
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onFinish()
    }

    override fun onClick(url: String, view: View) {
        val intent = DogDetailActivity.getIntent(context!!, url)

        val options = getOptionsToAnimate(view)

        startActivity(intent, options)
    }

    private fun getOptionsToAnimate(view: View): Bundle {
        val transitionName = getString(R.string.transition_string)

        val options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity!!, view, transitionName)

        return options.toBundle()!!
    }

    override fun showListDogs(urls: MutableList<String>) {
        val adapter = DogListAdapter(urls, this)

        recyclerViewDogs.adapter = adapter

        recyclerViewDogs.layoutManager = StaggeredGridLayoutManager(2, 1)
    }

}
