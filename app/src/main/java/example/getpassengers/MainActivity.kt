/**
 * @author Preston Konkel
 * CS 3013 Mobile App Development
 * Profess Elliot Evans
 */

package example.getpassengers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private val listText: TextView
        get() = findViewById(R.id.show_list)
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val data = activityResult.data
            val count = (data?.getStringExtra("count") ?: "").toInt()
            for (i in 1..count) {
                val passenger = data?.getStringExtra("PASS$i") ?: ""
                listText.append(passenger)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate", "++++++++++ Entered onCreate ++++++++++")
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun getList(v : View) {
        startForResult.launch(
            Intent(this,
            GetPassengers::class.java)
        )
    }
}

class Passenger(private val fName:String, private val lName:String,
                private val phoneNumber: String) {
    override fun toString() : String {
        val s = "\n" + this.fName.trim() + " " + this.lName.trim() + " " + this.phoneNumber
        return s
    }
}