package eu.ase.proiectgestiune.bun.util;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Administrator")
public class Administrator
{

    private String nume;
    private String prenume;
    @PrimaryKey
    @NonNull
    private String username;
    private String parola;

    public Administrator(String nume, String prenume, String username, String parola) {
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
}
