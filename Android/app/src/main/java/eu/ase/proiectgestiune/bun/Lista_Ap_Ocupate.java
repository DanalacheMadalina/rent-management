package eu.ase.proiectgestiune.bun;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.database.ApartamentDatabase;
import eu.ase.proiectgestiune.bun.database.ApartamentRepository;
import eu.ase.proiectgestiune.bun.util.Apartament;
import eu.ase.proiectgestiune.bun.util.ApartamentAdapter;
import static eu.ase.proiectgestiune.bun.Lista_Ap_Libere.KEY_DISPONIBILITATE;

public class Lista_Ap_Ocupate extends AppCompatActivity {
    List<Apartament> listaApartamentelor = new ArrayList<>();
    ListView listViewApartament;
    private ApartamentAdapter adapter;
    Intent intent;
    TextView locatie;
    TextView suprafata;
    TextView pret;
    TextView an;
    TextView tipApartament;
    TextView structura;
    ApartamentDatabase database;
    Apartament apartament;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_general);
        locatie = (TextView) findViewById(R.id.locatiaApr);
        an = (TextView) findViewById(R.id.anBloc);
        suprafata = (TextView) findViewById(R.id.suprafataApar);
        pret = (TextView) findViewById(R.id.pret);
        tipApartament = (TextView) findViewById(R.id.tipulApar);
        structura = (TextView) findViewById(R.id.structuraApar);
        listViewApartament = (ListView) findViewById(R.id.lw_general);
        database = ApartamentDatabase.getInstance(this);

//            Apartament ap1 = new Apartament("str. Dr.Mihaila, nr.75, etaj 8, scara B", 60, 1968, 250, "ap cu 2 camere", "Decomandat");
//            Apartament ap2 = new Apartament("str. Liveni, nr.33, etaj 20,scara D", 40, 2010, 170, "garsoniera", "Semidecomandat");
//            Apartament ap3 = new Apartament("str. Aleea Lilicacului , nr 8, etaj 1, scara E", 80, 2015, 300, "ap cu 3 camere", "Nedecomandat");
//                 //de lucrat cu baza de date.
//
//            listaApartamentelor.add(ap1);
//            listaApartamentelor.add(ap2);
//            listaApartamentelor.add(ap3);
        intent = getIntent();
        if (intent.hasExtra(KEY_DISPONIBILITATE)) {
             apartament = intent.getParcelableExtra(KEY_DISPONIBILITATE);
        }
        try {
            listaApartamentelor = getdisponibilitate(0);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapter = new ApartamentAdapter(this, R.layout.lista_ocupate, listaApartamentelor, getLayoutInflater());
        listViewApartament.setAdapter(adapter);
    }
    public List<Apartament> getdisponibilitate (int id) throws ExecutionException, InterruptedException {

        return new ApartamentRepository.GetApartmentsDisponibility(database.getapartamentDao()).execute(id).get();
    }
    @Override
    public void onBackPressed() {
        Intent intent= new Intent(this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        // FragmentManager  manager= getSupportFragmentManager();
    }
}
