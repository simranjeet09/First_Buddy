package dev.simranjeet.firstbuddy.ui.addpost

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.simranjeet.firstbuddy.R
import kotlinx.android.synthetic.main.add_post.view.*
import kotlinx.android.synthetic.main.add_post.view.title
import kotlinx.android.synthetic.main.post_item.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class AddPostFragment : Fragment(), KodeinAware {
    private val factory: AddPostViewModelFactory by instance()
    private lateinit var viewModel: AddPostViewModel
    override val kodein by kodein()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.add_post, container, false)
        viewModel = ViewModelProvider(requireActivity(), factory)[AddPostViewModel::class.java]

        view.description.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                viewModel.description.value = s.toString()
            }
        })
        view.title.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                viewModel.title.value = s.toString()
            }
        })

        view.buttonAdd.setOnClickListener {
            viewModel.addPost(requireContext())

            view.title.text?.clear()
            view.description.text?.clear()


        }
        return view
    }


}