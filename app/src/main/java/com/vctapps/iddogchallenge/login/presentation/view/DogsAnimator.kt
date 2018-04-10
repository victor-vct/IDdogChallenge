package com.vctapps.iddogchallenge.login.presentation.view

import android.view.View

interface DogsAnimator {

    companion object {
        const val TO_RIGHT = 0
        const val TO_LEFT = 1
    }

    fun addDog(dog: View, directionToShow: Int)

    fun start()

    fun stop()

}