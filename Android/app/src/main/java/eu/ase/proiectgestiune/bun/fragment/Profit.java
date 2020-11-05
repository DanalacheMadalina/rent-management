package eu.ase.proiectgestiune.bun.fragment;

import android.content.SharedPreferences;
import android.icu.text.PluralRules;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.database.ApartamentDatabase;
import eu.ase.proiectgestiune.bun.database.ApartamentRepository;

import static android.content.Context.MODE_PRIVATE;
public class Profit extends Fragment {


    TextView calcul;
    TextView nrApr;
    TextView rezultat;
    AutoCompleteTextView tipAp;
    int sumaChirii = 0;
    Button calculeazanr;
    ApartamentDatabase database;
    List<String> listatipurideApartamente = new ArrayList<>();
    private String fileName="raport.txt";
    Button btn_salveaza;
    Button btn_citeste;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        database = ApartamentDatabase.getInstance(getContext());

        final View rootview = inflater.inflate(R.layout.fragment_profit, container, false);
        calcul =  rootview.findViewById(R.id.calcul1);
        nrApr =  rootview.findViewById(R.id.nrApCifre);
        tipAp=rootview.findViewById(R.id.profit_tip_ap);
        rezultat=rootview.findViewById(R.id.tw_rezultat);
        calculeazanr=rootview.findViewById(R.id.calculeaza);
        btn_citeste=rootview.findViewById(R.id.btn_citeste_raport);
        btn_salveaza=rootview.findViewById(R.id.btn_salveaza_raport);
        String[] tipApartamentarray=getResources().getStringArray(R.array.tip_apartament);
        Collections.addAll(listatipurideApartamente,tipApartamentarray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, listatipurideApartamente);
        tipAp.setAdapter(adapter);
        SharedPreferences preferences = getActivity().getSharedPreferences("valoare", MODE_PRIVATE);
        try {
            sumaChirii = getPretTotal(0);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String sumaChiriiString = String.valueOf(sumaChirii);
        calcul.setText(sumaChiriiString);

        calculeazanr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    nrApr.setText(String.valueOf(getNrTotal(tipAp.getText().toString())));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        btn_salveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salveazaFisier(rootview);
            }
        });
        btn_citeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                citesteFisier(rootview);
            }
        });
        return rootview;

    }
    public int getPretTotal (int id) throws ExecutionException, InterruptedException {
        return new ApartamentRepository.GetPretTotal(database.getapartamentDao()).execute(id).get();
    }
    public int getNrTotal (String tip) throws ExecutionException, InterruptedException {
        return new ApartamentRepository.GetNrTotal(database.getapartamentDao()).execute(tip).get();
    }

    public void salveazaFisier(View view) {

        String nrAptext= getString(R.string.nrtotal)+ tipAp.getText().toString()+ " este de " + nrApr.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = getActivity().openFileOutput(fileName, MODE_PRIVATE);
            fos.write(nrAptext.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void citesteFisier(View view) {
        FileInputStream fis = null;
        try {
           fis = getActivity().openFileInput(fileName);
            BufferedReader buf = new BufferedReader(new InputStreamReader(fis));
            String text = null;
            while((text = buf.readLine())!=null){
                rezultat.setText(text);
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}