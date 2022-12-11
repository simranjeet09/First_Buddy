package dev.simranjeet.firstbuddy.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.simranjeet.firstbuddy.R
import dev.simranjeet.firstbuddy.data.models.PostModel


class PostAdapter :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    var categories: List<PostModel> = listOf()
    var email = MutableLiveData<String>("")

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val desc: TextView
        val timeView: TextView
        val image: ImageView
        val delete: ImageView

        init {
            textView = view.findViewById(R.id.title)
            desc = view.findViewById(R.id.desc)
            timeView = view.findViewById(R.id.time)
            delete = view.findViewById(R.id.delete)
            image = view.findViewById(R.id.image)
        }
    }

    fun submitList(list: List<PostModel>) {
        this.categories = list
        notifyDataSetChanged()

    }

    fun setEmail(str: String) {
        this.email.value = str
        notifyDataSetChanged()

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.post_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = categories[position].title
        viewHolder.desc.text = categories[position].description
        viewHolder.timeView.text = categories[position].createdat.toDate().toString()
       /* if (categories[position].email == email.value) {
            viewHolder.delete.visibility = View.VISIBLE
        } else {
            viewHolder.delete.visibility = View.GONE
        }*/
        if (!categories[position].image.isNullOrBlank()) {
            viewHolder.image.visibility = View.VISIBLE
            Picasso.get().load(categories[position].image).into(viewHolder.image)

        } else {
            viewHolder.image.visibility = View.GONE
        }



    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = categories.size

}
