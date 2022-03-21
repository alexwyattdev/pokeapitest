package hu.alexujvary.pokeapitest.view.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import hu.alexujvary.pokeapitest.extensions.changeVisibility
import hu.alexujvary.pokeapitest.util.ErrorWrapper

abstract class BaseFragment constructor(@LayoutRes val resId: Int) : Fragment(resId) {

    var progressLayout: View? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    abstract fun injectDependencies()

    open fun displayError(errorWrapper: ErrorWrapper?) {
        errorWrapper?.let { error ->
            val errorMessage = error.errorMessage ?: getString(error.errorType.errorResId)
            view?.let { nonNullView ->
                Snackbar.make(nonNullView, errorMessage, Snackbar.LENGTH_LONG).show()
            } ?: kotlin.run {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    open fun displayProgress(show: Boolean) {
        takeIf { show }?.let {
            if (swipeRefreshLayout?.isRefreshing != true) {
                progressLayout?.changeVisibility(true)
            }
        } ?: kotlin.run {
            if (swipeRefreshLayout?.isRefreshing == true) {
                swipeRefreshLayout?.isRefreshing = false
            } else {
                progressLayout?.changeVisibility(false)
            }
        }
    }
}