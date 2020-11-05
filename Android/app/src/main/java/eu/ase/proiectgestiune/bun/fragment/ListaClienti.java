package eu.ase.proiectgestiune.bun.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import eu.ase.proiectgestiune.R;

import eu.ase.proiectgestiune.bun.network.HttpManager;
import eu.ase.proiectgestiune.bun.network.HttpResponse;
import eu.ase.proiectgestiune.bun.network.JsonParser;
import eu.ase.proiectgestiune.bun.network.ListaTranzactiiClientiAdapter;
import eu.ase.proiectgestiune.bun.network.Plata;


public class ListaClienti extends Fragment {
    private static final String URL="https://pastebin.com/raw/fBFDEfew";
    private HttpResponse httpResponse;
    private Button buton_an2017;
    private Button buton_an2018;
    private Button buton_an2019;
    private ListView lvResponse;int sum = 0;
    private List<Plata> selectedResponse = new ArrayList<>();
    private ListaTranzactiiClientiAdapter adapter;

    @SuppressLint("StaticFieldLeak")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clienti, container, false);
        initComponents(view);
        new HttpManager() {
            @Override
            protected void onPostExecute(String s) {
                httpResponse = JsonParser.parseJson(s);
                if (httpResponse != null) {
                    Toast.makeText(getContext(),
                            R.string.connection_succesfully,
                            Toast.LENGTH_LONG).show();
                }
            }
        }.execute(URL);

        return view;


}
    private void initComponents(View view) {
        buton_an2017 = view.findViewById(R.id.btn_an2017);
        buton_an2018 = view.findViewById(R.id.btn_an2018);
        buton_an2019 = view.findViewById(R.id.btn_an2019);
        lvResponse = view.findViewById(R.id.plataLV);
        adapter = new ListaTranzactiiClientiAdapter(getContext(), android.R.layout.simple_list_item_1, selectedResponse,getLayoutInflater());
        lvResponse.setAdapter(adapter);

        buton_an2017.setOnClickListener(new View
                .OnClickListener() {
            @Override
            public void onClick(View view) {
                if (httpResponse != null && httpResponse
                        .getAn2017() != null) {
                    selectResponse(httpResponse.
                            getAn2017());

                }
            }
        });

        buton_an2018.setOnClickListener(new View
                .OnClickListener() {
            @Override
            public void onClick(View view) {
                if (httpResponse != null && httpResponse
                        .getAn2018() != null) {
                    selectResponse(httpResponse.
                            getAn2018());
                }
            }
        });
        buton_an2019.setOnClickListener(new View
                .OnClickListener() {
            @Override
            public void onClick(View view) {
                if (httpResponse != null && httpResponse
                        .getAn2019() != null) {
                    selectResponse(httpResponse.
                            getAn2019());
                }
            }
        });

    }

    private void selectResponse(List<Plata> list) {
        selectedResponse.clear();
        selectedResponse.addAll(list);
        ArrayAdapter<Plata> adapter = (ArrayAdapter<Plata>) lvResponse.getAdapter();
        adapter.notifyDataSetChanged();
    }
}

