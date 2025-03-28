package com.example.appgestionnotas;

import android.os.Bundle;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appgestionnotas.controller.StudentController;
import com.example.appgestionnotas.databinding.ActivityMainBinding;
import com.example.appgestionnotas.model.Estudiante;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
        private StudentController estudianteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        estudianteController = new StudentController(this);
        ListView listView = findViewById(R.id.studentList);

        List<Estudiante> estudiantes = estudianteController.obtenerEstudiantes();
        // Aquí debes implementar un adaptador para mostrar los estudiantes en un ListView o RecyclerView

    }
}