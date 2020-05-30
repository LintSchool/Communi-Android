package com.example.rxjavademo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lintschool.communi.R

class FeedCommentsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed_comments, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bottomSheetFragment = BottomSheetFragment()

        bottomSheetFragment.isCancelable = false
        bottomSheetFragment.show(fragmentManager!!, "feed_comments")
    }
}