package com.carry.playvideo.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.carry.playvideo.fragments.ImageFragment
import com.carry.playvideo.fragments.VideoFragment
import com.google.common.collect.Multimap

/**
 * Create by SunnyDay /06/07 21:40:22
 */
class VideoPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    private val videoUrls = listOf(
        "https://media.w3.org/2010/05/sintel/trailer.mp4",
        "https://www.w3schools.com/html/movie.mp4",
        "https://media.w3.org/2010/05/sintel/trailer.mp4",
        "https://www.w3schools.com/html/movie.mp4",
        "https://media.w3.org/2010/05/sintel/trailer.mp4",
        "https://www.w3schools.com/html/movie.mp4",
        "https://media.w3.org/2010/05/sintel/trailer.mp4",
        "https://www.w3schools.com/html/movie.mp4",
        "https://media.w3.org/2010/05/sintel/trailer.mp4",
        "https://www.w3schools.com/html/movie.mp4",
        "https://media.w3.org/2010/05/sintel/trailer.mp4",
        "https://www.w3schools.com/html/movie.mp4",
        "https://media.w3.org/2010/05/sintel/trailer.mp4",
        "https://www.w3schools.com/html/movie.mp4",
    )

    override fun getItemCount(): Int = videoUrls.size

    override fun createFragment(position: Int): Fragment {
        return if (position % 3 == 0) {
            ImageFragment.newInstance(videoUrls[position])
        } else {
            VideoFragment.newInstance(videoUrls[position])
        }

    }
}