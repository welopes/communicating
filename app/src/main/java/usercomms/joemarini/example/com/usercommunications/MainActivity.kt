package usercomms.joemarini.example.com.usercommunications

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLaunchToast.setOnClickListener(this)
        btnLaunchSnackbar.setOnClickListener(this)
        btnLaunchDialog.setOnClickListener(this)
        btnLaunchNotification.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLaunchToast -> startActivity(Intent(this, ToastActivity::class.java))
            R.id.btnLaunchSnackbar -> startActivity(Intent(this, SnackbarActivity::class.java))
            R.id.btnLaunchDialog -> startActivity(Intent(this, DialogActivity::class.java))
            R.id.btnLaunchNotification -> startActivity(Intent(this, NotificationActivity::class.java))
        }
    }
}
