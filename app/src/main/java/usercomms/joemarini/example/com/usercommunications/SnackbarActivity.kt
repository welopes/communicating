package usercomms.joemarini.example.com.usercommunications

import android.graphics.Color

import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_snackbar.*

class SnackbarActivity : AppCompatActivity(), View.OnClickListener {

//    private val TAG = "AUC-SNACKBAR"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snackbar)

        btnShowSnackbar.setOnClickListener(this)
        btnShowActionSnackbar.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnShowSnackbar -> showSnackbar()
            R.id.btnShowActionSnackbar -> showActionSnackbar()
        }
    }

    private fun showSnackbar() {
        // Create and show the snackbar
        val snackbar = Snackbar.make(myCoordinatorLayout,
                "This is a snackbar", Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    private fun showActionSnackbar() {
        // Create a snackbar that has an action item
        val snackbar = Snackbar.make(myCoordinatorLayout,
                "This is a snackbar", Snackbar.LENGTH_LONG)
        snackbar.setAction("Very Cool") {
            Toast.makeText(applicationContext, "Snackbar Action Click!",
                    Toast.LENGTH_SHORT).show()
        }
        snackbar.setActionTextColor(Color.RED)
        snackbar.show()
    }

}
