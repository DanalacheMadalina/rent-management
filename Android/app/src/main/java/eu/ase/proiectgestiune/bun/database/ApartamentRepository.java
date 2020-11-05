package eu.ase.proiectgestiune.bun.database;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;
import eu.ase.proiectgestiune.bun.util.Apartament;

public class ApartamentRepository {

    private ApartamentDao apartamentDao;
    private List<Apartament> allapartments;

    public ApartamentRepository(Application application) //context
    {
        ApartamentDatabase database = ApartamentDatabase.getInstance(application);
        apartamentDao = database.getapartamentDao();
        allapartments = apartamentDao.getallapartments();
    }
    public static class  InsertApartamentAsyncTask extends AsyncTask<Apartament,Void, Void>{
        private ApartamentDao apartamentDao; //database operations

        public InsertApartamentAsyncTask(ApartamentDao apartamentDao)
        {
            this.apartamentDao=apartamentDao;
        }
        @Override
        protected Void doInBackground(Apartament... apartaments) {
            apartamentDao.insertApartament(apartaments[0]);
            return null;
        }
    }
    public static class  GetPretTotal extends AsyncTask<Integer, Void, Integer> {
        private ApartamentDao apartamentDao; //database operations

        public GetPretTotal(ApartamentDao apartamentDao)
        {
            this.apartamentDao=apartamentDao;
        }


        @Override
        protected Integer doInBackground(Integer... integers) {
            return apartamentDao.getPretTotal(integers[0]);
        }
    }
    public static class  GetNrTotal extends AsyncTask<String, Void, Integer> {
        private ApartamentDao apartamentDao; //database operations
        public GetNrTotal(ApartamentDao apartamentDao)
        {
            this.apartamentDao=apartamentDao;
        }

        @Override
        protected Integer doInBackground(String... strings) {
           return apartamentDao.getnrTotal(strings[0]);
        }
    }
    public static class  UpdateApartamentAsyncTask extends AsyncTask<Apartament,Void, Void>{
        private ApartamentDao apartamentDao; //database operations

        public UpdateApartamentAsyncTask(ApartamentDao apartamentDao)
        {
            this.apartamentDao=apartamentDao;
        }
        @Override
        protected Void doInBackground(Apartament... apartaments) {
            apartamentDao.updateApartament(apartaments[0]);
            return null;
        }
    }
    public static class  DeleteApartamentAsyncTask extends AsyncTask<Apartament,Void, Void>{
        private ApartamentDao apartamentDao; //database operations

        public DeleteApartamentAsyncTask(ApartamentDao apartamentDao)
        {
            this.apartamentDao=apartamentDao;
        }


        @Override
        protected Void doInBackground(Apartament... apartaments) {
            apartamentDao.deleteApartament(apartaments[0]);
            return null;
        }
    }

    public static class DeleteAll extends AsyncTask<  Void, Void, Void> {
        private ApartamentDao apartamentDao;

        public DeleteAll(ApartamentDao apartamentDao) {
            this.apartamentDao=apartamentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(apartamentDao.getallapartments()!=null)
            apartamentDao.deleteAllApartments();
            return null;
        }
    }

public static class GetAll
        extends AsyncTask<Void, Void, List<Apartament>> {

    private ApartamentDao apartamentDao; //database operations

        public GetAll(ApartamentDao apartamentDao)
        {
            this.apartamentDao=apartamentDao;
        }
    @Override
    protected List<Apartament> doInBackground(Void... voids) {
        return apartamentDao.getallapartments();
    }
}
    public static class GetApartmentsDisponibility
            extends AsyncTask<Integer, Void, List<Apartament>> {

        private ApartamentDao apartamentDao; //database operations

        public GetApartmentsDisponibility(ApartamentDao apartamentDao)
        {
            this.apartamentDao=apartamentDao;
        }

        @Override
        protected List<Apartament> doInBackground(Integer... integers) {
            return apartamentDao.getApartmentsDisponibilitate(integers[0]);
        }
    }
}
