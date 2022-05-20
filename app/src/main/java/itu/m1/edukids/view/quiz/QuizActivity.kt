package itu.m1.edukids.view.quiz

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import itu.m1.edukids.R
import itu.m1.edukids.controller.QuizViewModel
import itu.m1.edukids.databinding.ActivityQuizBinding
import itu.m1.edukids.model.Reponse

class QuizActivity : AppCompatActivity() {
    private val viewModel: QuizViewModel by viewModels()
    private lateinit var binding: ActivityQuizBinding
    private lateinit var responseListFragment: QuizFragment
    private var currentResponse: Reponse? = null
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.toolbar

        responseListFragment = binding.quizResponseList.getFragment<QuizFragment>()
        responseListFragment.viewModel = viewModel

        viewModel.getQuiz { getData() }
        binding.confirmButton.setOnClickListener {
            verifyAnswer()
        }

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayShowTitleEnabled(false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.quiz_top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_close -> {
                finish()
            }
        }
        return true
    }

    private fun verifyAnswer() {
        val selectedIndex = responseListFragment.selectedAnswer
        if(selectedIndex == -1) return

        currentResponse = viewModel.quiz.value?.reponses?.get(selectedIndex)

        showDialog()
    }

    private fun showDialog() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
        val expression = view.findViewById<TextView>(R.id.validatoin_expression)
        val desc = view.findViewById<TextView>(R.id.description)

        currentResponse?.let {
            expression.text = getString(R.string.quiz_correct_expression, it.desc)
            desc.text = getString(R.string.quiz_correct_desc)

            if(it.correct == null || !it.correct) {
                desc.text = getString(R.string.quiz_incorrect_desc)

                val title = view.findViewById<TextView>(R.id.validation_title)
                title.text = getString(R.string.incorrect_answer_title)
                expression.setTextColor(Color.RED)

                val message = view.findViewById<TextView>(R.id.validation_message)
                message.text = getString(R.string.incorrect_answer_message)

                val image = view.findViewById<ImageView>(R.id.validation_img)
                image.setImageResource(R.drawable.fail_clipart)
            }
        }

        dialog.setCancelable(false)

        dialog.setContentView(view)
        dialog.show()
    }

    private fun getData() {
        viewModel.quiz?.value?.let {
            binding.quizTitle.text = it.question?.text
            binding.quizDescription.text = it.indice.desc
        }
    }
}