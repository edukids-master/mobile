package itu.m1.edukids.view.ui.math

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import itu.m1.edukids.MainActivity
import itu.m1.edukids.R
import itu.m1.edukids.databinding.ActivityMathQuizBinding
import itu.m1.edukids.model.Notification

class MathQuiz : MainActivity() {
    companion object{
        private var resultatCorrect = 0
    }
    private lateinit var binding: ActivityMathQuizBinding
    private lateinit var mathFragment: MathResponseFragment
    private lateinit var question: String
    private lateinit var reponseList: List<Int>
    private var correctAnswer: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMathQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mathFragment = binding.mathAnswerContainer.getFragment<MathResponseFragment>()

        createQuiz()
        binding.confirmButton.setOnClickListener {
            verifyAnswer()
            if(resultatCorrect >= 2){
                val notification : Notification = Notification()
                notification.createNotificationChannel(this,this,"EduKids","Félicitation 😊🥳")
            }
        }
    }

    private fun verifyAnswer() {
        if(mathFragment.selectedAnswer == -1) return

        val answer = reponseList[mathFragment.selectedAnswer]
        val isCorrect = answer == correctAnswer
        
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
        val expression = view.findViewById<TextView>(R.id.validatoin_expression)

        if(!isCorrect) {
            val desc = view.findViewById<TextView>(R.id.description)
            desc.visibility = View.GONE

            val title = view.findViewById<TextView>(R.id.validation_title)
            title.text = getString(R.string.incorrect_answer_title)
            expression.setTextColor(Color.RED)

            val message = view.findViewById<TextView>(R.id.validation_message)
            message.text = getString(R.string.incorrect_answer_message)

            val image = view.findViewById<ImageView>(R.id.validation_img)
            image.setImageResource(R.drawable.fail_clipart)
        }

        if(isCorrect) resultatCorrect++

        expression.text = getString(R.string.math_question_answer, question, answer)

        btnClose.setOnClickListener {
            finish()
            startActivity(this.intent)
        }

        dialog.setCancelable(false)

        dialog.setContentView(view)
        dialog.show()
    }

    private fun createQuiz() {
        val numberRange = 1..9
        val operationList = listOf<String>("+", "-", "x", "÷")
        var firstMember = numberRange.shuffled().first()
        var secondMember = numberRange.shuffled().first()
        val operation = operationList.shuffled().first()

        if (operation == "÷" && firstMember % secondMember != 0) {
            secondMember = numberRange.filter { it -> firstMember % it == 0 }.shuffled().first()
        }
        if (operation == "-" && firstMember < secondMember) {
            val temp = firstMember
            firstMember = secondMember
            secondMember = temp
        }

        val expression = getString(R.string.math_question, firstMember, operation, secondMember)
        binding.mathQuestion.text = "$expression "
        question = expression

        reponseList = createReponseList(firstMember, operation, secondMember)
        mathFragment.reponseList = reponseList
    }

    private fun createReponseList(
        firstMember: Int,
        operation: String,
        secondMember: Int
    ): List<Int> {
        val reponseList = mutableListOf<Int>()
        val numberRange = 1..81
        correctAnswer = when (operation) {
            "+" -> firstMember + secondMember
            "-" -> firstMember - secondMember
            "x" -> firstMember * secondMember
            else -> firstMember / secondMember
        }

        reponseList += correctAnswer

        for (n in 1..3) {
            var randomAnswer: Int
            do {
                randomAnswer = numberRange.shuffled().first()
            } while (randomAnswer == correctAnswer)
            reponseList += randomAnswer
        }

        return reponseList.shuffled();
    }
}