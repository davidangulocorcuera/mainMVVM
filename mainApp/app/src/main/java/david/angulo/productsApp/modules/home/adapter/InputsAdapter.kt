package david.angulo.productsApp.modules.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import david.angulo.productsApp.R
import david.angulo.productsApp.model.Driver
import david.angulo.productsApp.modules.base.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.item_driver_input.*
import kotlinx.android.synthetic.main.item_driver_input.view.*


class InputsAdapter(
    val items: ArrayList<Driver>,
    context: Context,
    var onInputItemClicked: (Driver) -> Unit,
    var onNameReady: (String, String) -> Unit
) : BaseRecyclerAdapter<Driver, InputsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_driver_input, parent, false)

        return ViewHolder(v)
    }

    init {
        list = items
    }

    override fun removeAll() {
        super.removeAll()
    }

    override fun remove(element: Driver) {
        super.remove(element)
    }



    inner class ViewHolder(var view: View) : BaseRecyclerAdapter.ViewHolder(view) {
        private lateinit var current: Driver


        override fun bind(position: Int) {
            current = getItem(position)
            setValues()

        }




        private fun setValues() {
            itemView.ivDelete.setOnClickListener {
                onInputItemClicked(current)

            }
            itemView.etDriver.addTextChangedListener {
                onNameReady(it.toString(), current.id!!)
            }
        }

    }
}