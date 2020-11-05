package eu.ase.proiectgestiune.bun.network;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;
import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.util.Apartament;


public class ListaTranzactiiClientiAdapter extends ArrayAdapter<Plata> {
    private Context context;
    private int resource;
    private List<Plata> listaTranzactii=new ArrayList<>();
    private LayoutInflater inflater;
    TextView numeClient;
    TextView nrluniContract;
    TextView stradaAp;
    TextView numarBloc;
    TextView scaraBloc;
    TextView numarApartament;
    TextView pretChirieApartament;
    TextView tipApartament;
    public ListaTranzactiiClientiAdapter(@NonNull Context context, int resource, @NonNull List<Plata> listaTranzactii, LayoutInflater layoutInflater) {
        super(context,0, listaTranzactii);
        this.context = context;
        this.resource=resource;
        this.listaTranzactii = listaTranzactii;
        this.inflater=layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root= convertView;
        if(root==null)
        {
            root= inflater.inflate(R.layout.lista_tranzactii,parent,false);

        }
        Plata plataCurenta = listaTranzactii.get(position);
        if(plataCurenta!=null) {
            addNumeClient(root,plataCurenta.getNumeClient());
            addnrluniContract(root, plataCurenta.getNumarLuni());
            addstradaAp(root, plataCurenta.getApartamentInfo().getLocatieinfo().getStrada());
            addnumarBloc(root, plataCurenta.getApartamentInfo().getLocatieinfo().getNrBloc());
            addscaraBloc(root, plataCurenta.getApartamentInfo().getLocatieinfo().getScara());
            addnumarApartament(root, plataCurenta.getApartamentInfo().getLocatieinfo().getNumarApartament());
            addpretChirieApartament(root, plataCurenta.getApartamentInfo().getPretChirie());
            addtipApartament(root, plataCurenta.getApartamentInfo().getTipApartament());
        }

        return root;
    }

    private void addNumeClient(View view ,String numec) {
        numeClient = (TextView) view.findViewById(R.id.tr_numeclient);
        numeClient.setText(numec);
    }
    private void addnrluniContract(View view ,Integer nrluni) {
        nrluniContract = (TextView) view.findViewById(R.id.tr_nrLuniContract);
        nrluniContract.setText(String.valueOf(nrluni));

    }
    private void addstradaAp(View view ,String stradaApar) {
        stradaAp = (TextView) view.findViewById(R.id.tr_stradaApartament);
        stradaAp.setText(stradaApar);
    }
    private void addnumarBloc(View view ,Integer numarb) {
        numarBloc = (TextView) view.findViewById(R.id.tr_numarBloc);
        numarBloc.setText(String.valueOf(numarb));
    }
    private void addscaraBloc(View view ,String scaraB) {
        scaraBloc = (TextView) view.findViewById(R.id.tr_scaraBloc);
        scaraBloc.setText(scaraB);
    }
    private void addnumarApartament(View view ,Integer nrapr) {
        numarApartament = (TextView) view.findViewById(R.id.tr_numarApartament);
        numarApartament.setText(String.valueOf(nrapr));
    }
    private void addpretChirieApartament(View view ,Integer pretCh) {
        pretChirieApartament = (TextView) view.findViewById(R.id.tr_pretChirieApar);
        pretChirieApartament.setText(String.valueOf(pretCh));
    }
    private void addtipApartament(View view ,String tipApar) {
        tipApartament = (TextView) view.findViewById(R.id.tr_tipapartament);
        tipApartament.setText(tipApar);
    }
}
