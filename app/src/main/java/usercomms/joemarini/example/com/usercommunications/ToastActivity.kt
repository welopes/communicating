package usercomms.joemarini.example.com.usercommunications

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_toast.*
import kotlinx.android.synthetic.main.custom_toast_layout.*
import kotlinx.android.synthetic.main.custom_toast_layout.view.*

class ToastActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)

        btnShowToast.setOnClickListener(this)
        btnShowCustomToast.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnShowToast -> showToast()
            R.id.btnShowCustomToast -> showCustomToast()
        }
    }

    private fun showToast() {
        val toastDuration = if (rbShort.isChecked) Toast.LENGTH_SHORT else Toast.LENGTH_LONG

        // Create and show the toast message
        val toast = Toast.makeText(this, "This is a toast", toastDuration)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    private fun showCustomToast() {
        val toastDuration = if (rbShort.isChecked) Toast.LENGTH_SHORT else Toast.LENGTH_LONG

        // Get the custom layout and inflate it
        val inflater = LayoutInflater.from(this)
        val layout = inflater.inflate(R.layout.custom_toast_layout, customToastLayout)

        // Build a toast message that uses the custom layout
        val tv = layout.textContent
        tv.text = "This is a custom toast"
        Toast(applicationContext).run {
            duration = toastDuration
            setGravity(Gravity.BOTTOM or Gravity.START, 100, 100)
            view = layout
            show()
        }
//        val toast = Toast(applicationContext)
//        toast.duration = toastDuration
//        toast.setGravity(Gravity.BOTTOM or Gravity.START, 100, 100)
//        toast.view = layout
//        toast.show()
    }
}
