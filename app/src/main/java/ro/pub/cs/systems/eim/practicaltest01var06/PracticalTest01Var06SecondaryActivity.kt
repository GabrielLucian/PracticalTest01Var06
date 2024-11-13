package ro.pub.cs.systems.eim.practicaltest01var06

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var06SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var06_secondary)

        val score = findViewById<TextView>(R.id.score)
        val okbutton = findViewById<TextView>(R.id.okbutton)

        val number1 = intent.getStringExtra("number1")
        val number2 = intent.getStringExtra("number2")
        val number3 = intent.getStringExtra("number3")
        val checked = intent.getIntExtra("checked", 0)


        var tempscore = 0
        // check if all numbers are the same (* is considered any number)
        if (number1 == number2 && number2 == number3) {
            tempscore = 3 - checked
        } else if (number1 == "*" && number2 == "*" && number3 == "*") {
            tempscore = 3 - checked
        } else if (number1 == number2 && number3 == "*") {
            tempscore = 3 - checked
        } else if (number1 == number3 && number2 == "*") {
            tempscore = 3 - checked
        } else if (number2 == number3 && number1 == "*") {
            tempscore = 3 - checked
        } else {
            tempscore = -1
        }

        if (tempscore == -1) {
            score.text = "Nothing"
            tempscore = 0
        } else if (tempscore == 0) {
            score.text = "Nothing"
            tempscore = 0
        } else if (tempscore == 1) {
            score.text = "Gained : 10"
            tempscore = 10
        } else if (tempscore == 2) {
            score.text = "Gained : 50"
            tempscore = 50
        } else if (tempscore == 3) {
            score.text = "Gained : 100"
            tempscore = 100
        }


        okbutton.setOnClickListener {
            setResult(RESULT_OK, intent.putExtra("message", tempscore))
            finish()
        }

    }
}