package com.axel.hogwartslibrary.ui.scenes.students

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axel.hogwartslibrary.R
import com.axel.hogwartslibrary.ui.scenes.students.adapters.StudentsAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_students.*

class StudentsFragment : Fragment() {

    private lateinit var studentViewModel: StudentsViewModel
    private val mAdapter = StudentsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentViewModel =
            ViewModelProvider(this).get(StudentsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_students, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
        setupLoading()

        context?.let {
            recyclerStudents.adapter = mAdapter
            recyclerStudents.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        textStudentSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mAdapter.filter(query = s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        studentViewModel.fetchStudents()

    }


    private fun setupLoading() {
        studentViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            txtStudentsLoading.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }

            recyclerStudents.visibility = if (it) {
                View.GONE
            } else {
                View.VISIBLE
            }

            textStudentSearch.visibility = if (it) {
                View.GONE
            } else {
                View.VISIBLE
            }
        })
    }

    private fun setupData() {
        studentViewModel.students.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                mAdapter.setData(newData = it)
            }
        })
    }


}