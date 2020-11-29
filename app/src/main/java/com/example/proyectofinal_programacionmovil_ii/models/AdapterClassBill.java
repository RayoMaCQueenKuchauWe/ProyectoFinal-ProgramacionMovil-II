package com.example.proyectofinal_programacionmovil_ii.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyectofinal_programacionmovil_ii.R;
import java.util.ArrayList;

public class AdapterClassBill extends RecyclerView.Adapter<AdapterClassBill.ViewHolderDatos>{

    ArrayList<BillClass> listBills;

    public AdapterClassBill(ArrayList<BillClass> listBills) {
        this.listBills = listBills;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.format_list_bill,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.Assigndata(listBills.get(position));
    }

    @Override
    public int getItemCount() {
        return listBills.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tvID, tvnroBill, tvDate, tvImport;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvIdFormB);
            tvnroBill = itemView.findViewById(R.id.tvBillB);
            tvDate = itemView.findViewById(R.id.tvDateB);
            tvImport = itemView.findViewById(R.id.tvImportB);
        }

        public void Assigndata(BillClass billClass) {
            tvID.setText(String.valueOf(billClass.getIdBill()));
            tvnroBill.setText(String.valueOf(billClass.getNroBill()));
            tvDate.setText(billClass.getDateIssued());
            tvImport.setText(String.valueOf(billClass.getImport()));
        }

    }
}
