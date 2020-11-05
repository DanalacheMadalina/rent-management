package eu.ase.proiectgestiune.bun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.fragment.Cheltuieli;
import eu.ase.proiectgestiune.bun.util.EvidentaCheltuieli;
import eu.ase.proiectgestiune.bun.util.EvidentaCheltuieliAdaptor;
import static eu.ase.proiectgestiune.bun.AdaugaCheltuiala.ADD_CHELTUIALA_KEY;


public class ListaCheltuieli  extends AppCompatActivity {


    ArrayList<EvidentaCheltuieli> listacheltuieli = new ArrayList<>();
    ListView lwCheltuieli;
    private EvidentaCheltuieliAdaptor adapter;
    EvidentaCheltuieli evidentaCheltuieli;
    DatabaseReference databasecheltuieli;
    public static final String KEY_SHOW_ITEM="itemApartament";
    TextView detaliich;
    TextView pretch;
    TextView datach;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_cheltuieli);
        detaliich = (TextView) findViewById(R.id.tw_detalii);
        pretch = (TextView) findViewById(R.id.tw_suma);
        datach = (TextView) findViewById(R.id.tw_data);
        databasecheltuieli= FirebaseDatabase.getInstance().getReference("evidentacheltuieli");

        lwCheltuieli = (ListView) findViewById(R.id.list_view_cheltuieli);

//        if (getIntent() != null) {
//            evidentaCheltuieli = getIntent().getParcelableExtra(ADD_CHELTUIALA_KEY);
//            listacheltuieli.add(evidentaCheltuieli);
//
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        databasecheltuieli.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for( DataSnapshot chsnapShot :dataSnapshot.getChildren())
                {
                    EvidentaCheltuieli evCh=chsnapShot.getValue(EvidentaCheltuieli.class);
                    listacheltuieli.add(evCh);

                }
                adapter = new EvidentaCheltuieliAdaptor(ListaCheltuieli.this, R.layout.listacheltuieli, listacheltuieli, getLayoutInflater());

                lwCheltuieli.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
//
//    @Override
//    public void onBackPressed() {
//        Intent intent= new Intent(this, Cheltuieli.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//    }


}
