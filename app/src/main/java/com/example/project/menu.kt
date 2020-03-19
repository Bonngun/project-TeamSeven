package com.example.project

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager
import org.json.JSONObject


class menu : Fragment() {
    // TODO: Rename and change types of parameters
    var PhotoURL : String = ""
    var Name : String = ""
    val tasks = tasks()
    val graph = graph()

    fun newInstance(url: String,name : String): menu {

        val menu = menu()
        val bundle = Bundle()
        bundle.putString("PhotoURL", url)
        bundle.putString("Name", name)
        menu.setArguments(bundle)

        return menu
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view:View = inflater.inflate(R.layout.fragment_menu, container, false)
        val jsonString : String = loadJsonFromAsset("team7.json", activity!!.baseContext).toString()
        val json = JSONObject(jsonString)
        val jsonArray = json.getJSONArray("team7")
        val recyclerView: RecyclerView = view.findViewById(R.id.recy_Layout)

        val fm = fragmentManager
        val transaction : FragmentTransaction = fm!!.beginTransaction()
        //ตั้งค่า Layout
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity!!.baseContext)

        recyclerView.layoutManager = layoutManager

        //ตั้งค่า Adapter
        val adapter = MyRecyclerAdapter(activity!!.baseContext,activity!!.supportFragmentManager,jsonArray)
        recyclerView.adapter = adapter

       val ivProfilePicture = view.findViewById(R.id.iv_profile) as ImageView
       val tvName = view.findViewById(R.id.tv_name) as TextView
       val login_button2 = view.findViewById(R.id.login_button2) as Button

       Glide.with(activity!!.baseContext)
           .load(PhotoURL)
            .into(ivProfilePicture)

        tvName.setText(Name)

       login_button2.setOnClickListener{

            LoginManager.getInstance().logOut()
            activity!!.supportFragmentManager.popBackStack()
        }
        val tasks_button:Button = view.findViewById(R.id.tasks)
        tasks_button.setOnClickListener{
                transaction.replace(R.id.login_button,tasks,"tasks")
                transaction.addToBackStack("tasks")
                transaction.commit()
        }
        val graph_button:Button = view.findViewById(R.id.graph)
        graph_button.setOnClickListener{
            transaction.replace(R.id.login_button,graph,"graph")
            transaction.addToBackStack("graph")
            transaction.commit()
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            PhotoURL = bundle.getString("PhotoURL").toString()
            Name = bundle.getString("Name").toString()

        }

    }

    private fun loadJsonFromAsset(filename: String, context: Context): String? {
        val json: String?

        try {
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: java.io.IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }


}




