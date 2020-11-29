package com.example.proyectofinal_programacionmovil_ii.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal_programacionmovil_ii.R;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolderDatos> implements View.OnClickListener{
    ArrayList<FormModel> listForm;
    private View.OnClickListener listener;

    public AdapterClass(ArrayList<FormModel> listForm) {
        this.listForm = listForm;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.Assigndata(listForm.get(position));
    }

    @Override
    public int getItemCount() {
        return listForm.size();
    }

    public  void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tvTitle, tvMonth, tvYear, tvIdForm;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvIdForm = itemView.findViewById(R.id.tvIdForm);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvMonth = itemView.findViewById(R.id.tvMonth);
            tvYear = itemView.findViewById(R.id.tvYear);
        }

        public void Assigndata(FormModel formModel) {
            tvIdForm.setText(String.valueOf(formModel.getIdForm()));
            tvTitle.setText(formModel.getName());
            tvMonth.setText("Month: " + formModel.getMonth());
            tvYear.setText("Year: " + formModel.getYear());
        }

    }
}
