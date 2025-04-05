package com.example.appgestionnotas.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appgestionnotas.R;
import com.example.appgestionnotas.model.Estudiante;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<Estudiante> listaEstudiantes;

    public StudentAdapter(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detalles_estudiantes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Estudiante estudiante = listaEstudiantes.get(position);
        holder.txtName.setText("Nombre: " + estudiante.getName());
        holder.txtidCode.setText("Código: " + estudiante.getIdCode());

    }

    @Override
    public int getItemCount() {

        return listaEstudiantes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtidCode;
        TextView txtName;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtidCode = itemView.findViewById(R.id.txtidCode);
        }
    }
}
