package com.example.themobilemoviedatabase.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.themobilemoviedatabase.R
import com.example.themobilemoviedatabase.databinding.FragmentFavoriteBinding
import com.example.themobilemoviedatabase.ui.adapter.TabLayoutAdapter
import com.google.android.material.tabs.TabLayoutMediator


class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayoutList = resources.getStringArray(R.array.tab_layout_category).toList()
        val tabLayoutAdapter = TabLayoutAdapter(this)
        with(binding) {
            favoriteViewPager.adapter = tabLayoutAdapter
            val tabLayoutMediator =
                TabLayoutMediator(favoriteTabLayout, favoriteViewPager) { tab, position ->
                    tab.text = tabLayoutList[position]
                }
            tabLayoutMediator.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}