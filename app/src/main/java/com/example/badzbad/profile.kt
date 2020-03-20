package com.example.badzbad


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager
import android.content.DialogInterface
import android.widget.Toast
import com.example.badzbad.R
import com.facebook.FacebookSdk.getApplicationContext


/**
 * A simple [Fragment] subclass.
 */
class profile : Fragment() {

    var PhotoURL : String = ""
    var Name : String = ""

    fun newInstance(url: String,name : String): profile {

        val profile = profile()
        val bundle = Bundle()
        bundle.putString("PhotoURL", url)
        bundle.putString("Name", name)
        profile.setArguments(bundle)

        return profile
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val ivProfilePicture = view.findViewById(R.id.iv_profile) as ImageView
        val tvName = view.findViewById(R.id.tv_name) as TextView
        val login_button2 = view.findViewById(R.id.login_button2) as Button

        Glide.with(activity!!.baseContext)
            .load(PhotoURL)
            .into(ivProfilePicture)

        tvName.setText(Name)

        login_button2.setOnClickListener{

            val builder = AlertDialog.Builder(context)
            builder.setMessage("ต้องการออกจากแก๊งค์จริงๆ หรอ ?")
            builder.setPositiveButton("ใช่", DialogInterface.OnClickListener { dialog, id ->
                 LoginManager.getInstance().logOut()
                activity!!.supportFragmentManager.popBackStack()

            })
            builder.setNegativeButton("ไม่", DialogInterface.OnClickListener { dialog, which ->
                //dialog.dismiss();
            })
            builder.show()


        }
        // Inflate the layout for this fragment
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

}