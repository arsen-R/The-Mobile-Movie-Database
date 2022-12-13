package com.example.themobilemoviedatabase.ui.profile

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.themobilemoviedatabase.R
import com.example.themobilemoviedatabase.databinding.FragmentProfileBinding
import com.example.themobilemoviedatabase.domain.util.Constants

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.settingsMenuButton.setOnClickListener { view ->
            val action = ProfileFragmentDirections.actionProfileScreenToSettingsScreen()
            findNavController().navigate(action)
        }
        binding.bugReportMenuButton.setOnClickListener {
            contactUs(
                arrayOf(Constants.EMAIL_ADDRESS),
                resources.getString(R.string.bug_report_label)
            )
        }
        binding.sourceCodeMenuButton.setOnClickListener {
            goToOriginalWebPage("https://github.com/arsen-R/The-Mobile-Movie-Database")
        }
    }
    private fun contactUs(addresses: Array<String>, subject: String) {
        val sendFeedback = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        try {
            startActivity(sendFeedback)
        } catch (e: ActivityNotFoundException) {
            Log.d("LogArticleNewsError", e.message.toString())
        }
    }
    private fun goToOriginalWebPage(link: String) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        try {
            startActivity(webIntent)
        } catch (e: ActivityNotFoundException) {
            Log.d("LogArticleNewsError", e.message.toString())
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}