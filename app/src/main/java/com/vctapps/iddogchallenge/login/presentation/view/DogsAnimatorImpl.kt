package com.vctapps.iddogchallenge.login.presentation.view

import android.animation.Animator
import android.animation.AnimatorInflater
import android.view.View
import com.vctapps.iddogchallenge.R
import timber.log.Timber
import java.util.*

class DogsAnimatorImpl: DogsAnimator {

    val animators = arrayListOf<Animator>()

    var currentAnimator: Animator? = null

    val animatorListener = object : Animator.AnimatorListener{
        override fun onAnimationRepeat(p0: Animator?) {}

        override fun onAnimationEnd(p0: Animator?) {sortAnimator()}

        override fun onAnimationCancel(p0: Animator?) {}

        override fun onAnimationStart(p0: Animator?) {}
    }

    override fun addDog(dog: View, directionToShow: Int) {
        val animator: Animator

        if(directionToShow == DogsAnimator.TO_RIGHT){
            animator = AnimatorInflater.loadAnimator(dog.context, R.animator.dog_left_to_right)
        } else {
            animator = AnimatorInflater.loadAnimator(dog.context, R.animator.dog_right_to_left)
        }

        animator.setTarget(dog)

        animators.add(animator)
    }

    override fun start() {
        sortAnimator()
    }

    override fun stop() {
        animators.forEach { animator ->
            animator.cancel()
        }
    }

    private fun sortAnimator(){
        currentAnimator?.removeAllListeners()

        val position = getRandomInt()
        val animator = animators[position]

        Timber.d("position sort: " + position)

        currentAnimator = animator

        currentAnimator?.addListener(animatorListener)

        currentAnimator?.startDelay = 20

        currentAnimator?.start()
    }

    private fun getRandomInt(): Int {
        return Random().nextInt(animators.size)
    }

}