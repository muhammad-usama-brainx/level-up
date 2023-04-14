package adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.levelup.R
import models.Notification

class Notification_Recycler_Adapter(private val notificationsList: MutableList<Notification>,private val context: Context) : RecyclerView.Adapter<Notification_Recycler_Adapter.CardViewHolder>() {

    inner class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var role: TextView = view.findViewById(R.id.role)
        var title: TextView = view.findViewById(R.id.title)
        var message: TextView = view.findViewById(R.id.message)
        var time: TextView = view.findViewById(R.id.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.notification_card,parent,false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
       return notificationsList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.name.text = notificationsList[position].name
        holder.role.text = notificationsList[position].role
        holder.title.text = notificationsList[position].title
        holder.message.text = notificationsList[position].message
        holder.time.text = notificationsList[position].time
    }


}