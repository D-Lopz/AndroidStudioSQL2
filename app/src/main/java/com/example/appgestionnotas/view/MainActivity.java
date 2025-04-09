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

    // Para manejar resultado de AgregarEstudiantes
    private final ActivityResultLauncher<Intent> agregarEstudianteLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    cargarListaEstudiantes(); // recargar al volver
                    Toast.makeText(this, "Estudiante agregado", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        estudianteController = new StudentController(this);
        listaEstudiantes = new ArrayList<>();

        // Seteo inicial
        studentAdapter = new StudentAdapter(listaEstudiantes, this);
        binding.studentList.setLayoutManager(new LinearLayoutManager(this));
        binding.studentList.setAdapter(studentAdapter);

        // Cargar estudiantes al inicio
        cargarListaEstudiantes();

        // Botón para ir a AgregarEstudiantes
        binding.AddStudent.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AgregarEstudiantes.class);
            agregarEstudianteLauncher.launch(intent); // ← usar launcher
        });
    }

    private void cargarListaEstudiantes() {
        listaEstudiantes.clear();
        listaEstudiantes.addAll(estudianteController.obtenerEstudiantes());
        studentAdapter.notifyDataSetChanged(); // Notificar que los datos cambiaron
    }
}
