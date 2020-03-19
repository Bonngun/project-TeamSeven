package com.example.project


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class Edit : Fragment() {

    var Str_key = ""
    var Str_username = ""
    var Str_text = ""

    fun newInstance(key: String,username: String,text: String): Edit {

        val fragment = Edit()
        val bundle = Bundle()
        bundle.putString("key", key)
        bundle.putString("username", username)
        bundle.putString("text", text)
        fragment.setArguments(bundle)

        return fragment
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_edit, container, false)
        // Inflate the layout for this fragment

        val edit_text = view.findViewById<TextView>(R.id.edit_text)
        val sendData = view.findViewById<Button>(R.id.sendData)
        val delData = view.findViewById<Button>(R.id.delData)


        sendData.setOnClickListener {
            val mRootRef = FirebaseDatabase.getInstance().getReference()
            val mMessagesRef = mRootRef.child("data")
            mMessagesRef.child(Str_key).child("text").setValue(edit_text.text.toString())
            val fm: FragmentManager = activity!!.getSupportFragmentManager()
            fm.popBackStack("fragment_Edit", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        delData.setOnClickListener {
            val mRootRef = FirebaseDatabase.getInstance().getReference()
            val mMessagesRef = mRootRef.child("data").child(Str_key)
            mMessagesRef.setValue(null)
            val fm: FragmentManager = activity!!.getSupportFragmentManager()
            fm.popBackStack("fragment_Edit", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        return view

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            Str_key = bundle.getString("key").toString()
            Str_username = bundle.getString("username").toString()
            Str_text = bundle.getString("text").toString()

        }

    }

}
