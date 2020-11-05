package eu.ase.proiectgestiune.bun.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.database.Client;


public class EvidentaCheltuieliAdaptor extends ArrayAdapter<EvidentaCheltuieli> {
    Context context;
    Integer resource;
    List<EvidentaCheltuieli> listaCheltuieli= new ArrayList<>();
    LayoutInflater inflater;
    TextView descrierech;
    TextView pretch;
    TextView datach;

    public EvidentaCheltuieliAdaptor(@NonNull Context context, int resource, @NonNull List<EvidentaCheltuieli> objects, LayoutInflater layoutInflater) {
        super(context, 0, objects);
        this.context = context;
        this.resource=resource;
        this.listaCheltuieli = objects;
        this.inflater=layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listacheltuieli, parent, false);
        }
        EvidentaCheltuieli cheltuialaCurenta=listaCheltuieli.get(position);
        if(cheltuialaCurenta!=null)
        {
            addDescriereCh(convertView,cheltuialaCurenta.getDescriereCh());
            addcostCh(convertView, cheltuialaCurenta.getSumaCh());
            addData(convertView,cheltuialaCurenta.getDatach());
        }

        return convertView;
    }
    private void addDescriereCh(View view, String descriere)
    { descrierech= (TextView) view.findViewById(R.id.tw_detalii);
        if(descriere!=null)
        {descrierech.setText(descriere);}
        else
            descrierech.setText("-");
    }

    private void addcostCh(View view, Integer suma)
    { pretch= (TextView) view.findViewById(R.id.tw_suma);
        if(suma!=null)
        {pretch.setText(String.valueOf(suma));}
        else
            pretch.setText("-");
    }
    private void addData(View view, String dataaleasa)
    { datach= (TextView) view.findViewById(R.id.tw_data);
        if(dataaleasa!=null)
        {datach.setText(dataaleasa);}
        else
            datach.setText("-");
    }


}
