package eu.ase.proiectgestiune.bun;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.util.EvidentaCheltuieli;


public class AdaugaCheltuiala extends Activity {
    EditText descriereCh;
    EditText pretcheltuiala;
    TextView dataAleasa;
    Button btn_alege_data;
    Button  btn_adaugaCh;
    Intent intent;
    DatabaseReference  databasecheltuieli;
    public static final String ADD_CHELTUIALA_KEY = "cheltuialaKey";
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adauga_cheltuiala);
        descriereCh = findViewById(R.id.descrierech);
        pretcheltuiala = findViewById(R.id.sumaCh);
        dataAleasa = findViewById(R.id.tw_adauga_data_aleasa);
        btn_alege_data = findViewById(R.id.btn_data);
        btn_adaugaCh = findViewById(R.id.btn_adauga_ch);
        databasecheltuieli= FirebaseDatabase.getInstance().getReference("evidentacheltuieli");
        btn_alege_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(AdaugaCheltuiala.this, datePickerListener, year, month, day);
                dateDialog.show();


            }
        });
        intent = getIntent();
        btn_adaugaCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                  EvidentaCheltuieli ch =  createCheltuielifromForm();

                    databasecheltuieli.child(ch.getIdCh()).setValue(ch);
                    Intent intent = new Intent(AdaugaCheltuiala.this, ListaCheltuieli.class);
                    intent.putExtra(ADD_CHELTUIALA_KEY, ch);
                    startActivityForResult(intent, RESULT_OK);
                    finish();


                }
            }
        });
    }
        private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String dataAleasa1 = dayOfMonth + "-" + (monthOfYear+1) + "-" + year;
            dataAleasa.setText(dataAleasa1);
        }
    };
    private EvidentaCheltuieli  createCheltuielifromForm() {
        String  descriereCheltuieli= descriereCh.getText().toString();
        Integer costCheltuieli = Integer.parseInt(pretcheltuiala.getText().toString());
        String data= dataAleasa.getText().toString();
        String id= databasecheltuieli.push().getKey();
        return new EvidentaCheltuieli(id,descriereCheltuieli,costCheltuieli,data);
    }


    private boolean validate() {
        if (descriereCh.getText() == null
                || descriereCh.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.wrong_description,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (pretcheltuiala.getText() == null
                || pretcheltuiala.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.wrong_suma, Toast.LENGTH_LONG).show();
            return false;
        }

        if (dataAleasa.getText() == null
                || dataAleasa.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.wrong_date,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }


        return true;
    }



}
