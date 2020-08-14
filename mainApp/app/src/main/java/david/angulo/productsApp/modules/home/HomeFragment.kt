package david.angulo.productsApp.modules.home


import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import david.angulo.productsApp.databinding.FragmentHomeBinding
import david.angulo.productsApp.R
import david.angulo.productsApp.modules.base.BaseFragment
import david.angulo.productsApp.modules.home.adapter.InputsAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import david.angulo.productsApp.model.Driver
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : BaseFragment<HomeFragmentViewModel, FragmentHomeBinding>(
    HomeFragmentViewModel::class.java
) {

    private var driversAux = ArrayList<Driver>()
    private var drivers = ArrayList<Driver>()

    private lateinit var mInputsAdapter: InputsAdapter

    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun viewCreated(view: View?) {
        initInputsAdapter()
        initDriversList()
        setListeners()
    }

    private fun initDriversList() {
        context?.let {
            val layoutManager = GridLayoutManager(it, 1)
            rvDrivers.layoutManager = layoutManager
            rvDrivers.adapter = mInputsAdapter
        }
    }

    private fun initInputsAdapter() {
        mInputsAdapter = InputsAdapter(drivers, requireContext(), { driver ->

            if (drivers.size > 1) btnChooseDriver.visibility = View.VISIBLE
            else btnChooseDriver.visibility = View.GONE

            mInputsAdapter.remove(driver)
            showToast(driver.id!!, requireContext())
        }, { name, id ->
            drivers.forEach {
                if (it.id.equals(id)) {
                    it.name = name
                }
            }
        })
    }


    private fun checkCompleteError(): Boolean {
        var count = 0
        while (drivers.size < count) {
            if (drivers[count].name.isNullOrEmpty()) {
                showSnackbar(
                    view,
                    "Complete all fields!",
                    requireContext(),
                    R.color.colorRed
                )
                return true
            }
            count++

        }
        return false
    }


    private fun setListeners() {
        btnAddDriver.setOnClickListener {
            drivers.add(Driver(id = (Math.random() * 1000000000).toString(), name = ""))
            mInputsAdapter.notifyDataSetChanged()

            if (drivers.size > 1) btnChooseDriver.visibility = View.VISIBLE
            else btnChooseDriver.visibility = View.GONE
        }
        btnChooseDriver.setOnClickListener {
            val isError = checkCompleteError()
            if (!isError) {
                showToast(drivers[Random().nextInt(drivers.size)].name!!, context)
                drivers.clear()
                mInputsAdapter.notifyDataSetChanged()
                btnChooseDriver.visibility = View.GONE
            }

        }


    }
}
