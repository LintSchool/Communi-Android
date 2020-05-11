package com.lintschool.communi.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lintschool.communi.R

/**
 * A simple [Fragment] subclass.
 */
class EventsFragment : Fragment() {

    companion object {
        fun newInstance(): EventsFragment = EventsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false)
    }
}