package itu.m1.edukids.view.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import itu.m1.edukids.controller.VideoViewModel
import itu.m1.edukids.databinding.FragmentVideoListBinding

/**
 * A fragment representing a list of Items.
 */
class VideoFragmentList : Fragment() {

    private var columnCount = 1
    private val viewModel: VideoViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var binding: FragmentVideoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }

        observeEndlessScrolling()
    }

    private fun observeEndlessScrolling() {
//        binding.videoList.setOnScrollChangeListener { view, i, i2, i3, i4 ->  }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoListBinding.inflate(inflater)

        progressBar = binding.progressBarVideo
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.videoList.adapter = VideoListRecyclerViewAdapter()

        progressBar.visibility = View.GONE

        return binding.root
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            VideoFragmentList().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}