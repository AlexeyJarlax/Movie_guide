package ru.yandex.practicum.moviessearch.ui.info

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.yandex.practicum.moviessearch.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val versionName = getVersionName()
        binding.versionTextView.text = "v. ${versionName}"
    }

    // Метод для получения версии приложения
    private fun getVersionName(): String {
        return try {
            val packageInfo: PackageInfo =
                requireActivity().packageManager.getPackageInfo(
                    requireActivity().packageName,
                    0
                )
            packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            "N/A"
        }
    }
}