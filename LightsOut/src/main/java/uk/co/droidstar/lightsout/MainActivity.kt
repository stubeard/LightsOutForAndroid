package uk.co.droidstar.lightsout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import uk.co.droidstar.lightsout.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    companion object {
        var bar : ActionBar? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        bar = supportActionBar
        supportActionBar?.hide()
    }
}
