package eu.ase.proiectgestiune.bun.fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import eu.ase.proiectgestiune.R;
import eu.ase.proiectgestiune.bun.database.ApartamentDao;
import eu.ase.proiectgestiune.bun.database.ApartamentDatabase;
import eu.ase.proiectgestiune.bun.database.ClientDao;
import eu.ase.proiectgestiune.bun.database.ClientRepository;
import eu.ase.proiectgestiune.bun.database.Client;
import eu.ase.proiectgestiune.bun.database.ClientAdaptor;
import eu.ase.proiectgestiune.bun.util.Apartament;

public class DetaliiClienti extends Fragment {
   ListView lvClienti;
    ClientAdaptor adaptor;
    List<Client> listaClient= new ArrayList<>();
    ApartamentDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.list_view_general, container,false);
         database= ApartamentDatabase.getInstance(getContext());
        lvClienti=root.findViewById(R.id.lw_general);

        //deleteallClients();
        //insertClient();
        try {
            listaClient=getAllClients();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        adaptor= new ClientAdaptor(getContext(),R.layout.fragment_detaliiclienti,listaClient,getLayoutInflater());
        lvClienti.setAdapter(adaptor);
        return root;
    }

//    public void insertClient(Client client){
//
//        new ClientRepository.InsertClientAsyncTask(database.getClientDao()).execute(client);
//    }

public void insertClient(){
    AsyncTask.execute(new Runnable() {
        @Override
        public void run() {
            ClientDao clientDao = ApartamentDatabase.getInstance(getContext()).getClientDao();
            clientDao.insertClient(new Client("Popescu Adrian", 40741758386L, 100165869451L, R.drawable.man,9 ));
              clientDao.insertClient(new Client("Ana Maria",40712589351L,2002895602047L,R.drawable.girl ,8));
        }

    });
}
    public List<Client> getAllClients() throws ExecutionException, InterruptedException {
        return new ClientRepository.GetAll(database.getClientDao()).execute().get();
    }

    public void deleteallClients() {
        if(database.getClientDao()!=null)
            new ClientRepository.DeleteAll(database.getClientDao()).execute();

    }
}
