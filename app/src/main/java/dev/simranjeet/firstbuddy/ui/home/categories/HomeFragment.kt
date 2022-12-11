package dev.simranjeet.firstbuddy.ui.home.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dev.simranjeet.firstbuddy.R
import dev.simranjeet.firstbuddy.data.models.DataModal
import dev.simranjeet.firstbuddy.databinding.FragmentHomeBinding
import dev.simranjeet.firstbuddy.ui.home.HomeViewModel
import dev.simranjeet.firstbuddy.ui.home.HomeViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class HomeFragment : Fragment(), KodeinAware {

    private val factory: HomeViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel
    override val kodein by kodein()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]

        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewmodel = viewModel

        val adapter = CategoriesAdapter()
        binding.rvMain.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvMain.adapter = adapter
        viewModel.categories.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }


        return binding.root
    }


}