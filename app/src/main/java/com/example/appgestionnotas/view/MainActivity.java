package com.example.appgestionnotas;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appgestionnotas.controller.StudentController;
import com.example.appgestionnotas.databinding.ActivityMainBinding;
import com.example.appgestionnotas.model.AgregarEstudiantes;
import com.example.appgestionnotas.model.Estudiante;
import com.example.appgestionnotas.model.StudentAdapter;
import com.example.appgestionnotas.view.PasarActivity;
import com.google.android.material.progressindicator.BaseProgressIndicator;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private StudentAdapter studentAdapter;
    private StudentController estudianteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Inflar el binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtener referencia del RecyclerView
        RecyclerView recyclerView = findViewById(R.id.student_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener datos de estudiantes (si tienes un controlador)
        estudianteController = new StudentController(this);
        List<Estudiante> estudiantes = estudianteController.obtenerEstudiantes();

        // Si la lista está vacía, agregar datos de ejemplo
        if (estudiantes.isEmpty()) {
            estudiantes.add(new Estudiante(1, "Juan Pérez", "55652"));
            estudiantes.add(new Estudiante(2, "María López", "22654"));
            estudiantes.add(new Estudiante(3, "Carlos Gómez", "23561"));
        }

        // Configurar el adapter y asignarlo al RecyclerView
        studentAdapter = new StudentAdapter(estudiantes);
        recyclerView.setAdapter(studentAdapter);


        binding.AddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarEstudiantes.class);
                startActivity(intent);
            }
        });


    }
}