package eu.ase.proiectgestiune.bun;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;

import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.database.ApartamentDatabase;
import eu.ase.proiectgestiune.bun.database.ApartamentRepository;
import eu.ase.proiectgestiune.bun.fragment.ListaApartamente;
import eu.ase.proiectgestiune.bun.util.Apartament;

import static android.app.Activity.RESULT_OK;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class AdaugaApartament extends Activity {

    EditText locatie;
    EditText anInfiintare;
    EditText suprafata;
    EditText pretChirie;
    Spinner tipApartament;
    RadioGroup structuraAp;
    Button btn_save;
    public static final String ADD_APARTAMENT_KEY = "apartamentKey";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adauga_apartament);
        btn_save = (Button) findViewById(R.id.btn_save);
        locatie = (EditText)findViewById(R.id.zona);
        suprafata =(EditText)findViewById(R.id.suprafata);
        anInfiintare =(EditText)findViewById(R.id.anBloc);
        pretChirie = (EditText)findViewById(R.id.pret);
        tipApartament = (Spinner) findViewById(R.id.tipAp);
        structuraAp = (RadioGroup) findViewById(R.id.rg_structuraAp);
        ArrayAdapter<CharSequence> positionsAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.tip_apartament, R.layout.support_simple_spinner_dropdown_item);
        tipApartament.setAdapter(positionsAdapter);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {    Apartament apartamentnou = createApartamentfromForm();
                  //  db.getapartamentDao().insertApartament(apartamentnou);
                   insertApartament(apartamentnou);
                    Intent intent = new Intent(AdaugaApartament.this, Lista_Ap_Libere.class);
                    Toast.makeText(getApplicationContext(), apartamentnou.toString(), Toast.LENGTH_LONG).show();
                    intent.putExtra(ADD_APARTAMENT_KEY, apartamentnou);
                    startActivityForResult(intent, RESULT_OK);
                    finish();
                }
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
        public void insertApartament(Apartament apartament){
            ApartamentDatabase database= ApartamentDatabase.getInstance(this);
      new ApartamentRepository.InsertApartamentAsyncTask(database.getapartamentDao()).execute(apartament);
        }

     private Apartament createApartamentfromForm()
     {
         String locatieApartament= locatie.getText().toString();
         Integer suprafataApartament = Integer.parseInt(suprafata.getText().toString());
         Integer anApartament= Integer.parseInt(anInfiintare.getText().toString());
         Integer pretApartamentChirie=  Integer.parseInt(pretChirie.getText().toString());
         String tipApartamentSpinner = tipApartament.getSelectedItem().toString();
         RadioButton selectStructura = findViewById(structuraAp.getCheckedRadioButtonId());
         String structura = selectStructura.getText().toString();
         Integer disponibilitate =1;

         return new Apartament(locatieApartament,suprafataApartament,anApartament,pretApartamentChirie,tipApartamentSpinner,structura, disponibilitate);
     }

    private boolean validate() //validari pt completarea formului
  {
        if (locatie.getText() == null || locatie.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getApplicationContext(), R.string.wrong_location_message, Toast.LENGTH_LONG).show();
            return false;
        }

        if (suprafata.getText() == null || suprafata.getText().toString().trim().isEmpty() || suprafata.getText().toString().length() >3)
        {
            Toast.makeText(getApplicationContext(), R.string.wrong_surface, Toast.LENGTH_LONG).show();
            return false;
        }
        if (anInfiintare.getText() == null || anInfiintare.getText().toString().trim().isEmpty() || anInfiintare.getText().toString().length()!=4)
        {
            Toast.makeText(getApplicationContext(), R.string.wrong_year, Toast.LENGTH_LONG).show();
            return false;
        }
        if (pretChirie.getText() == null || pretChirie.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getApplicationContext(), R.string.wrong_price, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}