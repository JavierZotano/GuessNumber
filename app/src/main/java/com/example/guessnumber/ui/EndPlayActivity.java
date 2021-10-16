package com.example.guessnumber.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.guessnumber.R;
import com.example.guessnumber.data.model.Configuracion;
import com.example.guessnumber.data.model.Resultados;
import com.example.guessnumber.databinding.ActivityEndPlayBinding;

public class EndPlayActivity extends AppCompatActivity {
    Configuracion configuracion;
    Resultados resultados;
    private ActivityEndPlayBinding binding;

    /**
     * Se muestran en pantalla los datos recibidos de la configuración y los resultados de la
     * partida.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEndPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        configuracion = (Configuracion) intent.getExtras().getSerializable("configuracion");
        resultados= (Resultados) intent.getExtras().getSerializable("resultados");

        binding.tvNombreEnd.setText(configuracion.getNombre());
        binding.tvNumeroIntentosEnd.setText(String.valueOf(configuracion.getNumIntentos()));
        binding.tvNumeroIntentosRestantesPlay.setText(String.valueOf(resultados.getIntentosRestantes()));
        if (resultados.isPartidaGanada()){
            binding.tvResultadoEnd.setText(R.string.resultadoPartidaGanada);
            binding.tvResultadoEnd.setTextColor(Color.GREEN);
        } else{
            binding.tvResultadoEnd.setText(R.string.resultadoPartidaPerdida);
            binding.tvResultadoEnd.setTextColor(Color.RED);
        }
    }
}