package com.axel.hogwartslibrary.ui.scenes.staff.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.axel.hogwartslibrary.R

class StaffAdapter : RecyclerView.Adapter<StaffAdapter.StaffViewHolder>() {
    private val mDataList = ArrayList<StaffCellModel>()

    fun setData(newData: List<StaffCellModel>) {
        mDataList.clear()
        mDataList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StaffViewHolder(itemView = inflater.inflate(R.layout.cell_staff, parent, false))
    }

    override fun onBindViewHolder(holder: StaffViewHolder, position: Int) {
        holder.bind(model = mDataList[position])
    }

    override fun getItemCount(): Int = mDataList.count()

    class StaffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val txtName: TextView = itemView.findViewById(R.id.txtStaffName)
        private val txtType: TextView = itemView.findViewById(R.id.txtStaffType)

        fun bind(model: StaffCellModel) {
            txtName.text = model.name
            txtType.text = model.species
        }

    }
}