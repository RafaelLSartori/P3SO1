package br.edu.fateczl.prep3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etSInicial;
    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etSInicial = findViewById(R.id.etSInicial);
        etSInicial.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        tvRes = findViewById(R.id.tvRes);
        tvRes.setTextColor(ContextCompat.getColor(this, R.color.black));
        tvRes.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        Button btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(op -> calc());
    }

    private void calc(){
        String sInicial = etSInicial.getText().toString();
        if (sInicial.isEmpty()) {
            Toast.makeText(this, "Digite um número!", Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            float sInicial1 = Float.parseFloat(sInicial);
            if(sInicial1 < 1 || sInicial1 > 19){
                Toast.makeText(this, "O número precisa ser entre 1 e 19!", Toast.LENGTH_SHORT).show();
                etSInicial.setText("");
            } else {
                float serie = 0;
                for (float i = 1; i <= sInicial1; i++){
                    if (i % 2 == 0){
                        serie -= 1 / i;
                    } else{
                        serie += 1 / i;
                    }

                }
                String res = getString(R.string.resultado_n) + " " + serie;
                tvRes.setText(res);
                etSInicial.setText("");
            }
        } catch (NumberFormatException e){
            Toast.makeText(this, "Por favor, insira um número válido!", Toast.LENGTH_SHORT).show();
            etSInicial.setText("");
        }

    }
}