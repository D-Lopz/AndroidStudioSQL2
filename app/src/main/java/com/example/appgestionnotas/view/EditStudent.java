package com.example.appgestionnotas.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appgestionnotas.controller.StudentController;
import com.example.appgestionnotas.databinding.ActivityEditStudentBinding;
import com.example.appgestionnotas.model.Estudiante;

public class EditStudent extends AppCompatActivity {
    ActivityEditStudentBinding binding;
    StudentController estudianteController = new StudentController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String codEstudiante = getIntent().getStringExtra("codEstudiante");
        super.onCreate(savedInstanceState);
        binding = ActivityEditStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Estudiante estudiante = estudianteController.obtenerEstudiantePorCodigo(codEstudiante);

        String nombreActual = estudiante.getName();
        String codigoActual = estudiante.getIdCode();

        TextView tvNombreActual = binding.tvNombreActualEst;
        TextView tvCodigoActual = binding.tvCodigoActual;

        tvNombreActual.setText(nombreActual);
        tvCodigoActual.setText(codigoActual);

        binding.btnEditarEst.setOnClickListener(v -> {
            String nuevoNombre = binding.inputNuevoNombre.getText().toString().trim();
            String nuevoCodigo = binding.inputNuevoCodigo.getText().toString().trim();

            // Usa valores actuales si están vacíos
            if (nuevoNombre.isEmpty()) nuevoNombre = estudiante.getName();
            if (nuevoCodigo.isEmpty()) nuevoCodigo = estudiante.getIdCode();

            // Verifica si hay cambios
            if (nuevoNombre.equals(estudiante.getName()) && nuevoCodigo.equals(estudiante.getIdCode())) {
                Toast.makeText(this, "No se hicieron cambios.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verifica si el nuevo código ya está en uso
            Estudiante estudianteExistente = estudianteController.obtenerEstudiantePorCodigo(nuevoCodigo);
            if (estudianteExistente != null && estudianteExistente.getId() != estudiante.getId()) {
                binding.inputNuevoCodigo.setError("Este código ya está en uso.");
                return;
            }

            // Editar estudiante
            estudianteController.editarEstudiante(nuevoNombre, nuevoCodigo, estudiante.getId());
            Toast.makeText(this, "Estudiante editado exitosamente.", Toast.LENGTH_SHORT).show();

            // Animaciones sutiles
            tvNombreActual.setAlpha(0f);
            tvCodigoActual.setAlpha(0f);

            tvNombreActual.setText(nuevoNombre);
            tvCodigoActual.setText(nuevoCodigo);

            tvNombreActual.animate().alpha(1f).setDuration(500).start();
            tvCodigoActual.animate().alpha(1f).setDuration(500).start();

            // Limpia inputs
            binding.inputNuevoNombre.setText("");
            binding.inputNuevoCodigo.setText("");

            // Devuelve resultado para actualización futura
            setResult(RESULT_OK, new Intent());
        });

        binding.btnVolverEditarEst.setOnClickListener(v -> {
            finish(); // Mejor que ir a MainActivity directamente
        });
    }
}
