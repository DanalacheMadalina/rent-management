package eu.ase.proiectgestiune.bun;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.database.ApartamentDatabase;
import eu.ase.proiectgestiune.bun.database.ApartamentRepository;
import eu.ase.proiectgestiune.bun.util.Apartament;
import eu.ase.proiectgestiune.bun.util.ApartamentAdapter;
import static eu.ase.proiectgestiune.bun.Lista_Ap_Libere.KEY_SHOW_ITEM;

public class ItemDetailsLV_ap_Libere extends AppCompatActivity {
    TextView locatie;
    TextView suprafata;
    TextView pret;
    TextView anbloc;
    TextView tipApartament;
    TextView structura;
    Button sterge;
    Button btn_locatie;
    Intent intent;
    public static final  String KEY_FOR_LOCATION="locatie";
    Apartament apartament;
    CoordonateApartament coord;
    private ApartamentAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details_lv);

        locatie = (TextView) findViewById(R.id.locatiaApr);
        anbloc = (TextView) findViewById(R.id.anblocApar);
        suprafata = (TextView) findViewById(R.id.suprafataApar);
        pret = (TextView) findViewById(R.id.pretChirieApar);
        tipApartament = (TextView) findViewById(R.id.tipulApar);
        structura = (TextView) findViewById(R.id.structuraApar);
        sterge=findViewById(R.id.btn_sterge_details);
        btn_locatie=findViewById(R.id.btn_locatie);

        intent = getIntent();
        if (intent.hasExtra(KEY_SHOW_ITEM)) {
             apartament = intent.getParcelableExtra(KEY_SHOW_ITEM);
            locatie.setText(apartament.getLocatieAp());
            anbloc.setText(String.valueOf(apartament.getAnInfiintare()));
            suprafata.setText(String.valueOf(apartament.getSuprafata()));
            pret.setText(String.valueOf(apartament.getPretChirie()));
            tipApartament.setText(apartament.getTipApartament());
            structura.setText(apartament.getStructura());}

        sterge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteApartament(apartament);
                Intent intent = new Intent(getApplicationContext(), Lista_Ap_Libere.class);
                startActivity(intent);
            }
        });

        btn_locatie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rnd = new Random();
                double min1= 44;
                double max1=44.5;
                double random = min1 + rnd.nextDouble() * (max1 - min1);

                double min2= 26;
                double max2=26.8;
                double random2 = min2 + rnd.nextDouble() * (max2 - min2);

                coord=new CoordonateApartament(random, random2,apartament.getLocatieAp());
                Intent intent = new Intent(getApplicationContext(), VizualizareMap.class);
                intent.putExtra(KEY_FOR_LOCATION, coord);
                startActivity(intent);
            }
        });

        new DownloadImage((ImageView) findViewById(R.id.imageView2))
                .execute("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSbmBjM0aRTWhVdwDs9IaMSgdjXjId-BKZxn04pVwZMUpgjRA1Y&s");

    }

    // sau Apasa D pentru a sterge apartamentul
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_D) {
            deleteApartament(apartament);
            Intent intent = new Intent(this, Lista_Ap_Libere.class);
            this.startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void deleteApartament(Apartament apartament){
        ApartamentDatabase database= ApartamentDatabase.getInstance(this);
        new ApartamentRepository.DeleteApartamentAsyncTask(database.getapartamentDao()).execute(apartament);
    }

}


