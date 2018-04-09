package com.vctapps.iddogchallenge.dog.list.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.vctapps.iddogchallenge.R
import kotlinx.android.synthetic.main.dog_list_item.view.*

class DogListAdapter(private val addressList: MutableList<String>,
                     private val onClickDog: OnClickDog):
        RecyclerView.Adapter<DogListAdapter.DogListViewHolder>() {

    interface OnClickDog{
        fun onClick(url: String, view: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.dog_list_item, parent, false)

        return DogListViewHolder(view)
    }

    override fun getItemCount() = addressList.size

    override fun onBindViewHolder(holder: DogListViewHolder, position: Int) {
        holder.loadImage(addressList[position])
    }

    inner class DogListViewHolder(view: View): RecyclerView.ViewHolder(view){

        val imageView = view.imageViewDogList

        fun loadImage(url: String){
            Picasso.with(itemView.context)
                    .load(url)
                    .into(imageView)

            imageView.setOnClickListener { onClickDog.onClick(url, itemView) }
        }

    }

}