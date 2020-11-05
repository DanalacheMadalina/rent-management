package eu.ase.proiectgestiune.bun.database;

import android.content.Context;
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

public class ClientAdaptor  extends ArrayAdapter<Client> {
    Context context;
    Integer resource;
    List<Client> listaclienti= new ArrayList<>();
    LayoutInflater inflater;
    TextView numeclient;
    TextView numartelefonClient;
    TextView cnpClient;
    ImageView pozaClient;

    public ClientAdaptor(@NonNull Context context, int resource, @NonNull List<Client> objects, LayoutInflater layoutInflater) {
        super(context, 0, objects);
        this.context = context;
        this.resource=resource;
        this.listaclienti = objects;
        this.inflater=layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root= convertView;

        if(root==null)
        {
            root=inflater.inflate(R.layout.fragment_detaliiclienti,parent,false);
        }
        Client clientCurent=listaclienti.get(position);
        if(clientCurent!=null)
        {
            addnumeClient(root,clientCurent.getNumeCient());
            addnumartelefonClient(root, clientCurent.getNumarTelefon());
            addcnpClient(root,clientCurent.getCnpClient());
            addpozaClient(root,clientCurent.getPozaClient());
        }

        return root;
    }

    private void addnumeClient(View view, String numec)
    { numeclient= (TextView) view.findViewById(R.id.dc_numeClient);
        if(numec!=null)
        {numeclient.setText(numec);}
        else
            numeclient.setText("-");
    }

    private void addnumartelefonClient(View view, Long nrtel)
    { numartelefonClient= (TextView) view.findViewById(R.id.dc_numarTelefon);
        if(nrtel!=null)
        {numartelefonClient.setText(String.valueOf(nrtel));}
        else
            numartelefonClient.setText("-");
    }

    private void addcnpClient(View view, Long cnp)
    { cnpClient= (TextView) view.findViewById(R.id.dc_cnp);
        if(cnp!=null )
        {cnpClient.setText(String.valueOf(cnp));}
        else
            cnpClient.setText("-");
    }
    private void addpozaClient(View view, Integer poza)
    {
        pozaClient=(ImageView) view.findViewById(R.id.dc_pozaclient);
        pozaClient.setImageResource(poza);
    }

}
