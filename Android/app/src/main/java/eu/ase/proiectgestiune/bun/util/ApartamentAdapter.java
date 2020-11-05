package eu.ase.proiectgestiune.bun.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.AdaugaApartament;
import eu.ase.proiectgestiune.bun.fragment.ListaApartamente;

import static androidx.recyclerview.widget.RecyclerView.*;

public class ApartamentAdapter extends ArrayAdapter<Apartament> {
    private Context mcontext;
    private int resource;
    private List<Apartament> listaDEapartamente =new ArrayList<>();
    private LayoutInflater inflater;

    public ApartamentAdapter(@NonNull Context context,int resource, @NonNull List<Apartament> apartamente, LayoutInflater layoutInflater) {
        super(context,0, apartamente);
        this.mcontext = context;
        this.resource=resource;
        this.listaDEapartamente = apartamente;
        this.inflater=layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    if(convertView==null)
    {
        inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(R.layout.lista_libere, parent, false);
    }
            Apartament apartamentulCurent = listaDEapartamente.get(position);
            if(apartamentulCurent!=null) {
               addLocatie(convertView,apartamentulCurent.getLocatieAp());
                addAn(convertView, apartamentulCurent.getAnInfiintare());
                addPret(convertView, apartamentulCurent.getPretChirie());
                addStructura(convertView, apartamentulCurent.getStructura());
                addTip(convertView, apartamentulCurent.getTipApartament());
                addSuprafata(convertView, apartamentulCurent.getSuprafata());
               addDisponibilitate(convertView,apartamentulCurent.getDisponibilitate());
            }
            return convertView;

        }
        private void addLocatie(View view ,String locatieS)
        {
            TextView locatie = (TextView) view.findViewById(R.id.locatiaApr);
            if(locatieS!=null && !locatieS.trim().isEmpty())
            {
                locatie.setText(locatieS);
            }
            else locatie.setText("-");
        }
    private void addSuprafata(View view, Integer suprafata1) {
        TextView suprafata = (TextView) view.findViewById(R.id.suprafataApar);
        if (suprafata1 != null) {
            suprafata.setText(String.valueOf(suprafata1));
        } else {
            suprafata.setText("-");
        }
    }
    private void addAn(View view, Integer an1) {
        TextView anInfiintare=(TextView)view.findViewById(R.id.anblocApar) ;
        if (an1 != null) {
            anInfiintare.setText(String.valueOf(an1));
        } else {
            anInfiintare.setText("-");
        }
    }
    private void addPret(View view, Integer pret1) {
        TextView pret = (TextView) view.findViewById(R.id.pretChirieApar);
        if (pret1 != null) {
            pret.setText(String.valueOf(pret1));
        } else {
            pret.setText("-");
        }
    }
    private void addTip(View view ,String tips)
    {
        TextView tipApartament = (TextView) view.findViewById(R.id.tipulApar);
        if(tips!=null && !tips.trim().isEmpty())
        {
            tipApartament.setText(tips);
        }
        else tipApartament.setText("-");
    }
    private void addStructura(View view ,String structuras)
    {
        TextView structura = (TextView)view.findViewById(R.id.structuraApar);
        if(structuras!=null && !structuras.trim().isEmpty())
        {
            structura.setText(structuras);
        }
        else structura.setText("-");
    }
    private void addDisponibilitate(View view ,Integer disponibilitateb)
    {
        TextView disponibilitate = (TextView)view.findViewById(R.id.disponibilitate);
        if(disponibilitateb==1)
        disponibilitate.setText("disponibil");
        else
            disponibilitate.setText("ocupat");


    }

    public void setListaDEapartamente(List<Apartament> listaDEapartamente) {
        this.listaDEapartamente = listaDEapartamente;
        Log.i("adapter empty","da");
        notifyDataSetChanged();
    }
}


