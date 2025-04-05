package com.example.appgestionnotas.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appgestionnotas.controller.StudentController;
import com.example.appgestionnotas.databinding.ActivityMainBinding;
import com.example.appgestionnotas.model.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private StudentAdapter studentAdapter;
    private StudentController estudianteController;
    private List<Estudiante> listaEstudiantes;

/*    private final ActivityResultLauncher<Intent> agregarEstudianteLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {

                    listaEstudiantes.addAll(estudianteController.obtenerEstudiantes());
                    studentAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Estudiante agregado con éxito", Toast.LENGTH_SHORT).show();
                }
            });*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        estudianteController = new StudentController(this);
        listaEstudiantes = new ArrayList<>(estudianteController.obtenerEstudiantes());

        binding.studentList.setLayoutManager(new LinearLayoutManager(this));
        binding.studentList.setAdapter(studentAdapter);

        // Agregar datos de prueba si no hay estudiantes
        if (listaEstudiantes.isEmpty()) {
            //listaEstudiantes.add(new Estudiante(1, "Juan Pérez", "55652"));
            //listaEstudiantes.add(new Estudiante(2, "María López", "22654"));
            //listaEstudiantes.add(new Estudiante(3, "Carlos Gómez", "23561"));
            //listaEstudiantes.add(new Estudiante(3, "Carlos Gómez", "23561"));
        }


        // Configurar RecyclerView con binding
        binding.studentList.setLayoutManager(new LinearLayoutManager(this));
        studentAdapter = new StudentAdapter(listaEstudiantes);
        binding.studentList.setAdapter(studentAdapter);

        // Botón para ir a AgregarEstudiantes
        binding.AddStudent.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AgregarEstudiantes.class);
            startActivity(intent); // ✅
        });
    }
}
