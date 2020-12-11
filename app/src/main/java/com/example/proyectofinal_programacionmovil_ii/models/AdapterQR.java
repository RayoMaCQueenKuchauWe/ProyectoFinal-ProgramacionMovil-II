package com.example.proyectofinal_programacionmovil_ii.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyectofinal_programacionmovil_ii.R;
import java.util.ArrayList;

public class AdapterQR extends RecyclerView.Adapter<AdapterQR.ViewHolderDatos>{

    ArrayList<BillClass> listQR;

    public AdapterQR(ArrayList<BillClass> listQR) {
        this.listQR = listQR;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.format_qr,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.Assigndata(listQR.get(position));
    }

    @Override
    public int getItemCount() {
        return listQR.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder{
        TextView tvnroBill, tvDate, tvImport;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvnroBill = itemView.findViewById(R.id.tvQrBill);
            tvDate = itemView.findViewById(R.id.tvQrDate);
            tvImport = itemView.findViewById(R.id.tvQrImport);
        }

        public void Assigndata(BillClass billClass) {
            tvnroBill.setText(String.valueOf(billClass.getNroBill()));
            tvDate.setText(billClass.getDateIssued());
            tvImport.setText(String.valueOf(billClass.getImport()));
        }
    }
}
