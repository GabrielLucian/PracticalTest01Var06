package ro.pub.cs.systems.eim.practicaltest01var06

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    fun generateRandomNumberOrStar(): String {
        val randomValue = (1..4).random()
        return if (randomValue == 4) "*" else randomValue.toString()
    }

    var totalscore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val playbutton = findViewById<Button>(R.id.playbutton)
        val generatebutton = findViewById<Button>(R.id.generatebutton)

        val number1 = findViewById<TextView>(R.id.numar1)
        val number2 = findViewById<TextView>(R.id.numar2)
        val number3 = findViewById<TextView>(R.id.numar3)

        val check1 = findViewById<CheckBox>(R.id.check1)
        val check2 = findViewById<CheckBox>(R.id.check2)
        val check3 = findViewById<CheckBox>(R.id.check3)

        val activityResultsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val tempscore = data?.getIntExtra("message", 0)
                totalscore += tempscore!!
                Toast.makeText(this, "Total score: $totalscore", Toast.LENGTH_LONG).show()

                // if score is over 300 notify the service
                if (totalscore > 300) {
                    val intent = Intent().apply {
                        setComponent(ComponentName("ro.pub.cs.systems.eim.practicaltest01var06", "ro.pub.cs.systems.eim.practicaltest01var06.PracticalTest01Var06Service"))
                    }
                    startService(intent)
                }
            }
        }


        playbutton.setOnClickListener {
            // generate random number from 1 to 3 or * for each textview only if hold is unchecked
            if (!check1.isChecked) {
                number1.text = generateRandomNumberOrStar()
            }
            if (!check2.isChecked) {
                number2.text = generateRandomNumberOrStar()
            }
            if (!check3.isChecked) {
                number3.text = generateRandomNumberOrStar()
            }
        }

        generatebutton.setOnClickListener {
            // send to the second activity the numbers and the number of checked checkboxes
            val intent = Intent(this, PracticalTest01Var06SecondaryActivity::class.java)
            intent.putExtra("number1", number1.text.toString())
            intent.putExtra("number2", number2.text.toString())
            intent.putExtra("number3", number3.text.toString())
            intent.putExtra("checked", arrayOf(check1.isChecked, check2.isChecked, check3.isChecked).count { it })
            activityResultsLauncher.launch(intent)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("totalscore", totalscore)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        totalscore = savedInstanceState.getInt("totalscore")
        Toast.makeText(this, "Total score restored: $totalscore", Toast.LENGTH_LONG).show()
    }
}