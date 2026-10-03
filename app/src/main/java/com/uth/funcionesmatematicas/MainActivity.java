package com.uth.funcionesmatematicas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText numero1;
    private EditText numero2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });
        numero1 = findViewById(R.id.numero1);
        numero2 = findViewById(R.id.numero2);
        findViewById(R.id.suma).setOnClickListener(v -> calcular("+"));
        findViewById(R.id.resta).setOnClickListener(v -> calcular("−"));
        findViewById(R.id.division).setOnClickListener(v -> calcular("÷"));
        findViewById(R.id.multiplicacion).setOnClickListener(v -> calcular("×"));
    }

    private Double leerNumero(EditText campo) {
        String texto = campo.getText().toString().trim();
        if (texto.isEmpty()) {
            campo.setError(getString(R.string.campo_vacio));
            return null;
        }
        try {
            double valor = Double.parseDouble(texto.replace(',', '.'));
            if (!Double.isFinite(valor)) throw new NumberFormatException();
            campo.setError(null);
            return valor;
        } catch (NumberFormatException e) {
            campo.setError(getString(R.string.numero_invalido));
            return null;
        }
    }

    private void calcular(String simbolo) {
        Double a = leerNumero(numero1);
        Double b = leerNumero(numero2);
        if (a == null || b == null) {
            (a == null ? numero1 : numero2).requestFocus();
            return;
        }
        OperacionesMatematicas operaciones = new OperacionesMatematicas(a, b);
        double resultado;
        switch (simbolo) {
            case "+": resultado = operaciones.sumar(); break;
            case "−": resultado = operaciones.restar(); break;
            case "×": resultado = operaciones.multiplicar(); break;
            default:
                if (b == 0) {
                    numero2.setError(getString(R.string.division_cero));
                    numero2.requestFocus();
                    return;
                }
                resultado = operaciones.dividir();
        }
        if (!Double.isFinite(resultado)) {
            Toast.makeText(this, R.string.resultado_fuera_rango, Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.EXTRA_RESULTADO, resultado);
        intent.putExtra(ResultActivity.EXTRA_OPERACION,
                ResultActivity.formatear(a) + " " + simbolo + " " + ResultActivity.formatear(b));
        startActivity(intent);
    }
}
