package itu.m1.edukids.view.quiz

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import itu.m1.edukids.AppConst
import itu.m1.edukids.MainActivity
import itu.m1.edukids.R
import itu.m1.edukids.controller.QuizViewModel
import itu.m1.edukids.databinding.ActivityQuizBinding
import itu.m1.edukids.model.Notification
import itu.m1.edukids.model.Reponse

class QuizActivity : MainActivity() {
    private val viewModel: QuizViewModel by viewModels()
    private lateinit var binding: ActivityQuizBinding
    private lateinit var quizContentFragment: QuizContentFragment
    private var currentResponse: Reponse? = null
    private var count: Int = 0
    private var correctAnswerCount: Int = 0
    private lateinit var toolbar: Toolbar
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.toolbar
        progressBar = binding.quizProgress

        viewModel.getQuiz {
            attachFragment()
        }

        viewModel.selectedReponse.observe(this, Observer {
            currentResponse = it
        })

        viewModel.quiz.observe(this, Observer {
            loadQuiz()
        })

        binding.confirmButton.setOnClickListener {
            verifyAnswer()
        }

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayShowTitleEnabled(false)
            initProgress()
        }
    }

    private fun initProgress() {
        progressBar?.let {
            it.max = AppConst.QUIZ_COUNT
            it.progress = 0
            binding.progressCount.text = "0"
        }
    }

    private fun loadQuiz() {
        if(this::quizContentFragment.isInitialized) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.detach(quizContentFragment)
            transaction.commit()

            attachFragment()
        }
    }

    private fun attachFragment() {
        quizContentFragment = QuizContentFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.quiz_content_container, quizContentFragment).commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.quiz_top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_close -> {
                finish()
            }
        }
        return true
    }

    private fun verifyAnswer() {
        if (currentResponse == null) return

        if(currentResponse!!.correct == true) {
            correctAnswerCount++
        }

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

            if (it.correct == null || !it.correct) {
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

        var action: () -> Unit = {
            dialog.dismiss()
            nextQuiz()
            progressBar.progress = count
            binding.progressCount.text = "$count"
        }

        if(count == AppConst.QUIZ_COUNT - 1) {
            btnClose.text = getString(R.string.quiz_finish_button)
            action = {
                Notification.notify(this) {
                    it.setContentText("Félicitation, tu as eu $correctAnswerCount bonnes réponses sur ${AppConst.QUIZ_COUNT}")
                }
                finish()
            }
        }

        btnClose.setOnClickListener {
            action()
        }

        dialog.setCancelable(false)

        dialog.setContentView(view)
        dialog.show()
    }

    private fun nextQuiz() {
        viewModel.selectItem(null)
        count++
        viewModel.nextQuiz(count)
    }
}