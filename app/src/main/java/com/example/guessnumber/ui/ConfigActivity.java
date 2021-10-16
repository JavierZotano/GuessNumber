package com.example.guessnumber.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.guessnumber.R;
import com.example.guessnumber.data.model.Configuracion;
import com.example.guessnumber.databinding.ActivityConfigBinding;

public class ConfigActivity extends AppCompatActivity {
    private String nombre, stringNumIntentos;
    private int numIntentos;
    private ActivityConfigBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfigBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * Método que comprueba si los campos edNombreConfig y edNumeroConfig están vacíos, y si el
     * valor introducido en el campo edNumero es un valor válido para un entero. Esto se hace a
     * través del método esUnEnteroValido. Si los datos son correctos y pasan todas las
     * comprobaciones mencionadas, se lanza el método empezarJuego.
     * @param view
     */
    public void comprobarDatos(View view) {
        nombre = binding.edNombreConfig.getText().toString();
        stringNumIntentos = binding.edNumeroIntentosConfig.getText().toString();

        if (nombre.isEmpty() || stringNumIntentos.isEmpty()) {
            Toast toast = Toast.makeText(this, R.string.avisoCampoVacio, Toast.LENGTH_LONG);
            toast.show();
        } else if (!esUnEnteroValido(stringNumIntentos)) {
            Toast toast = Toast.makeText(this, R.string.enteroNoValido,
                    Toast.LENGTH_LONG);
            toast.show();
        } else if (Integer.parseInt(stringNumIntentos) == 0) {
            Toast toast = Toast.makeText(this, R.string.enteroNoValido,
                    Toast.LENGTH_LONG);
            toast.show();
        } else {
            numIntentos = Integer.parseInt(stringNumIntentos);
            empezarJuego();
        }
    }

    /**
     * Comprueba si el número indicado puede ser transformado en un entero.
     *
     * @param numero Número que se quiere comprobar expresado como string.
     * @return True si el número puede ser convertido a entero, false si no puede.
     */
    private boolean esUnEnteroValido(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Se preparan los datos introducidos por el usuario para pasarlos a PlayActivity y se lanza la
     * misma.
     */
    private void empezarJuego() {
        Bundle bundle = new Bundle();
        Configuracion configuracion = new Configuracion(nombre, numIntentos);
        bundle.putSerializable("configuracion", configuracion);
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}