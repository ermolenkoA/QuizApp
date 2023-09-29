package com.example.quizapp.ui

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.animation.addListener
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
        val list = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
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

    private var currentAnimator: Animator? = null

    companion object {
        private const val defaultDelay = 300L
        private const val smallUpdateDuration = 70L
        private const val maxUpdateDuration = 2000L
        private const val maxElementForUpdate = maxUpdateDuration/smallUpdateDuration
        private const val textViewFadeDuration = 300L
        private const val buttonFadeDuration = 700L
        private const val imageFadeDuration = 500L
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
        if(viewModel.animationEnded) {
            setupViews()
        } else {
            setupViewsWithAnimation()
            viewModel.animationEnded = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModelStore.clear()
        _binding = null
    }

    private fun setupViews() {
        binding.timeTextView.text = resources.getString(
            R.string.results_time,
            viewModel.totalTime.get(Calendar.SECOND),
            viewModel.totalTime.get(Calendar.MINUTE)
        )
        binding.answersTextView.text = resources.getString(
            R.string.correct_answers,
            viewModel.correctAnswersCount,
            viewModel.numberOfQuestions
        )
        setImage()
        setButtonsListeners()
    }

    private fun setupViewsWithAnimation() {
        hideViews()
        setTime()
        setButtonsListeners()
    }

    private fun hideViews() {
        with(binding) {
            arrayOf(
                timeTextView,
                answersTextView,
                viewResultsButton,
                gradeImageView,
                buttonsLinearLayout)
                .forEach {
                    it.visibility = View.INVISIBLE
                }
        }
    }

    private fun setButtonsListeners() {
        with(binding) {
            viewResultsButton.setOnClickListener {
                navigateToGameFragmentForResults()
            }
            tryAgainButton.setOnClickListener {
                navigateToGameFragment()
            }
            toMainScreenButton.setOnClickListener {
                findNavController().popBackStack()
                viewModelStore.clear()
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

    private fun navigateToGameFragmentForResults() {
        findNavController().navigate(
            R.id.gameFragment,
            viewModel.createBundleForGameFragmentResults()
        )

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
           0,
            viewModel.numberOfQuestions
        )
        animateResultsTextView()
    }

    private fun setTime() {
        binding.timeTextView.text = resources.getString(
            R.string.results_time,
            0,
            0
        )
        animateTimeTextView()
    }

    private fun setImage() {
        binding.gradeImageView.setImageResource(
            with(viewModel){
                when(correctAnswersCount.toDouble()/numberOfQuestions) {
                    in 0.0..0.09 -> R.drawable.grade_f_img
                    in 0.1..0.29 -> R.drawable.grade_e_img
                    in 0.3..0.49 -> R.drawable.grade_d_img
                    in 0.5..0.69 -> R.drawable.grade_c_img
                    in 0.7..0.89 -> R.drawable.grade_b_img
                    in 0.9..0.99 -> R.drawable.grade_a_img
                    else -> R.drawable.grade_a_plus_img
                }
            }
        )
    }

    private fun animateTimeTextView() {
        val textView = binding.timeTextView
        fadeIn(textView, 200) {
            val totalSec = viewModel.totalTime.get(Calendar.SECOND) +
                    viewModel.totalTime.get(Calendar.MINUTE)*60

            ValueAnimator.ofInt(0, totalSec).apply {
                duration = if(totalSec < maxElementForUpdate)
                    totalSec * smallUpdateDuration
                else
                    maxUpdateDuration
                addUpdateListener {
                    val sec = animatedValue as Int % 60
                    val min = animatedValue as Int / 60
                    textView.text = resources.getString(
                        R.string.results_time,
                        min,
                        sec
                    )
                }
                addListener(object: AnimatorListener {
                    override fun onAnimationStart(p0: Animator) {}
                    override fun onAnimationCancel(p0: Animator) {}
                    override fun onAnimationRepeat(p0: Animator) {}
                    override fun onAnimationEnd(p0: Animator) {
                        setTotalScore()
                    }
                })
                start()
            }
        }
    }

    private fun animateResultsTextView() {
        val textView = binding.answersTextView
        fadeIn(textView, textViewFadeDuration){
            ValueAnimator.ofInt(0, viewModel.correctAnswersCount).apply {
                duration = if(viewModel.correctAnswersCount < maxElementForUpdate)
                    viewModel.correctAnswersCount * smallUpdateDuration
                else
                    maxUpdateDuration
                interpolator = LinearInterpolator()
                addUpdateListener {
                    binding.answersTextView.text = resources.getString(
                        R.string.correct_answers,
                        animatedValue as Int,
                        viewModel.numberOfQuestions
                    )
                }
                addListener(object: AnimatorListener {
                    override fun onAnimationStart(p0: Animator) {}
                    override fun onAnimationCancel(p0: Animator) {}
                    override fun onAnimationRepeat(p0: Animator) {}
                    override fun onAnimationEnd(p0: Animator) {
                        setImageView()
                    }
                })
                start()
            }
        }
    }


    private fun setImageView() {
        val imageView = binding.gradeImageView
        setImage()
        changeScale(imageView){
            animateButtons()
        }

    }

    private fun animateButtons() {
        with(binding){
            viewResultsButton.isClickable = false
            buttonsLinearLayout.isClickable = false
            flyFromLeftToRight(viewResultsButton){
                viewResultsButton.isClickable = true
            }
            fadeIn(buttonsLinearLayout, buttonFadeDuration){
                buttonsLinearLayout.isClickable = true
            }
        }
    }

    private fun changeScale(view: View, conclusion: () -> Unit) {
        view.apply {
            scaleX = 5f
            scaleY = 5f
        }
        ObjectAnimator.ofFloat(
            view,
            "scaleY",
            1f
        ).apply {
            duration = imageFadeDuration
            startDelay = defaultDelay
            start()
        }
        ObjectAnimator.ofFloat(
            view,
            "scaleX",
            1f
        ).apply {
            duration = imageFadeDuration
            startDelay = defaultDelay
            addListener(object: AnimatorListener {
                override fun onAnimationStart(p0: Animator) {
                    view.visibility = View.VISIBLE
                }
                override fun onAnimationCancel(p0: Animator) {}
                override fun onAnimationRepeat(p0: Animator) {}
                override fun onAnimationEnd(p0: Animator) {
                    conclusion()
                }
            })
            start()
        }
    }

    private fun flyFromLeftToRight(view: View, conclusion: () -> Unit) {
        view.apply {
            translationX = -(parent as View).width.toFloat()
            visibility = View.VISIBLE
        }
        ObjectAnimator.ofFloat(
            view,
            "translationX",
            0f
        ).apply {
            duration = buttonFadeDuration
            addListener(object: AnimatorListener {
                override fun onAnimationStart(p0: Animator) {}
                override fun onAnimationCancel(p0: Animator) {}
                override fun onAnimationRepeat(p0: Animator) {}
                override fun onAnimationEnd(p0: Animator) {
                    conclusion()
                }
            })
            start()
        }
    }

    private fun fadeIn(view: View, long: Long, conclusion: () -> Unit) {
        view.apply {
            alpha = 0f
            visibility = View.VISIBLE
        }
        ObjectAnimator.ofFloat(
            view,
            "alpha",
            1f
        ).apply {
            duration = long
            addListener(object: AnimatorListener {
                override fun onAnimationStart(p0: Animator) {}
                override fun onAnimationCancel(p0: Animator) {}
                override fun onAnimationRepeat(p0: Animator) {}
                override fun onAnimationEnd(p0: Animator) {
                    conclusion()
                }
            })
            start()
        }
    }
}