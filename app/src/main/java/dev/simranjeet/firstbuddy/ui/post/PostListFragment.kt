package dev.simranjeet.firstbuddy.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import dev.simranjeet.firstbuddy.R
import dev.simranjeet.firstbuddy.databinding.FragmentPostBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class PostListFragment : Fragment(), KodeinAware {

    private val factory: PostViewModelFactory by instance()
    private lateinit var viewModel: PostViewModel
    override val kodein by kodein()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentPostBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false)
        viewModel = ViewModelProvider(requireActivity(), factory)[PostViewModel::class.java]
        if (arguments != null) {
            val title = requireArguments().getString("titleToFetch", "Accommodation")
            viewModel.dataToFetch.value = title
            binding.toolbarTitle.text = title.uppercase()

            viewModel.fetchData()
        }


         val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        binding.viewmodel = viewModel
        // This callback will only be called when MyFragment is at least Started.
        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(false /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                }
            }
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()

        }

        val adapter = PostAdapter()
        binding.rvMain.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvMain.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)


        }
        viewModel.email.observe(viewLifecycleOwner) {
            adapter.setEmail(it)

        }
        viewModel


        return binding.root
    }


}