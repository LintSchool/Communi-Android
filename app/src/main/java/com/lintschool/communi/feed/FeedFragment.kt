package com.lintschool.communi.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lintschool.communi.R
import com.lintschool.communi.camera.CameraActivity
import com.lintschool.communi.stories.StoriesActivity
import kotlinx.android.synthetic.main.feedbottomsheet.*
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    lateinit var storiesAdapter: StoriesAdapter
    lateinit var postsAdapter: PostsAdapter
    var postsList = listOf<Post>(
        Post(
            "1",
            null,
            "Kate",
            R.drawable.download,
            "ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga"
        ),
        Post(
            "2",
            R.drawable.download1,
            "Kate",
            R.drawable.download,
            "ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga"
        ),
        Post(
            id = "3",
            imagePath = null,
            userName = "Kate",
            userImagePath = R.drawable.download,
            postText = "ay 7aga ay 7aga ay 7aga ay 7agaga ay 7aga ay 7aga"
        ),
        Post(
            "4",
            null,
            "Kate",
            R.drawable.download,
            " ay 7aga ay 7aga ay 7aga "
        ),
        Post(
            "5",
            R.drawable.download1,
            "Kate",
            R.drawable.download,
            "ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga"
        ),
        Post(
            "6",
            R.drawable.download1,
            "Kate",
            R.drawable.download,
            "ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga ay 7aga"
        ),
        Post(
            id = "7",
            imagePath = null,
            userName = "Kate",
            userImagePath = R.drawable.download,
            postText = "aga ay 7aga ay 7aga ay 7aga ay 7aga"
        )
    )
    var storiesDataList = listOf<Image>(
        Image("0", null, true),
        Image("1", R.drawable.ic_profile, false),
        Image("2", R.drawable.ic_profile, false),
        Image("3", R.drawable.ic_profile, false),
        Image("4", R.drawable.ic_profile, false),
        Image("5", R.drawable.ic_profile, false),
        Image("6", R.drawable.ic_profile, false)
    )

    companion object {
        fun newInstance(): FeedFragment = FeedFragment()
        var postItemDecoratorOffset = 8
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
    }

    fun setUp() {
        feedTitle.text = getString(R.string.feed_title) + "ya 2y 7aga."

        feedSubTitle.setOnClickListener {
        }

        postsAdapter = PostsAdapter()
        postsAdapter.submitList(postsList)
        feedsRV.adapter = postsAdapter
        var layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        feedsRV.layoutManager = layoutManager
        feedsRV.addItemDecoration(PostItemDecorator(postItemDecoratorOffset))

        postsAdapter.onPostClick = { itemView ->
            Toast.makeText(
                activity,
                postsList[storiesRV.getChildAdapterPosition(itemView)].id.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }

        storiesAdapter = StoriesAdapter()
        storiesAdapter.submitList(storiesDataList)
        storiesRV.adapter = storiesAdapter
        storiesRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        storiesAdapter.onItmClick = { position ->
            startActivity(StoriesActivity.startIntent(requireContext(), position))
        }

        storiesAdapter.onAddMyStoryItmClick = { itemView ->
            startActivity(CameraActivity.startIntent(requireContext()))
        }

        feedSubTitle.setOnClickListener {
            val bottomSheetDialog = context?.let { it1 -> BottomSheetDialog(it1, R.style.BottomSheetDialogTheme) }

            val bottomSheetView = LayoutInflater.from(context).inflate(
                R.layout.feedbottomsheet, bottomsheetcontainers
            )
            bottomSheetDialog!!.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }
    }
}