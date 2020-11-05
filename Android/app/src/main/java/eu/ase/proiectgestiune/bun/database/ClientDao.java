package eu.ase.proiectgestiune.bun.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
@Dao
public interface ClientDao {
    @Insert
    void insertClient(Client client);
    @Query("select * from Client")
    List<Client> afiseazaClienti();

    @Query("DELETE FROM Client")
    void deleteAllClients();

}
