package com.example.scmask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.util.ArrayList


class recyclerAdapter(storeArrayList: ArrayList<Stores>, context: Context?) : RecyclerView.Adapter<recyclerAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return storeArrayList.size
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var storeArrayList: ArrayList<Stores> //학교 앞 원룸 List
    private var context : Context

    init {
        this.storeArrayList = ArrayList<Stores>()
        this.storeArrayList.addAll(storeArrayList)
        this.context = context!!

    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        internal var textViewStoreName: TextView? = view.findViewById(R.id.store_name_textview) as TextView
        internal var textViewRemainMask: TextView? = view.findViewById(R.id.mask_count_result_textview)
        internal var textViewUpdateDate: TextView? = view.findViewById(R.id.update_time_result_textview)
        internal var textViewAddress: TextView? = view.findViewById(R.id.store_address) as TextView

        internal fun onBind(store: Stores) {
            if (store.name == "")
                textViewStoreName!!.setText("-")
            else
                textViewStoreName!!.setText(store.name)
            if ( store.addr == "")
                textViewAddress!!.setText("-")
            else
                textViewAddress!!.setText("[${store.addr}]")
            if (store.remain_stat == "")
                textViewRemainMask!!.setText("-")
            if (store.remain_stat == "plenty")
                textViewRemainMask!!.setText("100개이상")

            if (store.remain_stat == "some")
                textViewRemainMask!!.setText("30개~100개")

            if (store.remain_stat == "few")
                textViewRemainMask!!.setText("2개~30개")

            if (store.remain_stat == "empty")
                textViewRemainMask!!.setText("1개이하")

            if (store.remain_stat == "break")
                textViewRemainMask!!.setText("판매중지")
            if (store.stock_at == "")
                textViewUpdateDate!!.setText("X")
            else
                textViewUpdateDate!!.setText(store.stock_at)
        }

    }

    /**
     * ViewHolder와 Layout 파일을 연결해주는 함수
     *
     * @param parent
     * @param viewType
     * @return
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyclerAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return recyclerAdapter.ViewHolder(itemView)
    }

    /**
     * ViewHolder에 들어갈 데이터를 연결해주는 함수
     *
     * @param holder   뷰홀더
     * @param position 아이템을 선택했을때 위치
     */

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.onBind(storeArrayList[position])
        val land = storeArrayList[position]
        /*
        holder.view.setOnClickListener {
            val intent = Intent(context, LandDetailActivity::class.java)
            if (position != RecyclerView.NO_POSITION) {
                intent.putExtra("Land_ID", land.getId())
                intent.putExtra("Land_latitude", land.getLatitude())
                intent.putExtra("Land_longitude", land.getLongitude())
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "복덕방을 찾을수 없습니다", Toast.LENGTH_LONG).show()
            }
        }

         */


    }


}