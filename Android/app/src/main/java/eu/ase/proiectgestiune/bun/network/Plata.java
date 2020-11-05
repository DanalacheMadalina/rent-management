package eu.ase.proiectgestiune.bun.network;

import eu.ase.proiectgestiune.bun.util.Apartament;

public class Plata {
 private String numeClient;
 private Integer numarLuni;
 private ApartamentInfo apartamentInfo;

    public Plata(String numeClient, Integer numarLuni, ApartamentInfo apartamentInfo) {
        this.numeClient = numeClient;
        this.numarLuni = numarLuni;
        this.apartamentInfo = apartamentInfo;
    }

    @Override
    public String toString() {
        return "Nume Client: " + numeClient  +
                ", numar luni inchiriat: " + numarLuni +
                "\n" + apartamentInfo ;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public Integer getNumarLuni() {
        return numarLuni;
    }

    public void setNumarLuni(Integer numarLuni) {
        this.numarLuni = numarLuni;
    }

    public ApartamentInfo getApartamentInfo() {
        return apartamentInfo;
    }

    public void setApartamentInfo(ApartamentInfo apartamentInfo) {
        this.apartamentInfo = apartamentInfo;
    }
}
