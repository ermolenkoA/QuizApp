package com.example.quizapp.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.data.GameQuestion
import com.example.quizapp.databinding.FragmentResultsBinding
import com.example.quizapp.domain.ResultsViewModel
import com.example.quizapp.utils.Keys.GAME_QUESTION_KEY
import com.example.quizapp.utils.Keys.SPENT_TIME_KEY
import com.example.quizapp.utils.Keys.TOPIC_ID_KEY
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject


@AndroidEntryPoint
class ResultsFragment : Fragment() {
    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var viewModelAssistedFactory: ResultsViewModel.Factory
    private val viewModel: ResultsViewModel by viewModels {
        val list =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            requireArguments().getParcelableArrayList(GAME_QUESTION_KEY, GameQuestion::class.java)
        } else{
            requireArguments().getParcelableArrayList(GAME_QUESTION_KEY)
        }
        val spentTime = requireArguments().getLong(SPENT_TIME_KEY)
        val topicId = requireArguments().getLong(TOPIC_ID_KEY)
        ResultsViewModel.provideFactory(
            viewModelAssistedFactory,
            list!!.toList(),
            topicId,
            spentTime
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModelStore.clear()
        _binding = null
    }

    private fun setupViews() {
        setTime()
        setTotalScore()
        setButtonsListeners()
    }

    private fun setButtonsListeners() {
        with(binding) {
            tryAgainButton.setOnClickListener {
                navigateToGameFragment()
            }
            toMainScreenButton.setOnClickListener {
                findNavController().popBackStack()
            }
            shareResultsButton.setOnClickListener {
                clickedShareResultsButton()
            }
        }
    }

    private fun navigateToGameFragment() {
        findNavController().apply {
            popBackStack()
            navigate(
                R.id.gameFragment,
                viewModel.createBundleForGameFragment()
            )
        }
    }

    private fun clickedShareResultsButton() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            resources.getString(
                R.string.text_for_share,
                viewModel.correctAnswersCount,
                viewModel.numberOfQuestions
            ) + "\n(link)"
        )
        sendIntent.type = "text/plain"
        startActivity(
            Intent.createChooser(
                sendIntent,
                resources.getString(R.string.share_results_with)
            )
        )
    }

    private fun setTotalScore() {
        binding.answersTextView.text = resources.getString(
            R.string.correct_answers,
            viewModel.correctAnswersCount,
            viewModel.numberOfQuestions
        )
    }

    private fun setTime() {
        binding.timeTextView.text = resources.getString(
            R.string.results_time,
            viewModel.totalTime.get(Calendar.MINUTE),
            viewModel.totalTime.get(Calendar.SECOND)
        )
    }


}