package com.example.appgestionnotas.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appgestionnotas.controller.StudentController;
import com.example.appgestionnotas.controller.NoteController;
import com.example.appgestionnotas.databinding.ActivityDetallesEstudiantesBinding;
import com.example.appgestionnotas.model.Estudiante;
import com.example.appgestionnotas.model.Nota;
import java.util.ArrayList;
import java.util.List;

public class DetallesEstudiantes extends AppCompatActivity {
    ActivityDetallesEstudiantesBinding binding;
    NoteController notaController = new NoteController(this);
    StudentController estudianteController = new StudentController(this);

    List<Nota> notas = new ArrayList<>();
    NoteListAdapter notasAdapter = new NoteListAdapter(this, notas);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetallesEstudiantesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerView = binding.listaDeNotas;
        recyclerView.setAdapter(notasAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configura el listener para clics en notas
        notasAdapter.setOnNotaClickListener(nota -> {
            mostrarDialogoOpciones(nota);
        });

        binding.verPromedioBtn.setOnClickListener(v -> {
            String codigoEstudiante = binding.codigoEstudiante.getText().toString().trim();

            if (codigoEstudiante.isEmpty()) {
                binding.codigoEstudiante.setError("Este campo no puede estar vacío!");
                return;
            }

            Estudiante estudiante = estudianteController.obtenerEstudiantePorCodigo(codigoEstudiante);

            if (estudiante == null) {
                binding.codigoEstudiante.setError("Estudiante no encontrado!");
                return;
            }

            notas.clear();
            notas.addAll(notaController.obtenerNotasPorEstudiante(estudiante.getId()));
            notasAdapter.notifyDataSetChanged();

            double promedio = notaController.calcularPromedio(notas);
            binding.tvNombre.setText(estudiante.getName());
            binding.tvPromedio.setText(String.format("%.1f", promedio));
        });

        binding.btnAgregarNotaDetalles.setOnClickListener(view -> {
            String codigoEstudiante = binding.codigoEstudiante.getText().toString().trim();
            String notaStr = String.valueOf(binding.inputAgregarNotaDetalles.getText());
            Estudiante estudiante = estudianteController.obtenerEstudiantePorCodigo(codigoEstudiante);

            if (codigoEstudiante.isEmpty() || notaStr.isEmpty()) {
                binding.codigoEstudiante.setError("Este campo no puede estar vacío!");
                binding.inputAgregarNotaDetalles.setError("Este campo no puede estar vacío!");
                return;
            }

            double notaAgregada = Double.parseDouble(notaStr);
            if (notaAgregada < 1 || notaAgregada > 5) {
                binding.inputAgregarNotaDetalles.setError("La nota debe estar entre 1 y 5!");
                return;
            }

            notaController.agregarNota(estudiante.getId(), notaAgregada);
            notas.clear();
            notas.addAll(notaController.obtenerNotasPorEstudiante(estudiante.getId()));
            notasAdapter.notifyDataSetChanged();

            double promedio = notaController.calcularPromedio(notas);
            Toast.makeText(this, "Nota agregada!", Toast.LENGTH_SHORT).show();
            binding.tvPromedio.setText(String.format("%.1f", promedio));
            binding.inputAgregarNotaDetalles.setText("");
        });

        binding.btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void mostrarDialogoOpciones(Nota nota) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una acción")
                .setItems(new CharSequence[]{"Editar", "Eliminar"}, (dialog, which) -> {
                    if (which == 0) {
                        Intent intent = new Intent(this, NoteEdit.class);
                        intent.putExtra("idNota", nota.getId());
                        intent.putExtra("notaActual", nota.getValue());
                        startActivity(intent);
                    } else if (which == 1) {
                        mostrarDialogoConfirmacion(nota);
                    }
                })
                .show();
    }

    private void mostrarDialogoConfirmacion(Nota nota) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Estás seguro de eliminar esta nota?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    notaController.eliminarNota(nota.getId());
                    notas.remove(nota);
                    notasAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Nota eliminada", Toast.LENGTH_SHORT).show();
                    binding.tvNombre.setText("Ingresa el código");
                    binding.tvPromedio.setText("Ingresa el código");
                }).setNegativeButton("Cancelar", null).show();
    }
}
