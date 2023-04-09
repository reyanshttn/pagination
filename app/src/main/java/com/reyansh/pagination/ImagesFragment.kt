package com.reyansh.pagination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.reyansh.pagination.adapter.ImagesAdapter
import com.reyansh.pagination.adapter.LoaderStateAdapter
import com.reyansh.pagination.viewmodel.ImagesViewModel
import com.example.sample.databinding.FragmentImagesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class ImagesFragment : Fragment() {

    private lateinit var viewModel: ImagesViewModel
    private var _binding: FragmentImagesBinding? = null
    private lateinit var adapter: ImagesAdapter
    private lateinit var loaderAdapter: LoaderStateAdapter


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupUi()
        lifecycleScope.launch {
            viewModel.fetchImages().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        viewModel = defaultViewModelProviderFactory.create(ImagesViewModel::class.java)
        adapter = ImagesAdapter()
        loaderAdapter = LoaderStateAdapter()
    }

    private fun setupUi() {
        val manager = GridLayoutManager(context, 2)

        binding.rvImages.layoutManager = manager
        binding.rvImages.adapter = adapter.withLoadStateFooter(loaderAdapter)

    }
}