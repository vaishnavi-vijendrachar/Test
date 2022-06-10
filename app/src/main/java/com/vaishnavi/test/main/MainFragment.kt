package com.vaishnavi.test.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.vaishnavi.test.ComposeContainer
import com.vaishnavi.test.databinding.FragmentFirstBinding
import com.vaishnavi.test.ui.ErrorScreen
import com.vaishnavi.test.ui.LoadingScreen
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        mainViewModel =
            ViewModelProvider(this)[MainViewModel::class.java]
        binding.composeView.setContent {
            LoadingScreen(1f)
        }
        mainViewModel.getDataFromServer()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            mainViewModel.response.collect() { response ->
                binding.composeView.setContent {
                    if (response != null) {
                        LoadingScreen(0f)
                        ComposeContainer(response)
                    } else {
                        LoadingScreen(1f)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}