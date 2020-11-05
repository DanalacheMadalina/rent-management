package eu.ase.proiectgestiune.bun.util;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Administrator.class},exportSchema = false,version =2)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;

    public abstract AdministratorDao getAdministratorDao();
    public static synchronized  UserDatabase getInstance(Context context){
        if(instance==null) {
            instance= Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,"user_database").fallbackToDestructiveMigration().build();  //builder in loc de new doarece e clasa abstracta
        }

        return instance;
    }
}
