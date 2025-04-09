package com.example.appgestionnotas.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appgestionnotas.controller.NoteController;
import com.example.appgestionnotas.databinding.ActivityNoteEditBinding;

public class NoteEdit extends AppCompatActivity {
    ActivityNoteEditBinding binding;
    NoteController notaController = new NoteController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int notaId = getIntent().getIntExtra("idNota", -1);
        double notaActual = getIntent().getDoubleExtra("notaActual", -1);

        // Validar que la nota y el ID sean válidos
        if (notaId == -1 || notaActual == -1) {
            Toast.makeText(this, "Error al cargar la nota.", Toast.LENGTH_SHORT).show();
            finish(); // Salir si algo salió mal
            return;
        }

        // Mostrar nota actual
        binding.tvNombreActualNota.setText(String.valueOf(notaActual));

        // Botón editar
        binding.btnEditarNota.setOnClickListener(v -> {
            String nuevaNotaStr = binding.inputNuevaNota.getText().toString().trim();

            if (nuevaNotaStr.isEmpty()) {
                binding.inputNuevaNota.setError("Ingresa una nota!");
                return;
            }

            try {
                double nuevaNota = Double.parseDouble(nuevaNotaStr);

                if (nuevaNota < 1.0 || nuevaNota > 5.0) {
                    binding.inputNuevaNota.setError("La nota debe estar entre 1.0 y 5.0.");
                    return;
                }

                // Editar la nota
                notaController.editarNota(notaId, nuevaNota);
                Toast.makeText(getApplicationContext(), "Nota editada exitosamente.", Toast.LENGTH_SHORT).show();

                // Actualizar vista
                binding.tvNombreActualNota.setText(nuevaNotaStr);
                binding.inputNuevaNota.setText("");

                // Cerrar teclado
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(binding.inputNuevaNota.getWindowToken(), 0);
                }

            } catch (NumberFormatException e) {
                binding.inputNuevaNota.setError("Ingresa un número válido.");
            }
        });

        // Botón volver
        binding.btnVolverEditarNota.setOnClickListener(v -> {
            Intent intent = new Intent(this, DetallesEstudiantes.class);
            startActivity(intent);
            finish(); // Para evitar que vuelva con el botón de atrás
        });
    }
}