package com.lintschool.communi.camera

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lintschool.communi.R
import com.lintschool.communi.utils.loadImageUri
import kotlinx.android.synthetic.main.activity_share_media.*

class ShareMediaActivity : AppCompatActivity() {

    private val capturedAdapter = CapturedMediaAdapter()
    private val adapterList = mutableListOf<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_share_media)
        setUp()
    }

    private fun setUp() {
        setupAdapter()

        captured_images_rv.apply {
            adapter = capturedAdapter
            layoutManager =
                LinearLayoutManager(this@ShareMediaActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        if (intent.getParcelableExtra<Uri>(NEW_IMAGE) != null) {
            val imageUri = intent.getParcelableExtra<Uri>(NEW_IMAGE)
            adapterList.add(CapturedImage(0, imageUri!!))
            selected_photo_iv.loadImageUri(imageUri)
        }
        adapterList.add("Placeholder for view type add")
        capturedAdapter.notifyDataSetChanged()

        back_iv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupAdapter() {
        capturedAdapter.onAddClicked = {
            startActivityForResult(
                CameraActivity.startIntent(this@ShareMediaActivity),
                IMAGE_ACTIVITY_REQUEST_CODE
            )
        }

        capturedAdapter.onItemClick = {
            selected_photo_iv.loadImageUri((adapterList[it] as CapturedImage).imagePath)
        }

        capturedAdapter.onItemRemove = {
            if (capturedAdapter.currentList.size == 2) {
                finish()
            } else {
                if (capturedAdapter.lastSelected == it) {
                    if (capturedAdapter.lastSelected == adapterList.size - 2) {
                        capturedAdapter.lastSelected -= 1
                    } else {
                        capturedAdapter.lastSelected += 1
                    }
                }
                selected_photo_iv.loadImageUri((adapterList[capturedAdapter.lastSelected] as CapturedImage).imagePath)
                adapterList.removeAt(it)
                capturedAdapter.notifyItemRemoved(it)
            }
        }

        capturedAdapter.submitList(adapterList)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data?.getParcelableExtra<Uri>(CameraActivity.CAPTURED_IMAGE) != null) {
                    val imageUri = data.getParcelableExtra<Uri>(CameraActivity.CAPTURED_IMAGE)!!
                    adapterList.add(
                        0,
                        CapturedImage(
                            1,
                            imageUri
                        )
                    )
                    capturedAdapter.notifyItemInserted(0)
                    selected_photo_iv.loadImageUri(imageUri)
                }
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }

    companion object {
        private const val IMAGE_ACTIVITY_REQUEST_CODE = 0
        var NEW_IMAGE = "new image"

        fun buildIntent(context: Context, imagePath: Uri): Intent {
            val intent = Intent(context, ShareMediaActivity::class.java)
            intent.putExtra(NEW_IMAGE, imagePath)
            return intent
        }
    }
}