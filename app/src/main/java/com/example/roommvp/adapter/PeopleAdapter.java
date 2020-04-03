package com.example.roommvp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roommvp.R;
import com.example.roommvp.database.entity.Person;
import com.example.roommvp.view.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    private List<Person> personList;
    private OnItemClickListener onItemClickListener;

    public PeopleAdapter(OnItemClickListener onItemClickListener) {
        personList = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.person = personList.get(position);
        holder.mTextName.setText(personList.get(position).name);
        holder.mTextPhone.setText(personList.get(position).phone);

        holder.mTextAddress.setText(holder.person.address);
        holder.mTextEmail.setText(holder.person.email);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.clickItem(holder.person);
            }
        });

        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.clickLongItem(holder.person);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void setValues(List<Person> values) {
        personList = values;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView mTextName;
        private TextView mTextPhone;
        private TextView mTextEmail;
        private TextView mTextAddress;
        private Person person;

        private ViewHolder(View view) {
            super(view);
            this.view = view;
            mTextName = view.findViewById(R.id.tv_name);
            mTextPhone = view.findViewById(R.id.tv_phone);
            mTextEmail = view.findViewById(R.id.tv_email);
            mTextAddress = view.findViewById(R.id.tv_address);
        }
    }


}
