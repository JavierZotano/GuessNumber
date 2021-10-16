package com.example.guessnumber.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.guessnumber.R;
import com.example.guessnumber.data.model.Configuracion;
import com.example.guessnumber.data.model.Resultados;
import com.example.guessnumber.databinding.ActivityPlayBinding;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    boolean partidaGanada;
    int intentosRestantes, numAleatorio, numIntentos, numIntroducido;
    private ActivityPlayBinding binding;
    Random random = new Random();
    String nombre, strNumIntroducido;
    Configuracion configuracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        configuracion = (Configuracion) intent.getExtras().getSerializable(
                "configuracion");

        partidaGanada = false;
        numAleatorio = random.nextInt(100) + 1;
        numIntentos = configuracion.getNumIntentos();
        intentosRestantes = numIntentos;
        nombre = configuracion.getNombre();

        binding.tvNumeroIntentosRestantesPLay.setText(String.valueOf(intentosRestantes));
    }

    /**
     * Método que comprueba que los datos introducidos en el campo edNumeroIntroducidoPlay sean
     * válidos. Esto se hace comprobando si al jugador le quedan intentos para seguir jugando, si ya
     * ha ganado la partida (en cuyo caso no podrá seguir jugando) y si el número introducido en
     * forma de string puede ser convertido sin problemas a entero a través de esUnEnteroVálido. Si
     * todas las comprobaciones son correctas, se convierte el número a entero y se lanza el método
     * probarNúmero.
     * @param view
     */
    public void comprobarDatos(View view) {
        strNumIntroducido = binding.edNumeroIntroducidoPlay.getText().toString();

        if (intentosRestantes == 0) {
            Toast toast = Toast.makeText(this, R.string.noQuedanIntentos, Toast.LENGTH_LONG);
            toast.show();
        } else if (!esUnEnteroValido(strNumIntroducido)) {
            Toast toast = Toast.makeText(this, R.string.enteroNoValido,
                    Toast.LENGTH_LONG);
            toast.show();
        } else if (partidaGanada) {
            Toast toast = Toast.makeText(this, R.string.avisoPartidaGanada,
                    Toast.LENGTH_LONG);
            toast.show();
        } else {
            numIntroducido = Integer.parseInt(strNumIntroducido);

            probarNumero(numIntroducido);
        }
    }

    /**
     * Comprueba si el número indicado puede ser transformado en un entero.
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
     * Se actualiza el número de intentos restantes y se muestra el nuevo valor al jugador en el
     * campo tvNumeroIntentosRestantesPlay. A continuación, se compara el número escrito por el
     * jugador con el número objetivo. Si el jugador acierta, partidaGanada se iguala a true y se
     * indica al jugador que ha ganado en el campo tvPistaPlay. Si no es correcto, se indica al
     * jugador si se ha pasado o se ha quedado corto. Por último, se desactivan el botón
     * btProbarNumeroPlay y el campo edNumeroIntroducidoPlay, y se activa el botón btResetearPlay.
     * @param numIntroducido
     */
    private void probarNumero(int numIntroducido) {
        intentosRestantes--;

        binding.tvNumeroIntentosRestantesPLay.setText(String.valueOf(intentosRestantes));

        if (numIntroducido == numAleatorio) {
            partidaGanada = true;
            binding.tvPistaPlay.setText(R.string.pistaPartidaGanada);
        } else if (numIntroducido<numAleatorio){
            binding.tvPistaPlay.setText(R.string.demasiadoBajo);
        } else {
            binding.tvPistaPlay.setText(R.string.demasiadoAlto);
        }

        binding.btProbarNumeroPlay.setEnabled(false);
        binding.btResetearPlay.setEnabled(true);
        binding.edNumeroIntroducidoPlay.setEnabled(false);
    }

    /**
     * Método activado al pulsar btResetearPlay. Vuelve a activar el botón btProbarNumeroPlay, a
     * desactivar el propio botón btResetarPlay, vacía el contenido del campo tvPistaPlay, vueve a
     * activar el campo edNumeroIntroducidoPlay y vacía su contenido.
     * @param view
     */
    public void resetear(View view) {
        binding.btProbarNumeroPlay.setEnabled(true);
        binding.btResetearPlay.setEnabled(false);
        binding.tvPistaPlay.setText("");
        binding.edNumeroIntroducidoPlay.setEnabled(true);
        binding.edNumeroIntroducidoPlay.setText("");
    }

    /**
     * Método activado al pulsar el botón btTerminarPartidaPlay. Crea un objeto de tipo Resultados
     * en el que almacena los resultados de la partida, y los introduce en un Bundle junto a la
     * configuración recibida de ConfigActivity. Por último, lanza EndPlayActivity y le pasa el
     * Bundle creado.
     * @param view
     */
    public void terminarPartida(View view) {
        Bundle bundle = new Bundle();
        Resultados resultados = new Resultados(partidaGanada, intentosRestantes);
        bundle.putSerializable("configuracion", configuracion);
        bundle.putSerializable("resultados", resultados);
        Intent intent = new Intent(this, EndPlayActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}