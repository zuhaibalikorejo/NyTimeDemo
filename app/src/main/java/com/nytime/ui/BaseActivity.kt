package com.nytime.ui


import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar



import androidx.lifecycle.Observer
import com.nytime.base.BaseViewModel

/**
 * Activity ViewModel class for application's dashboard
 * <p>
 * @author zuhaib
 * Email: zuhaib.ahmed@adib.com
 * Company: ADIB
 */

abstract class BaseActivity(
) : AppCompatActivity() {

    protected abstract fun initViewBinding()
    protected abstract val viewModel: BaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        setUpObservers()
    }



    override fun onUserInteraction() {

    }

    override fun onResume() {
        super.onResume()
        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)

    }

    override fun onPause() {
        super.onPause()
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

    }

    open fun setUpObservers() {

        viewModel.networkRequestProgress.observe(
            this,
            Observer {

            })

        viewModel.networkFailed.observe(this, Observer { it?.let {


        } })



    }


    override fun onDestroy() {

        super.onDestroy()
    }

}
