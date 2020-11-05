package eu.ase.proiectgestiune.bun.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import eu.ase.proiectgestiune.bun.util.Apartament;

@Dao
public interface ApartamentDao {

    @Insert
    public void insertApartament(Apartament apartament);

    @Update
    public void updateApartament(Apartament apartament);

    @Delete
    public void deleteApartament(Apartament apartament);

    @Query("select * from Apartament")
    List<Apartament>getallapartments();

    @Query("select * from Apartament where disponibilitate = :disponibilitate  ORDER BY pretChirie")
    List<Apartament> getApartmentsDisponibilitate(int disponibilitate);

    @Query("select SUM(pretChirie) from Apartament where disponibilitate = :disponibilitate")
   int getPretTotal (int disponibilitate);

    @Query("select count(codUnic) from Apartament where tipApartament=:tipApartament")
    Integer getnrTotal (String tipApartament);
    @Query("DELETE FROM Apartament")
    void deleteAllApartments();


}
