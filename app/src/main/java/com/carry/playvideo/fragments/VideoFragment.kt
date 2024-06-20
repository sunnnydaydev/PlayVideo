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
class VideoFragment : Fragment(), Player.Listener {

    private var player: ExoPlayer? = null
    private var playerView: PlayerView? = null
    private var img: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerView = view.findViewById(R.id.player_view)
        img = view.findViewById(R.id.imageView)
        loadCover()
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireContext()).build().also { exoPlayer ->
            exoPlayer.addListener(this)
            playerView?.player = exoPlayer
            val videoUrl = arguments?.getString(ARG_VIDEO_URL) ?: return
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.repeatMode = REPEAT_MODE_ONE
            exoPlayer.play()
        }
    }

    private fun releasePlayer(){
        player?.stop()
        playerView?.player = null
        player?.release()
        player = null
    }

    override fun onResume() {
        super.onResume()
        initializePlayer()
        Log.d("VideoFragment","onResume:${arguments?.getString(ARG_VIDEO_URL)}")
    }

    override fun onPause() {
        super.onPause()
        Log.d("VideoFragment","onPause:${arguments?.getString(ARG_VIDEO_URL)}")
        player?.pause()
    }

    override fun onStop() {
        super.onStop()
        Log.d("VideoFragment","onStop:${arguments?.getString(ARG_VIDEO_URL)}")
        player?.stop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("VideoFragment","onDestroyView:${arguments?.getString(ARG_VIDEO_URL)}")
        releasePlayer()
    }


    companion object {
        private const val ARG_VIDEO_URL = "video_url"

        fun newInstance(videoUrl: String) = VideoFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_VIDEO_URL, videoUrl)
            }
        }
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            Player.STATE_IDLE -> {
                Log.d("VideoFragment","STATE_IDLE:${arguments?.getString(ARG_VIDEO_URL)}")
            }
            Player.STATE_BUFFERING -> {
                Log.d("VideoFragment","STATE_BUFFERING:${arguments?.getString(ARG_VIDEO_URL)}")
                img?.visibility = View.VISIBLE
            }
            Player.STATE_READY -> {
                img?.visibility = View.GONE
                Log.d("VideoFragment","STATE_READY:${arguments?.getString(ARG_VIDEO_URL)}")
            }
            Player.STATE_ENDED -> {
                Log.d("VideoFragment","STATE_ENDED:${arguments?.getString(ARG_VIDEO_URL)}")
            }
            else -> {
                Log.d("VideoFragment","else:${arguments?.getString(ARG_VIDEO_URL)}")
            }
        }
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        Log.d("VideoFragment","onPlayerError:${arguments?.getString(ARG_VIDEO_URL)}")
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        if (isPlaying) {
            // 播放器正在播放
            Log.d("VideoFragment","isPlaying:${arguments?.getString(ARG_VIDEO_URL)}")
        } else {
            // 播放器暂停或停止
            Log.d("VideoFragment","pause or stop video:${arguments?.getString(ARG_VIDEO_URL)}")
        }
    }

    private fun loadCover(){
        img?.let {
            Glide.with(this)
                .asBitmap()
                .load(arguments?.getString(ARG_VIDEO_URL))
                .frame(5000000) // 1秒（1000000微秒）
                .into(it)
        }
    }

}