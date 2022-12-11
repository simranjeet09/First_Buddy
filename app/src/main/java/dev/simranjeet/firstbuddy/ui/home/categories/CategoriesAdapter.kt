package dev.simranjeet.firstbuddy.ui.home.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import dev.simranjeet.firstbuddy.R
import dev.simranjeet.firstbuddy.data.models.DataModal


class CategoriesAdapter() :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    var categories: List<DataModal> = listOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.title)
        }
    }
    fun submitList( list:List<DataModal>){
        this.categories= list
        notifyDataSetChanged()

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.categories_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = categories[position].title
        viewHolder.itemView.setOnClickListener{
            var bundle = Bundle()
            bundle.putString("titleMain",categories[position].title )
            bundle.putString("titleToFetch",categories[position].title )
            Navigation.findNavController(it).navigate(R.id.action_home_to_post_fragment, bundle );


        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = categories.size

}
