package eu.ase.proiectgestiune.bun.util;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface AdministratorDao {
    @Insert
    public void insertAdministrator(Administrator administrator);

    @Query("select username from Administrator where nume =:nume ")
    String getUsername(String nume);

    @Query("select parola from Administrator where nume =:nume")
    String getParola(String nume);

    @Query("DELETE FROM Administrator")
    void deleteAllAdmins();
}
