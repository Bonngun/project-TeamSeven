package com.example.project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import androidx.fragment.app.FragmentManager
import org.json.JSONArray
import androidx.fragment.app.FragmentTransaction
import org.json.JSONObject

class MyRecyclerAdapter(
    context: Context,
    fm: FragmentManager,
    val dataSource: JSONArray
) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {

    private val thiscontext : Context = context
    private val fragment: FragmentManager = fm

    class Holder(view : View) : RecyclerView.ViewHolder(view) {

        private val View = view;
        lateinit var layout : LinearLayout
        lateinit var firstnameTextView: TextView
        lateinit var lastnameTextView: TextView
        lateinit var positionTextView: TextView
        lateinit var htmlTextView: TextView
        lateinit var cssTextView: TextView
        lateinit var javascritpTextView: TextView
        lateinit var phpTextView: TextView
        lateinit var sqlTextView: TextView

        lateinit var image: ImageView

        fun Holder(){
            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            firstnameTextView = View.findViewById<View>(R.id.firstname) as TextView
            lastnameTextView = View.findViewById<View>(R.id.lastname) as TextView
            positionTextView = View.findViewById<View>(R.id.position_name) as TextView

            image = View.findViewById<View>(R.id.image) as ImageView
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_data, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.Holder()

        holder.firstnameTextView.setText( dataSource.getJSONObject(position).getString("firstname").toString() )
        holder.lastnameTextView.setText( dataSource.getJSONObject(position).getString("lastname").toString() )
        holder.positionTextView.setText( dataSource.getJSONObject(position).getString("position_name").toString() )

        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.image)

        holder.layout.setOnClickListener(){

            val image:String = dataSource.getJSONObject(position).getString("image").toString()
            val firstname:String = dataSource.getJSONObject(position).getString("firstname").toString()
            val lastname:String = dataSource.getJSONObject(position).getString("lastname").toString()
            val html:String = dataSource.getJSONObject(position).getString("html").toString()
            val css:String = dataSource.getJSONObject(position).getString("css").toString()
            val javascript:String = dataSource.getJSONObject(position).getString("javascript").toString()
            val php:String = dataSource.getJSONObject(position).getString("php").toString()
            val sql:String = dataSource.getJSONObject(position).getString("sql").toString()
            val position:String = dataSource.getJSONObject(position).getString("position_name").toString()


            val detail = detail().newInstance(image,firstname,lastname,position,html,css,javascript,php,sql)
            val transaction : FragmentTransaction = fragment!!.beginTransaction()
            transaction.replace(com.example.project.R.id.login_button,detail,"detail")
            transaction.addToBackStack("detail")
            transaction.commit()
        }
    }
}
