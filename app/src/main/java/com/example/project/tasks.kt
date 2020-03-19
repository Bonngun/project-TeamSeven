package com.example.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONArray
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class tasks : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)
        // Inflate the layout for this fragment.


        val fm = fragmentManager
        val transaction : FragmentTransaction = fm!!.beginTransaction()

        val ShowData = ShowData()
        transaction.replace(R.id.show, ShowData,"fragment_ShowData")
        transaction.addToBackStack("fragment_ShowData")

        val DataRealtime = DataRealtime()
        transaction.replace(R.id.add, DataRealtime,"fragment_DataRealtime")
        transaction.addToBackStack("fragment_DataRealtime")


        transaction.commit()
        return view

    }


}
