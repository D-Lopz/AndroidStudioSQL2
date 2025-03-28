package com.example.appgestionnotas.model;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appgestionnotas.R;
import com.example.appgestionnotas.controller.StudentController;
import com.example.appgestionnotas.databinding.ActivityAgregarEstudiantesBinding;

public class AgregarEstudiantes extends AppCompatActivity {
    private ActivityAgregarEstudiantesBinding binding;
    private StudentController estudianteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_estudiantes);
        binding = ActivityAgregarEstudiantesBinding.inflate(getLayoutInflater());
        setContentView (binding.getRoot());

        String codigo = binding.codigoInput.getText().toString();
        String nombre = binding.nombreInput.getText().toString();
        int nota = Integer.parseInt(binding.notaInput.getText().toString()) ;

        binding.agregarButton.setOnClickListener(view -> {
            if (codigo.isEmpty() || nombre.isEmpty() || binding.notaInput.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            } else {
                estudianteController.agregarEstudiante(nombre, codigo);
                Toast.makeText(getApplicationContext(), "Estudiante agregado exitosamente.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}