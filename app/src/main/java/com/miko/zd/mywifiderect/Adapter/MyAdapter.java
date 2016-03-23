package com.miko.zd.mywifiderect.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miko.zd.mywifiderect.R;

import java.util.HashMap;
import java.util.List;


/**
 * Created by zd on 2016/3/4.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private List<HashMap<String, String>> mList;

    /*
    * 设置回调接口，实现点击与长按
    * */

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);

        void OnItemLongClick(View view, int position);
    }

    public OnItemClickListener mOnItemClickListener;

    public void SetOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public MyAdapter(List<HashMap<String, String>> list) {
        super();
        this.mList = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.card_item,
                parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        holder.tvname.setText(mList.get(position).get("name"));
        holder.tvaddress.setText(mList.get(position).get("address"));

        if (mOnItemClickListener != null) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.OnItemClick(holder.itemView, position);
                }

            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.OnItemLongClick(holder.itemView, position);
                    return false;
                }
            });
        }

    }
    public void addData(int position,HashMap map) {
        mList.add(position,map);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }
    public void RefreshView(){
        for(int i=0;i<getItemCount();i++)
            removeData(i);
        for(int i=0;i<getItemCount();i++)
            addData(i,mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        public TextView tvname;
        public TextView tvaddress;

        public MyHolder(View View) {
            super(View);
            tvname = (TextView) View.findViewById(R.id.tv_name);
            tvaddress = (TextView) View.findViewById(R.id.tv_address);

        }
    }
}