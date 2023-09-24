package com.example.quizapp.ui

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.quizapp.MainActivity
import com.example.quizapp.R
import com.example.quizapp.data.Answers
import com.example.quizapp.data.GameQuestion
import com.example.quizapp.data.Option
import com.example.quizapp.databinding.FragmentGameBinding
import com.example.quizapp.domain.GameViewModel
import com.example.quizapp.utils.Keys
import com.example.quizapp.utils.Keys.TOPIC_ID_KEY
import com.example.quizapp.utils.OnSwipeTouchListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class GameFragment : Fragment() {

    companion object {
        private val CORRECT_COLOR = R.color.correct
        private val WRONG_COLOR = R.color.wrong
    }

    private var defaultColor = Color.BLACK
    private var defaultTintList: ColorStateList? = null

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var viewModelAssistedFactory: GameViewModel.Factory
    private val viewModel: GameViewModel by viewModels {
        val list = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            requireArguments().getParcelableArrayList(Keys.GAME_QUESTION_KEY, GameQuestion::class.java)
        } else{
            requireArguments().getParcelableArrayList(Keys.GAME_QUESTION_KEY)
        }
        GameViewModel.provideFactory(
            viewModelAssistedFactory,
            list,
            requireArguments().getLong(TOPIC_ID_KEY)
        )
    }

    private var checkedRadioButton: RadioButton? = null
        set(value) {
            with(binding){
                if(value == null) {
                    answersRadioGroup.clearCheck()
                    if (!viewModel.isGameEnded)
                        confirmButton.visibility = View.INVISIBLE
                } else {
                    value.isChecked = true
                    if (!viewModel.isGameEnded)
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
        defaultTintList = binding.optionARadioButton.buttonTintList
        defaultColor = binding.optionARadioButton.currentTextColor
        if(!viewModel.isGameEnded){
            (activity as? MainActivity)?.onGameFragment = true
            setupViews()
        } else {
            setupViewsForResults()
        }
        setupObservers()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? MainActivity)?.onGameFragment = false
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
    private fun setupViewsForResults() {
        setImageListener()
        setupOnSwipeListener()
        setRadioGroupListener()
        hideUselessViews()
        viewModel.prepareForResults()
    }

    private fun setupObservers() {
        viewModel.currentQuestion.observe(viewLifecycleOwner) {
            it?.let {question ->
                with(binding) {
                    if(viewModel.isGameEnded)
                        clearOptionColor()
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
            if(it == null) {
                endGame()
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
            chronometer.base = SystemClock.elapsedRealtime()
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
                        optionARadioButton -> Option.A
                        optionBRadioButton -> Option.B
                        optionCRadioButton -> Option.C
                        else -> Option.D
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
                answers.getAnswerText(Option.A),
            )
            optionBRadioButton.text = resources.getString(
                R.string.answerB,
                answers.getAnswerText(Option.B),
            )
            optionCRadioButton.text = resources.getString(
                R.string.answerC,
                answers.getAnswerText(Option.C),
            )
            optionDRadioButton.text = resources.getString(
                R.string.answerD,
                answers.getAnswerText(Option.D),
            )
            answers.answerOption?.let {currentAnswer ->
                viewModel.withoutAnimation = true
                if(!viewModel.isGameEnded){
                    selectRadioButton(currentAnswer)
                } else {
                    selectRadioButton(answers.correctOption)
                    paintOption(answers.correctOption, CORRECT_COLOR)
                    if (currentAnswer != answers.correctOption){
                        paintOption(currentAnswer, WRONG_COLOR)
                    }
                }
                return
            }
        }
    }


    private fun selectRadioButton(option: Option) {
        with(binding) {
            checkedRadioButton = when (option) {
                Option.A -> optionARadioButton
                Option.B -> optionBRadioButton
                Option.C -> optionCRadioButton
                Option.D -> optionDRadioButton
            }
        }
    }

    private fun paintOption(option: Option, color: Int) {
        val radioButton = with(binding) {
            when (option) {
                Option.A -> optionARadioButton
                Option.B -> optionBRadioButton
                Option.C -> optionCRadioButton
                Option.D -> optionDRadioButton
            }
        }
        paint(radioButton, color)
    }

    @SuppressLint("ResourceAsColor")
    private fun clearOptionColor() {
        with(binding) {
            arrayOf(optionARadioButton,
            optionBRadioButton,
            optionCRadioButton,
            optionDRadioButton).forEach {
                it.buttonTintList = defaultTintList
                it.setTextColor(defaultColor)
            }
        }
    }


    private fun endGame() {
        findNavController().apply {
            popBackStack()
            navigate(
                R.id.resultsFragment,
                viewModel.createEndGameBundle(SystemClock.elapsedRealtime())
            )
        }
    }

    private fun hideUselessViews() {
        with(binding){
            confirmButton.visibility = View.INVISIBLE
            chronometer.visibility = View.GONE
            with(binding) {
                arrayOf(optionARadioButton,
                    optionBRadioButton,
                    optionCRadioButton,
                    optionDRadioButton
                ).forEach {
                    it.isClickable = false
                }
            }
        }
    }

    private fun paint(radioButton: RadioButton, color: Int) {
        val status = if(color == CORRECT_COLOR){
            1
        } else {
            -1
        }
        radioButton.buttonTintList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked*status)
            ), intArrayOf(
                resources.getColor(color)
            )
        )
        radioButton.setTextColor(resources.getColor(color))
    }
}