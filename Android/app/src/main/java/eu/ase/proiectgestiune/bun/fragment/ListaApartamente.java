package eu.ase.proiectgestiune.bun.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.util.Calendar;

import eu.ase.proiectgestiune.bun.AdaugaApartament;
import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.CoordonateApartament;
import eu.ase.proiectgestiune.bun.Lista_Ap_Libere;

import eu.ase.proiectgestiune.bun.Lista_Ap_Ocupate;

import eu.ase.proiectgestiune.bun.VizualizareMap;
import eu.ase.proiectgestiune.bun.util.ApartamentAdapter;


import static android.content.Context.MODE_PRIVATE;
import static eu.ase.proiectgestiune.bun.MainActivity.PREFS_NAME;
import static eu.ase.proiectgestiune.bun.MainActivity.PREF_USERNAME;


public class ListaApartamente extends Fragment {
    private Button btn_ap_libere;
    private Button btn_ap_ocupate;
    private Button btn_adauga_apartament;
    private Button btn_datepicker;
    private TextView welcome;
    private TextView tvdata;
    private ApartamentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_listaapartamente, container, false);
        tvdata=(TextView) rootview.findViewById(R.id.tw_data);
        welcome = rootview.findViewById(R.id.welcome);
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String nume = preferences.getString(PREF_USERNAME, welcome.toString());
        String afisareWelcome = getString(R.string.welcome)+nume  + "!";
        welcome.setText(afisareWelcome);
        btn_ap_libere = (Button) rootview.findViewById(R.id.btn_apLibere);
        btn_ap_libere.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Lista_Ap_Libere.class);
                startActivity(intent);
            }
        });

        btn_ap_ocupate = (Button) rootview.findViewById(R.id.btn_apOcupate);
        btn_ap_ocupate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Lista_Ap_Ocupate.class);
                startActivity(intent);
            }
        });

        btn_adauga_apartament = (Button) rootview.findViewById(R.id.btn_adauga_apartament);
        btn_adauga_apartament.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), AdaugaApartament.class);
                startActivity(intent1);
            }
        });


        return rootview;
    }


}





