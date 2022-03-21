package hu.alexujvary.pokeapitest.extensions

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//to be able to use a "scroll-to-top" animation without waiting for the entire list to scroll
fun RecyclerView.smoothAndFastScrollToPosition(targetItem: Int) {
    layoutManager?.apply {
        val maxScroll = 10
        when (this) {
            is LinearLayoutManager -> {
                val topItem = findFirstVisibleItemPosition()
                val distance = topItem - targetItem
                val anchorItem = when {
                    distance > maxScroll -> targetItem + maxScroll
                    distance < -maxScroll -> targetItem - maxScroll
                    else -> topItem
                }
                if (anchorItem != topItem) scrollToPosition(anchorItem)
                post {
                    smoothScrollToPosition(targetItem)
                }
            }
            else -> smoothScrollToPosition(targetItem)
        }
    }
}

//add a scroll-to-top FloatingActionButton view to a recyclerview to provide a behavior for showing and hiding
fun RecyclerView.addFabScrollToTop(fab: View) {
    val recyclerView = this
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if ((recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() == 0 || (recyclerView.layoutManager as LinearLayoutManager).itemCount == 0) {
                fab.gone()
            } else {
                fab.visible()
            }
        }
    })
}