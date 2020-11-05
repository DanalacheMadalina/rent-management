package eu.ase.proiectgestiune.bun.database;
import android.content.Context;
import android.util.Log;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import eu.ase.proiectgestiune.bun.util.AdministratorDao;
import eu.ase.proiectgestiune.bun.util.Apartament;


@Database(entities = {Apartament.class, Client.class},exportSchema = false,version =1)
public abstract class ApartamentDatabase extends RoomDatabase {

    private static ApartamentDatabase instance;
    public abstract ApartamentDao getapartamentDao();//sa accesam clasa Dao
    public abstract ClientDao getClientDao();
    public static synchronized  ApartamentDatabase getInstance(Context context){
        if(instance==null) {
           instance= Room.databaseBuilder(context.getApplicationContext(),ApartamentDatabase.class,"apartament_database").fallbackToDestructiveMigration().build();  //builder in loc de new doarece e clasa abstracta
        }

        return instance;
    }

}
