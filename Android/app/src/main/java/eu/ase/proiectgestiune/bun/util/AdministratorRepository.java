package eu.ase.proiectgestiune.bun.util;
import android.app.Application;
import android.os.AsyncTask;
import eu.ase.proiectgestiune.bun.database.ApartamentDatabase;
public class AdministratorRepository {
    private AdministratorDao administratorDao;
    public AdministratorRepository(Application application) //context
    {
        UserDatabase database= UserDatabase.getInstance(application);
        administratorDao=database.getAdministratorDao();


    }
    public static class  InsertAdminAsyncTask extends AsyncTask<Administrator,Void, Void> {
        private AdministratorDao administratorDao; //database operations

        public InsertAdminAsyncTask(AdministratorDao administratorDao)
        {
            this.administratorDao=administratorDao;
        }
        @Override
        protected Void doInBackground(Administrator... administrators) {
            administratorDao.insertAdministrator(administrators[0]);
            return null;
        }


    }
    public static class  GetUsername extends AsyncTask<String, Void, String> {
        private AdministratorDao administratorDao; //database operations
        public GetUsername(AdministratorDao administratorDao)
        {
            this.administratorDao=administratorDao;
        }

        @Override
        protected String doInBackground(String... strings) {
            return administratorDao.getUsername(strings[0]);
        }
    }

    public static class  GetParola extends AsyncTask<String, Void, String> {
        private AdministratorDao administratorDao; //database operations
        public GetParola(AdministratorDao administratorDao)
        {
            this.administratorDao=administratorDao;
        }

        @Override
        protected String doInBackground(String... strings) {
            return administratorDao.getParola(strings[0]);
        }
    }
    public static class DeleteAll extends AsyncTask<  Void, Void, Void> {
        private AdministratorDao administratorDao;

        public DeleteAll(AdministratorDao administratorDao) {
            this.administratorDao=administratorDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
                administratorDao.deleteAllAdmins();
            return null;
        }
    }

}
