package com.example.themobilemoviedatabase.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.themobilemoviedatabase.R

class SettingsFragment : PreferenceFragmentCompat() {
    private val themeProvider by lazy { ThemeProvider(requireContext()) }
    private val themePreference by lazy { findPreference<ListPreference>("theme_key") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setThemePreference()
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
    private fun setThemePreference() {
        themePreference?.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newsValue ->
            if (newsValue is String) {
                val theme = themeProvider.getTheme(newsValue)
                AppCompatDelegate.setDefaultNightMode(theme)
            }
            true
        }
        themePreference?.summaryProvider = Preference.SummaryProvider<ListPreference> { preference ->
            themeProvider.getThemFromPreferencesDescription(preference.value)
        }
    }
}