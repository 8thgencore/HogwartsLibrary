package com.axel.hogwartslibrary.ui.scenes.house

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.axel.hogwartslibrary.R
import com.axel.hogwartslibrary.helpers.Keys
import kotlinx.android.synthetic.main.fragment_houses.*
import kotlinx.android.synthetic.main.house_detail_fragment.*

class HouseDetailFragment : Fragment() {

    companion object {
        fun newInstance() = HouseDetailFragment()
    }

    private lateinit var viewModel: HouseDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.house_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HouseDetailViewModel::class.java)
        configureLayout()

        viewModel.fetchData(house = arguments?.get(Keys.Faculty.value) as Houses)
    }

    private fun configureLayout() {
        viewModel.houseName.observe(this, Observer {
            txtHouseName.text = it
        })

        viewModel.leader.observe(this, Observer {
            txtHouseLeader.text =
                getString(R.string.house_detail_leader).replace("[LEADER_NAME]", it)
        })

        viewModel.founder.observe(this, Observer {
            txtHouseOwner.text =
                getString(R.string.house_detail_owner).replace("[FOUNDER_NAME]", it)
        })

        viewModel.ghost.observe(this, Observer {
            txtHouseGhost.text =
                getString(R.string.house_detail_ghost).replace("[GHOST_NAME]", it)
        })

        viewModel.houseImage.observe(this, Observer {
            imgHouseDetail.setImageDrawable(resources.getDrawable(it))
        })
    }


}