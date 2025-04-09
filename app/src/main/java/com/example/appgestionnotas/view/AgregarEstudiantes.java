package com.example.appgestionnotas.view;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appgestionnotas.controller.NoteController;
import com.example.appgestionnotas.controller.StudentController;
import com.example.appgestionnotas.databinding.ActivityAgregarEstudiantesBinding;
import com.example.appgestionnotas.model.Estudiante;

public class AgregarEstudiantes extends AppCompatActivity {
    private ActivityAgregarEstudiantesBinding binding;
    private StudentController estudianteController;
    private NoteController notaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarEstudiantesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notaController = new NoteController(this);
        estudianteController = new StudentController(this);

        binding.agregarButton.setOnClickListener(view -> {
            String codigo = binding.codigoInput.getText().toString().trim();
            String nombre = binding.nombreInput.getText().toString().trim();
            String nota = binding.notaInput.getText().toString().trim();

            // Limpiar errores anteriores
            binding.codigoInput.setError(null);
            binding.nombreInput.setError(null);
            binding.notaInput.setError(null);

            if (codigo.isEmpty() || nombre.isEmpty() || nota.isEmpty()) {
                if (nombre.isEmpty()) binding.nombreInput.setError("Este campo no puede estar vacío");
                if (codigo.isEmpty()) binding.codigoInput.setError("Este campo no puede estar vacío");
                if (nota.isEmpty()) binding.notaInput.setError("Este campo no puede estar vacío");

                Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            Estudiante estudianteExistente = estudianteController.obtenerEstudiantePorCodigo(codigo);
            if (estudianteExistente != null) {
                binding.codigoInput.setError("Este código ya está en uso");
                Toast.makeText(getApplicationContext(), "Por favor, elija otro código.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int student_id = Integer.parseInt(codigo);
                double value = Double.parseDouble(nota);

                // Validar nota
                if (value < 0 || value > 100) {
                    binding.notaInput.setError("La nota debe estar entre 0 y 100");
                    return;
                }

                estudianteController.agregarEstudiante(nombre, codigo);
                notaController.agregarNota(student_id, value);

                // Limpiar campos
                binding.codigoInput.setText("");
                binding.nombreInput.setText("");
                binding.notaInput.setText("");

                Toast.makeText(getApplicationContext(), "Estudiante agregado exitosamente.", Toast.LENGTH_SHORT).show();

                // Devolver control a MainActivity con resultado OK
                setResult(RESULT_OK);
                finish();

            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Código y nota deben ser numéricos.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.volverBtn.setOnClickListener(view -> finish());
    }
}
