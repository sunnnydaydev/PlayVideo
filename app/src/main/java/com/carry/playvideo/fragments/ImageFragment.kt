package com.carry.playvideo.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.Player.REPEAT_MODE_ONE
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.bumptech.glide.Glide
import com.carry.playvideo.R


/**
 * Create by SunnyDay /06/07 21:42:59
 */
class ImageFragment : Fragment(){

    private var img: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        img = view.findViewById(R.id.imageView)
    }


    override fun onResume() {
        super.onResume()
        Log.d("VideoFragment","onResume:${arguments?.getString(ARG_VIDEO_URL)}")
    }

    override fun onPause() {
        super.onPause()
        Log.d("VideoFragment","onPause:${arguments?.getString(ARG_VIDEO_URL)}")
    }

    override fun onStop() {
        super.onStop()
        Log.d("VideoFragment","onStop:${arguments?.getString(ARG_VIDEO_URL)}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        private const val ARG_VIDEO_URL = "video_url"

        fun newInstance(videoUrl: String) = ImageFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_VIDEO_URL, videoUrl)
            }
        }
    }

    private fun loadCover(){
        img?.let {
            Glide.with(this)
                .asBitmap()
                .load(arguments?.getString(ARG_VIDEO_URL))
                .into(it)
        }
    }
}