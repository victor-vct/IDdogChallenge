package com.vctapps.iddogchallenge.dog.detail.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.vctapps.iddogchallenge.R
import kotlinx.android.synthetic.main.activity_dog_detail.*

class DogDetailActivity : AppCompatActivity() {

    companion object {

        const val URL_IMAGE = "url"

        fun getIntent(context: Context, imageUrl: String): Intent{
            val intent = Intent(context, DogDetailActivity::class.java)

            intent.putExtra(URL_IMAGE, imageUrl)

            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_detail)

        val url = intent.getStringExtra(URL_IMAGE)

        Picasso.with(this)
                .load(url)
                .into(imageViewDogDetail)
    }
}
