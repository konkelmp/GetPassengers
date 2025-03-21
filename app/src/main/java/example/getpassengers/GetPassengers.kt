/**
 * @author Preston Konkel
 * CS 3013 Mobile App Development
 * Profess Elliot Evans
 */

package example.getpassengers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GetPassengers : AppCompatActivity() {

    private var passList : MutableList<Passenger> = ArrayList()
    private val textFirst : EditText
        get() = findViewById(R.id.first_name)
    private val textLast : EditText
        get() = findViewById(R.id.last_name)
    private val textPhone : EditText
        get() = findViewById(R.id.phone_number)
    private val textPut : TextView
        get() = findViewById(R.id.accum_list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_passengers)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun enterPassenger(v : View) {
        val firstName = textFirst.text.toString()
        val lastName = textLast.text.toString()
        val phoneNumber = textPhone.text.toString()
        val passengerInfo = Passenger(firstName, lastName, phoneNumber)
        textPut.append(passengerInfo.toString())
        passList.add(passengerInfo)
        textFirst.setText("")
        textLast.setText("")
        textPhone.setText("")
    }

    fun backToMain(v : View) {
        Intent().let { passengerInfoIntent ->
            val count = passList.size
            var id = 1
            for (p in passList) {
                passengerInfoIntent.putExtra("PASS$id", p.toString())
                id++
            }
            passengerInfoIntent.putExtra("count", count.toString())
            setResult(Activity.RESULT_OK, passengerInfoIntent)
            finish()
        }
    }
}