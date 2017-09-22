package pl.osmalek.bartek.uibootcamp

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvGrid.layoutManager = GridLayoutManager(this, 2)
        rvGrid.adapter = ImageAdapter { view, url ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("shared_id", view.transitionName)
                putExtra("url", url)
            }
            val bundle = ActivityOptions.makeSceneTransitionAnimation(this, view, view.transitionName).toBundle()
            startActivity(intent, bundle)
        }
    }
}

