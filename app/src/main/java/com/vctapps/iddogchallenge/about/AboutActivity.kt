package com.vctapps.iddogchallenge.about

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.vctapps.iddogchallenge.R
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Picasso.with(this)
                .load(R.drawable.profile_photo)
                .transform(CropCircleTransformation())
                .into(profilePicture)

        setUpSocialMediaButtons()

        setUpContactButtons()
    }

    private fun setUpContactButtons() {
        email.setOnClickListener {
            startActivity(IntentContact.getEmailIntent())
        }

        projectLink.setOnClickListener {
            startActivity(IntentContact.getProjectPage())
        }
    }

    private fun setUpSocialMediaButtons() {
        facebook.setOnClickListener {
            val intent = IntentSocialMedia.facebook(this)
            startActivity(intent)
        }

        linkedin.setOnClickListener {
            val intent = IntentSocialMedia.linkedin()
            startActivity(intent)
        }

        github.setOnClickListener {
            val intent = IntentSocialMedia.github()
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == android.R.id.home){
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
