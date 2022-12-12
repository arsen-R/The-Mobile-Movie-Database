package com.example.themobilemoviedatabase.ui.adapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.themobilemoviedatabase.ui.favorite.favorite_item.FavoriteItemMovieFragment

class TabLayoutAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment = FavoriteItemMovieFragment()

        fragment.arguments = Bundle().apply {
            putInt("tab", position + 1)
        }
        Log.d("FragmentAdapterPosition", "fragment returned: $position")
        return fragment
    }
}