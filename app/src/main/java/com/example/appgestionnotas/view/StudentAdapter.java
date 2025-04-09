package com.example.appgestionnotas.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appgestionnotas.R;
import com.example.appgestionnotas.controller.NoteController;
import com.example.appgestionnotas.model.Estudiante;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private final List<Estudiante> listaEstudiantes;
    private final NoteController notaController;
    private final Context context;

    public StudentAdapter(List<Estudiante> listaEstudiantes, Context context) {
        this.listaEstudiantes = listaEstudiantes;
        this.context = context;
        this.notaController = new NoteController(context); // Solo una vez
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreText, codigoText, promedioText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreText = itemView.findViewById(R.id.nombreEstudiante);
            codigoText = itemView.findViewById(R.id.codigoEstudiante);
            promedioText = itemView.findViewById(R.id.promedioEstudiante);
        }
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_estudiante, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
        Estudiante estudiante = listaEstudiantes.get(position);

        holder.nombreText.setText(estudiante.getName());
        holder.codigoText.setText("Código: " + estudiante.getIdCode());

        double promedio = notaController
                .calcularPromedio(notaController.obtenerNotasPorEstudiante(estudiante.getId()));

        holder.promedioText.setText(String.format("Promedio: %.1f", promedio));

        // Listener para acciones futuras o detalles
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context,
                    "Estudiante: " + estudiante.getName() + "\nPromedio: " + promedio,
                    Toast.LENGTH_SHORT).show();

            // Aquí luego puedes lanzar detallesEstudiante si querés
        });
    }

    @Override
    public int getItemCount() {
        return listaEstudiantes.size();
    }
}
