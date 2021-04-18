package com.axel.hogwartslibrary.ui.scenes.staff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axel.hogwartslibrary.R
import com.axel.hogwartslibrary.ui.scenes.staff.adapters.StaffAdapter
import kotlinx.android.synthetic.main.fragment_staffs.*

class StaffFragment : Fragment() {

    private lateinit var staffViewModel: StaffViewModel
    private val mAdapter = StaffAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        staffViewModel =
            ViewModelProvider(this).get(StaffViewModel::class.java)
        return inflater.inflate(R.layout.fragment_staffs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecycler()
        configureDataDisplay()

        btnStaffHuman.setOnClickListener {
            btnStaffHuman.isSelected = !btnStaffHuman.isSelected
            staffViewModel.pressFilter(type = "human", isSelected = btnStaffHuman.isSelected)
        }

        btnStaffHalf.setOnClickListener {
            btnStaffHalf.isSelected = !btnStaffHalf.isSelected
            staffViewModel.pressFilter(type = "half-giant", isSelected = btnStaffHalf.isSelected)
        }

        btnStaffWarewolf.setOnClickListener {
            btnStaffWarewolf.isSelected = !btnStaffWarewolf.isSelected
            staffViewModel.pressFilter(type = "werewolf", isSelected = btnStaffWarewolf.isSelected)
        }

        btnStaffCat.setOnClickListener {
            btnStaffCat.isSelected = !btnStaffCat.isSelected
            staffViewModel.pressFilter(type = "cat", isSelected = btnStaffCat.isSelected)
        }
    }

    private fun configureRecycler() {
        recyclerStaffs.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerStaffs.adapter = mAdapter
    }

    private fun configureDataDisplay() {
        staffViewModel.staffsDisplay.observe(viewLifecycleOwner, Observer { data ->
            mAdapter.setData(newData = data)
        })
    }
}