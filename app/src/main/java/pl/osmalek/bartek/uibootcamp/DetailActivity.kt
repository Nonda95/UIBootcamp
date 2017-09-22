package pl.osmalek.bartek.uibootcamp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        postponeEnterTransition()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val sharedId = intent.getStringExtra("shared_id")
        ivImage.transitionName = sharedId
        val url = intent.getStringExtra("url")
        Glide.with(ivImage)
                .load(url)
                .apply(RequestOptions().dontTransform())
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                })
                .into(ivImage)
        setUpListeners()
    }

    private fun setUpListeners() {
        val upButtons = listOf(
                ivUpAuthor,
                ivUpCategory,
                ivUpDate,
                ivUpDesc
        )
        for (upButton in upButtons) {
            upButton.setOnClickListener {
                val constraintSet = ConstraintSet()
                constraintSet.clone(clContainer)
                val oldTop = (it.layoutParams as ConstraintLayout.LayoutParams).topToBottom
                constraintSet.connect(it.id, ConstraintSet.TOP, R.id.guideline, ConstraintSet.BOTTOM)
            }
        }
    }
}