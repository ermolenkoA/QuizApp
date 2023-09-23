package com.example.quizapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizapp.R
import com.example.quizapp.adapters.TopicListAdapter
import com.example.quizapp.databinding.FragmentTopicSelectionBinding
import com.example.quizapp.domain.TopicSelectionViewModel
import com.example.quizapp.utils.Keys.TOPIC_ID_KEY

class TopicSelectionFragment : Fragment() {
    private var _binding: FragmentTopicSelectionBinding? = null
    private val viewModel: TopicSelectionViewModel by activityViewModels()
    private var recyclerViewAdapter: TopicListAdapter? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        _binding = FragmentTopicSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        recyclerViewAdapter = TopicListAdapter(context)
        with(binding){
            recyclerView.adapter = recyclerViewAdapter
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            submitButton.setOnClickListener {  }
        }
    }

    private fun setupObservers() {
        viewModel.topics.observe(viewLifecycleOwner) { newData ->
            recyclerViewAdapter?.differ?.submitList(newData)
        }
        recyclerViewAdapter?.isTopicSelected?.observe(viewLifecycleOwner) {isTopicSelected ->
            with(binding){
                submitButton.visibility = if(isTopicSelected) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        }
        binding.submitButton.setOnClickListener {
            buttonClicked()
        }
    }

    private fun buttonClicked() {
        recyclerViewAdapter?.selectedTopicId?.let {id ->
            val bundle = Bundle()
            bundle.putLong(TOPIC_ID_KEY, id)
            findNavController().apply {
                popBackStack()
                navigate(R.id.gameFragment, bundle)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}