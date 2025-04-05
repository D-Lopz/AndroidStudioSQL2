package com.example.appgestionnotas.view;


import android.content.Intent;
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

            String codigo = binding.codigoInput.getText().toString();
            String nombre = binding.nombreInput.getText().toString();
            String nota = binding.notaInput.getText().toString();

            if (codigo.isEmpty() || nombre.isEmpty() || nota.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            } else {
                try {

                    int cod = Integer.parseInt(codigo);
                    double notaValor = Double.parseDouble(nota);

                    estudianteController.agregarEstudiante(nombre, codigo);
                    //notaController.agregarNota(cod, notaValor);

                    //Limpiar inputs
                    binding.codigoInput.setText("");
                    binding.nombreInput.setText("");
                    binding.notaInput.setText("");

                    Toast.makeText(getApplicationContext(), "Estudiante agregado exitosamente.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    //setResult(RESULT_OK);
                    finish();

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Código y nota deben ser numéricos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.volverBtn.setOnClickListener(view -> finish());
    }
}