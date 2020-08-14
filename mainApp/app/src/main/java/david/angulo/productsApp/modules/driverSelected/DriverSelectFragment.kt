package david.angulo.productsApp.modules.driverSelected

import android.view.View
import david.angulo.productsApp.R
import david.angulo.productsApp.databinding.FragmentDriverSelectBinding
import david.angulo.productsApp.model.Driver
import david.angulo.productsApp.modules.base.BaseFragment
import androidx.lifecycle.Observer


class DriverSelectFragment : BaseFragment<DriverSelectViewModel, FragmentDriverSelectBinding>(
    DriverSelectViewModel::class.java
) {

    private var mDriver = Driver()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_driver_select
    }

    override fun viewCreated(view: View?) {
        initObserver()
        arguments?.let {
            viewModel.getArguments(it)
        }
    }

    private fun initObserver() {
        viewModel.driver.observe(this, Observer {
            mDriver = it
        })
    }

}

