package com.axel.hogwartslibrary.ui.scenes.students.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.axel.hogwartslibrary.R
import com.squareup.picasso.Picasso

class StudentsAdapter : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    private val mDataList = ArrayList<StudentCellModel>()
    private val mDisplayList = ArrayList<StudentCellModel>()

    fun setData(newData: List<StudentCellModel>) {
        mDataList.clear()
        mDataList.addAll(newData)
        filter(query = "")
    }

    fun filter(query: String) {
        mDisplayList.clear()

        if (query.isEmpty()) {
            mDisplayList.addAll(mDataList)
            notifyDataSetChanged()
            return
        }

        mDisplayList.addAll(mDataList.filter {
            it.name.contains(query, true) || it.house.contains(query, true)
        })

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StudentViewHolder(itemView = inflater.inflate(R.layout.cell_student, parent, false))
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(model = mDisplayList[position])
    }

    override fun getItemCount(): Int = mDisplayList.count()

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val txtName: TextView = itemView.findViewById(R.id.txtStudentName)
        private val txtFaculty: TextView = itemView.findViewById(R.id.txtStudentFaculty)
        private val txtBirthday: TextView = itemView.findViewById(R.id.txtStudentBirthday)
        private val txtAncestry: TextView = itemView.findViewById(R.id.txtStudentAncestry)
        private val imgStudent: ImageView = itemView.findViewById(R.id.imgStudentImage)


        fun bind(model: StudentCellModel) {
            txtName.text = model.name
            txtBirthday.text = model.dateOfBirth
            txtFaculty.text = model.house
            txtAncestry.text = model.ancestry
            Picasso.get().load(model.image.replace("http", "https")).into(imgStudent)
        }

    }

}