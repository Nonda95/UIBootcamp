package pl.osmalek.bartek.uibootcamp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.grid_item.view.*
import java.util.*

class ImageAdapter(private val listener: (ImageView, String) -> Unit) : RecyclerView.Adapter<ImageAdapter.ImageVH>() {
    private val images = listOf(
            "https://unsplash.it/600/400?image=10",
            "https://unsplash.it/600/400?image=11",
            "https://unsplash.it/600/400?image=12",
            "https://unsplash.it/600/400?image=13",
            "https://unsplash.it/600/400?image=14",
            "https://unsplash.it/600/400?image=15",
            "https://unsplash.it/600/400?image=16",
            "https://unsplash.it/600/400?image=17",
            "https://unsplash.it/600/400?image=18",
            "https://unsplash.it/600/400?image=19",
            "https://unsplash.it/600/400?image=110",
            "https://unsplash.it/600/400?image=111"
    )

    override fun onBindViewHolder(holder: ImageVH, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return ImageVH(view)
    }

    inner class ImageVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(url: String) {
            with(itemView) {
                Glide.with(ivImage)
                        .load(url)
                        .apply(RequestOptions().dontTransform())
                        .into(ivImage)
                ivImage.setOnClickListener {
                    ivImage.transitionName = UUID.randomUUID().toString()
                    listener(ivImage, url)
                }
            }
        }
    }

}