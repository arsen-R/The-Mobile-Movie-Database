package com.example.themobilemoviedatabase.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.themobilemoviedatabase.R
import com.example.themobilemoviedatabase.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get () = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.posterBackground.load(R.drawable.bp)
        binding.detailsInfoButton.setOnClickListener { view ->
            val detailsDirections = HomeFragmentDirections.actionHomeScreenToDetailFragment()
            findNavController().navigate(detailsDirections)
        }
        binding.favoriteButton.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                binding.favoriteButton.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(view.context,R.drawable.ic_round_favorite_24), null, null)
            } else {
                binding.favoriteButton.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(view.context,R.drawable.ic_round_favorite_border_24), null, null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}