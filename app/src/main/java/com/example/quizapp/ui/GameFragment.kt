package com.example.quizapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.example.quizapp.MainActivity
import com.example.quizapp.R
import com.example.quizapp.data.Answers
import com.example.quizapp.databinding.FragmentGameBinding
import com.example.quizapp.domain.GameViewModel
import com.example.quizapp.utils.OnSwipeTouchListener
import com.example.quizapp.utils.Utils.TOPIC_ID_KEY
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@AndroidEntryPoint
class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var viewModelAssistedFactory: GameViewModel.Factory
    @FragmentScoped
    private val viewModel: GameViewModel by viewModels {
        GameViewModel.provideFactory(
            viewModelAssistedFactory,
            requireArguments().getLong(TOPIC_ID_KEY)
        )
    }
    private var checkedRadioButton: RadioButton? = null
        set(value) {
            with(binding){
                if(value == null) {
                    answersRadioGroup.clearCheck()
                    confirmButton.visibility = View.INVISIBLE
                } else {
                    value.isChecked = true
                    confirmButton.visibility = View.VISIBLE
                }
            }
            field = value
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.onGameFragment = true
        setupViews()
        setupObservers()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModelStore.clear()
        _binding = null
    }

    private fun setupViews() {
        startTimer()
        setImageListener()
        setRadioButtonsListeners()
        setupOnSwipeListener()
        setRadioGroupListener()
        setConfirmButtonClickListener()
    }

    private fun setupObservers() {
        viewModel.currentQuestion.observe(viewLifecycleOwner) {
            it?.let {question ->
                with(binding) {
                    questionTitleTextView.text = question.title
                    setupImage(question.image)
                    questionNumberTextView.text = resources.getString(
                        R.string.questions_count,
                        viewModel.currentQuestionIndex + 1,
                        viewModel.numberOfQuestions
                    )
                    setupAnswers(question.answers)
                }
            }
        }
    }

    private fun setImageListener() {
        with(binding) {
            questionImageView.setOnClickListener {
                imageConstraintLayout.visibility = View.VISIBLE
            }
            imageConstraintLayout.setOnClickListener {
                imageConstraintLayout.visibility = View.GONE
            }
        }
    }

    private fun startTimer() {
        with(binding) {
            chronometer.base = SystemClock.elapsedRealtime();
            viewModel.baseTime = chronometer.base
            chronometer.start()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupOnSwipeListener() {
        binding.root.setOnTouchListener(object: OnSwipeTouchListener(requireContext()){
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                viewModel.toNextQuestion()
            }
            override fun onSwipeRight() {
                super.onSwipeRight()
                viewModel.toPreviousQuestion()
            }
        })
    }

    private fun setRadioButtonsListeners() {
        with(binding) {
            optionARadioButton.setOnClickListener{
                radioButtonClicked(it as RadioButton)
            }
            optionBRadioButton.setOnClickListener{
                radioButtonClicked(it as RadioButton)
            }
            optionCRadioButton.setOnClickListener{
                radioButtonClicked(it as RadioButton)
            }
            optionDRadioButton.setOnClickListener{
                radioButtonClicked(it as RadioButton)
            }
        }
    }

    private fun radioButtonClicked(button: RadioButton) {
        with(binding){
            if(checkedRadioButton == button) {
                viewModel.choseOption(null)
                checkedRadioButton = null
            } else {
                checkedRadioButton = button
                viewModel.choseOption(
                    when(button) {
                        optionARadioButton -> Answers.Option.A
                        optionBRadioButton -> Answers.Option.B
                        optionCRadioButton -> Answers.Option.C
                        else -> Answers.Option.D
                    }
                )
            }
        }
    }

    private fun setRadioGroupListener() {
        binding.answersRadioGroup.setOnCheckedChangeListener { radioGroup, _ ->
            if (viewModel.withoutAnimation) {
                radioGroup.jumpDrawablesToCurrentState()
                viewModel.withoutAnimation = false
            }
        }
    }

    private fun setConfirmButtonClickListener() {
        binding.confirmButton.setOnClickListener {
            viewModel.confirmAnswer()
        }
    }

    private fun setupImage(imageRef: String?) {
        with(binding){
            if (imageRef != null) {
                Glide.with(requireContext())
                    .load(imageRef)
                    .apply{
                        into(questionImageView)
                        into(fullImageView)
                    }
                questionImageView.visibility= View.VISIBLE
            } else {
                questionImageView.visibility= View.GONE
            }
        }
    }

    private fun setupAnswers(answers: Answers) {
        with(binding){
            checkedRadioButton = null
            optionARadioButton.text = resources.getString(
                R.string.answerA,
                answers.getAnswerText(Answers.Option.A),
            )
            optionBRadioButton.text = resources.getString(
                R.string.answerB,
                answers.getAnswerText(Answers.Option.B),
            )
            optionCRadioButton.text = resources.getString(
                R.string.answerC,
                answers.getAnswerText(Answers.Option.C),
            )
            optionDRadioButton.text = resources.getString(
                R.string.answerD,
                answers.getAnswerText(Answers.Option.D),
            )
            answers.getCurrentAnswer()?.let {currentAnswer ->
                viewModel.withoutAnimation = true
                selectRadioButton(currentAnswer)
                return
            }
        }
    }


    private fun selectRadioButton(option: Answers.Option) {
        with(binding) {
            checkedRadioButton = when (option) {
                Answers.Option.A -> optionARadioButton
                Answers.Option.B -> optionBRadioButton
                Answers.Option.C -> optionCRadioButton
                Answers.Option.D -> optionDRadioButton
            }
        }
    }

}