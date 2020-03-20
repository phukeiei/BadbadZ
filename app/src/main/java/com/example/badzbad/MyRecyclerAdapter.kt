import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.example.badzbad.R
import com.example.myapplogin_fragment.detail_product


class MyRecyclerAdapter(fragmentActivity: FragmentActivity, val dataSource: JSONArray) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {

    private val thiscontext : Context = fragmentActivity.baseContext
    private val thisActivity = fragmentActivity

    class Holder(view : View) : RecyclerView.ViewHolder(view) {
        private val View = view;

        lateinit var layout : LinearLayout
        lateinit var titleTextView: TextView
        lateinit var titleTextView2: TextView
        lateinit var detailTextView: TextView

        lateinit var image: ImageView

        fun Holder(){

            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            titleTextView = View.findViewById<View>(R.id.tv_name) as TextView
            //detailTextView = View.findViewById<View>(R.id.tv_description) as TextView
            image = View.findViewById<View>(R.id.imgV) as ImageView


        }

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {


        holder.Holder()
        holder.titleTextView.setText( dataSource.getJSONObject(position).getString("firstname").toString() )
       // holder.detailTextView.setText( dataSource.getJSONObject(position).getString("description").toString() )
         Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.image)

        holder.layout.setOnClickListener{
            Toast.makeText(thiscontext,holder.titleTextView.text.toString(),Toast.LENGTH_SHORT).show()
            val send_img:String = dataSource.getJSONObject(position).getString("image").toString()
            val send_des:String = dataSource.getJSONObject(position).getString("description").toString()
            val _detail_product = detail_product().newInstance(holder.titleTextView.text.toString(),send_des,send_img)
            val frgMng = thisActivity.supportFragmentManager
            val transaction : FragmentTransaction = frgMng!!.beginTransaction()
            transaction.replace(R.id.layout, _detail_product,"_detail_product")
            transaction.addToBackStack("_detail_product")
            transaction.commit()

        }




    }


}
