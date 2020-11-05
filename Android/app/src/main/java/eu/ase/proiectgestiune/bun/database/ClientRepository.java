package eu.ase.proiectgestiune.bun.database;
import android.app.Application;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;


public class ClientRepository {
    private ClientDao clientDao;
    private List<Client> allclients = new ArrayList<>();
    public ClientRepository(Application application) //context
    {
        ApartamentDatabase database= ApartamentDatabase.getInstance(application);
        clientDao=database.getClientDao();
        allclients=clientDao.afiseazaClienti();

    }
    public static class  InsertClientAsyncTask extends AsyncTask<Client,Void, Void> {
        private ClientDao clientDao; //database operations

        public InsertClientAsyncTask(ClientDao clientDao)
        {
            this.clientDao=clientDao;
        }
        @Override
        protected Void doInBackground(Client... clients) {
            clientDao.insertClient(clients[0]);
            return null;
        }


    }
    public static class GetAll
            extends AsyncTask<Void, Void, List<Client>> {
        private ClientDao clientDao; //database operations

        public GetAll(ClientDao clientDao)
        {
            this.clientDao=clientDao;
        }
        @Override
        protected List<Client> doInBackground(Void... voids) {
            return clientDao.afiseazaClienti();
        }
    }

    public static class DeleteAll extends AsyncTask<  Void, Void, Void> {
        private ClientDao clientDao;

        public DeleteAll(ClientDao clientDao) {
            this.clientDao=clientDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            clientDao.deleteAllClients();
            return null;
        }
    }

}
