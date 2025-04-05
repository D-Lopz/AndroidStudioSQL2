package com.example.appgestionnotas.model;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appgestionnotas.MainActivity;
import com.example.appgestionnotas.R;
import com.example.appgestionnotas.controller.NoteController;
import com.example.appgestionnotas.controller.StudentController;
import com.example.appgestionnotas.databinding.ActivityAgregarEstudiantesBinding;

public class AgregarEstudiantes extends AppCompatActivity {
    private ActivityAgregarEstudiantesBinding binding;
    private StudentController estudianteController;
    private NoteController notaController;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_estudiantes);
        binding = ActivityAgregarEstudiantesBinding.inflate(getLayoutInflater());
        setContentView (binding.getRoot());

        binding.agregarButton.setOnClickListener(view -> {
            String codigo = binding.codigoInput.getText().toString();
            String nombre = binding.nombreInput.getText().toString();
            String nota = binding.notaInput.getText().toString();

            if (codigo.isEmpty() || nombre.isEmpty() || nota.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            } else {
                estudianteController.agregarEstudiante(nombre, codigo);
                notaController.agregarNota(Integer.parseInt(codigo), Double.parseDouble(nota));

                // Notificar al Adapter sobre el nuevo elemento
                adapter.notifyDataSetChanged();

                // Desplazarse al último elemento
                binding.student_list.post(() -> {
                    binding.student_list.smoothScrollToPosition(adapter.getItemCount() - 1);
                });

                Toast.makeText(getApplicationContext(), "Estudiante agregado exitosamente.", Toast.LENGTH_SHORT).show();

                binding.codigoInput.setText("");
                binding.nombreInput.setText("");
                binding.notaInput.setText("");
            }
        });


        binding.volverBtn.setOnClickListener(view ->{
            Intent intent = new Intent(AgregarEstudiantes.this, MainActivity.class);
            startActivity(intent);
        });




    }
}