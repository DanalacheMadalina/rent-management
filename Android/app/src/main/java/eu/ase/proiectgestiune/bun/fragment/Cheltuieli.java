package eu.ase.proiectgestiune.bun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.AdaugaApartament;
import eu.ase.proiectgestiune.bun.AdaugaCheltuiala;
import eu.ase.proiectgestiune.bun.ListaCheltuieli;
import eu.ase.proiectgestiune.bun.Lista_Ap_Libere;
import eu.ase.proiectgestiune.bun.database.ClientAdaptor;
import eu.ase.proiectgestiune.bun.util.EvidentaCheltuieli;
import eu.ase.proiectgestiune.bun.util.EvidentaCheltuieliAdaptor;

import static android.app.Activity.RESULT_OK;


public class Cheltuieli extends Fragment {
    Button btn_adauga;
    Button btn_afiseaza;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.lista_cheltuieli, container,false);

        btn_adauga=root.findViewById(R.id.btn_adaugacheltuieli);
        btn_adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AdaugaCheltuiala.class);
                startActivity(intent);
            }
        });

        btn_afiseaza=root.findViewById(R.id.btn_afiseazaCheltuieli);
        btn_afiseaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListaCheltuieli.class);
                startActivity(intent);
            }
        });
        return root;

    }

}