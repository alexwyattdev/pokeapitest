package hu.alexujvary.pokeapitest.extensions

import android.view.View
import android.view.ViewGroup
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager


fun View.changeVisibility(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}

fun ViewGroup.invisible() {
    this.visibility = View.INVISIBLE
}

fun ViewGroup.gone() {
    this.visibility = View.GONE
}

fun ViewGroup.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.fadeOut() {
    val transition: Transition = Fade()
    transition.addTarget(this)
    TransitionManager.beginDelayedTransition(this.parent as ViewGroup, transition)
    this.visibility = View.GONE
}