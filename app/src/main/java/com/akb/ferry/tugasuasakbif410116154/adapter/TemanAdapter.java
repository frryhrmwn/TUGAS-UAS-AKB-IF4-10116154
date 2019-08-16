package com.akb.ferry.tugasuasakbif410116154.adapter;

/*
    NIM : 10116154
    Nama : Ferry Hermawan
    Kelas : IF4
    Tgl Pengerjaan : 14 Agustus 2019
*/

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akb.ferry.tugasuasakbif410116154.R;
import com.akb.ferry.tugasuasakbif410116154.contract.TemanContract;
import com.akb.ferry.tugasuasakbif410116154.model.data.db.entity.Teman;

import java.util.ArrayList;
import java.util.List;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.ViewHolder> {

    private List<Teman> mValues;
    private TemanContract.OnItemClickListener mOnItemClickListener;

    public TemanAdapter(TemanContract.OnItemClickListener onItemClickListener) {
        mValues = new ArrayList<>();
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teman, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nimTextView.setText(mValues.get(position).nim);
        holder.namaTextView.setText(mValues.get(position).nama);
        holder.kelasTextView.setText(mValues.get(position).kelas);

        holder.teleponTextView.setText(mValues.get(position).telepon);
        holder.emailTextView.setText(holder.mItem.email);
        holder.sosmedTextView.setText(mValues.get(position).sosmed);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.clickItem(holder.mItem);
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnItemClickListener.clickLongItem(holder.mItem);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setValues(List<Teman> values) {
        mValues = values;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nimTextView;
        public final TextView namaTextView;
        public final TextView kelasTextView;
        public final TextView teleponTextView;
        public final TextView emailTextView;
        public final TextView sosmedTextView;
        public Teman mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nimTextView = (TextView) view.findViewById(R.id.nimTextView);
            namaTextView = (TextView) view.findViewById(R.id.namaTextView);
            kelasTextView = (TextView) view.findViewById(R.id.kelasTextView);
            teleponTextView = (TextView) view.findViewById(R.id.teleponTextView);
            emailTextView = (TextView) view.findViewById(R.id.emailTextView);
            sosmedTextView = (TextView) view.findViewById(R.id.sosmedTextView);

        }
    }
}
