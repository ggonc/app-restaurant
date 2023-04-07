package com.mobile.balaiodelenharestaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button btnCalcular;
    public EditText inputConsumoTotal, inputCouvert, inputDividirPor;
    public TextView inputTaxaServico, inputContaTotal, inputContaPorPessoa;

    double couvert, consumoTotal;
    int divisaoPor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalcular = findViewById(R.id.btnCalcular);
        inputConsumoTotal = findViewById(R.id.inputConsumoTotal);
        inputCouvert = findViewById(R.id.inputCouvert);
        inputDividirPor = findViewById(R.id.inputDividirPor);
        inputTaxaServico = findViewById(R.id.inputTaxaServico);
        inputContaTotal = findViewById(R.id.inputContaTotal);
        inputContaPorPessoa = findViewById(R.id.inputContaPorPessoa);

    }

    public void calcular(View view) {
        if (inputConsumoTotal.getText().toString().trim().isEmpty() ||
                Integer.parseInt(inputConsumoTotal.getText().toString()) <= 0 ||
                inputCouvert.getText().toString().trim().isEmpty() ||
                Integer.parseInt(inputCouvert.getText().toString()) <= 0 ||
                inputDividirPor.getText().toString().trim().isEmpty() ||
                Integer.parseInt(inputDividirPor.getText().toString()) <= 0
        )
        {
            Toast.makeText(this, "Erro: Verifique os valores inseridos!", Toast.LENGTH_SHORT).show();
            return;
        }
        consumoTotal = Double.parseDouble(inputConsumoTotal.getText().toString());
        couvert = Double.parseDouble(inputCouvert.getText().toString());
        divisaoPor = Integer.parseInt(inputDividirPor.getText().toString());
        calcularValorPorPessoa(view);
    }

    public double calcularContaTotal(View view) {
        double resultado;
        resultado = consumoTotal + (couvert * divisaoPor);

        inputContaTotal.setText("R$ " + String.format("%.2f", resultado));

        return resultado;
    }

    public double calcularTaxa(View view) {
        double taxa;
        taxa = consumoTotal * 0.1;

        inputTaxaServico.setText("R$ " + String.format("%.2f", taxa));

        return taxa;
    }

    public void calcularValorPorPessoa(View view) {
        double valorPorPessoa, valorFinal;
        valorFinal = calcularContaTotal(view) + calcularTaxa(view);
        valorPorPessoa = (valorFinal - calcularTaxa(view)) / divisaoPor;
        Toast.makeText(this, "valorFinal: " + valorFinal, Toast.LENGTH_SHORT).show();
        inputContaPorPessoa.setText("R$ " + String.format("%.2f", valorPorPessoa));
    }


}