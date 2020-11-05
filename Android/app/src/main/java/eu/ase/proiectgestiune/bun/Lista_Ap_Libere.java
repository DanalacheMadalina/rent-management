package eu.ase.proiectgestiune.bun;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
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

import static eu.ase.proiectgestiune.bun.AdaugaApartament.ADD_APARTAMENT_KEY;

public class Lista_Ap_Libere  extends AppCompatActivity {


    public static final int REQUEST_CODE_DISPONIBILITATE =20;
    List<Apartament> listaApartamentelor = new ArrayList<>();
    ListView listViewApartament;
    private ApartamentAdapter adapter;
    Apartament apartamentNOU;
    private int selectedPlayerIndex;
    public static final String KEY_SHOW_ITEM="itemApartament";
    TextView locatie;
    TextView suprafata;
    TextView pret;
    TextView anbloc;
    TextView tipApartament;
    TextView structura;
    TextView disponibilitate;
    ApartamentDatabase database;
    public static final int SOME_STATIC_INT=209;
    public static final  String KEY_DISPONIBILITATE="disponibilitateApartament";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_apartamentelibere);
        locatie = (TextView) findViewById(R.id.locatiaApr);
        anbloc = (TextView) findViewById(R.id.anblocApar);
        suprafata = (TextView) findViewById(R.id.suprafataApar);
        pret = (TextView) findViewById(R.id.pretChirieApar);
        tipApartament = (TextView) findViewById(R.id.tipulApar);
        structura = (TextView) findViewById(R.id.structuraApar);
        disponibilitate=(TextView) findViewById(R.id.disponibilitate);
        listViewApartament = (ListView) findViewById(R.id.lista_apartamenteLV);
        database = ApartamentDatabase.getInstance(this);
        if (getIntent() != null) {
            apartamentNOU = getIntent().getParcelableExtra(ADD_APARTAMENT_KEY);


        }
       Apartament ap1 = new Apartament(2,"str. Dr.Marinescu, nr.7, etaj 8, scara B", 60, 1998, 250, "ap cu 2 camere", "Decomandat");
       Apartament ap2 = new Apartament(3,"str. Mihail Kogalniceanu, nr.33, etaj 10,scara A", 40, 2000, 200, "garsoniera", "Semidecomandat");

//            Apartament ap3 = new Apartament("str. Independentei , nr 1, etaj 1, scara C", 70, 2015, 300, "ap cu 3 camere", "Nedecomandat");
//           Apartament ap4 = new Apartament("str. 1 Decembrie, nr 11,etaj 2, scara B", 52, 1977, 150, "garsoniera", "Decomandat");
//            Apartament ap5 = new Apartament("str. Mihail Moxa, nr 14,etaj 4, scara C", 65, 1987, 310, "mansarda", "Semidecomandat");
//         Apartament ap6 = new Apartament("str. Liveni, nr 55,etaj 4, scara A", 65, 1994, 260, "ap cu 2 camere", "Nedecomandat");

       try {
           listaApartamentelor= getdisponibilitate(1);
       } catch (ExecutionException e) {
           e.printStackTrace();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       adapter = new ApartamentAdapter(this, R.layout.lista_libere, listaApartamentelor, getLayoutInflater());
        listViewApartament.setAdapter(adapter);
        listViewApartament.setOnItemClickListener(LVApartamentSelectat());
        listViewApartament.setOnItemLongClickListener(LVApartamenteOcupate());
        }
    @SuppressLint("StaticFieldLeak")
    public void insertApartament(Apartament apartament){
        ApartamentDatabase database= ApartamentDatabase.getInstance(this);
        new ApartamentRepository.InsertApartamentAsyncTask(database.getapartamentDao()).execute(apartament);
    }

public List<Apartament> getdisponibilitate (int id) throws ExecutionException, InterruptedException {
    return new ApartamentRepository.GetApartmentsDisponibility(database.getapartamentDao()).execute(id).get();
}
    private void updatePlayer(Apartament apartament) {
        listaApartamentelor.get(selectedPlayerIndex).setDisponibilitate(0);
    }
    private AdapterView.OnItemClickListener LVApartamentSelectat()
       {
           return new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                   Intent intent = new Intent(getApplicationContext(), ItemDetailsLV_ap_Libere.class);
                   selectedPlayerIndex = position;
                   intent.putExtra(KEY_SHOW_ITEM, listaApartamentelor.get(position));
                   setResult(SOME_STATIC_INT,intent);
                   startActivityForResult(intent,SOME_STATIC_INT);

               }
           };
       }
        public void updateApartament(int index){
            listaApartamentelor.get(index).setDisponibilitate(0);
            new ApartamentRepository.UpdateApartamentAsyncTask(database.getapartamentDao()).execute(listaApartamentelor.get(index));
    }
    private  AdapterView.OnItemLongClickListener  LVApartamenteOcupate() {

        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Lista_Ap_Ocupate.class);
                selectedPlayerIndex = position;
                intent.putExtra(KEY_DISPONIBILITATE, listaApartamentelor.get(position));
                setResult(REQUEST_CODE_DISPONIBILITATE, intent);
                Apartament s = adapter.getItem(position);
                updateApartament(selectedPlayerIndex);
                adapter.remove(s);
                startActivityForResult(intent, REQUEST_CODE_DISPONIBILITATE);
                return true;
            }
        };}
    public void deleteallAp() {
        if(database.getapartamentDao()!=null)
        new ApartamentRepository.DeleteAll(database.getapartamentDao()).execute();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_delete_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.deleteAll ) {
            if (listaApartamentelor != null && !listaApartamentelor.isEmpty()) {
               deleteallAp();
                listaApartamentelor.clear();
                adapter.clear();
                adapter.notifyDataSetChanged();
                Toast.makeText(this, R.string.delete_message, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, R.string.delete_message_fail,Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent= new Intent(this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
