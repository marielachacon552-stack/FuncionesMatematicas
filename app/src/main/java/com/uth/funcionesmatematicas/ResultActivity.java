package com.uth.funcionesmatematicas;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.math.BigDecimal;

public class ResultActivity extends AppCompatActivity {
    public static final String EXTRA_RESULTADO = "com.uth.funcionesmatematicas.RESULTADO";
    public static final String EXTRA_OPERACION = "com.uth.funcionesmatematicas.OPERACION";

    public static String formatear(double valor) {
        return BigDecimal.valueOf(valor).stripTrailingZeros().toPlainString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!getIntent().hasExtra(EXTRA_RESULTADO)) {
            finish();
            return;
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });
        ((TextView) findViewById(R.id.operacion)).setText(getIntent().getStringExtra(EXTRA_OPERACION));
        ((TextView) findViewById(R.id.resultado)).setText(formatear(getIntent().getDoubleExtra(EXTRA_RESULTADO, 0)));
        findViewById(R.id.volver).setOnClickListener(v -> finish());
    }
}
