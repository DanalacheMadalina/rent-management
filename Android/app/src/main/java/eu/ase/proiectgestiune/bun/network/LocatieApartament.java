package eu.ase.proiectgestiune.bun.network;

public class LocatieApartament {

    private String strada;
    private Integer nrBloc;
    private String scara;
    private Integer numarApartament;

    public LocatieApartament( String strada, Integer nrBloc, String scara, Integer numarApartament) {

        this.strada = strada;
        this.nrBloc = nrBloc;
        this.scara = scara;
        this.numarApartament = numarApartament;
    }



    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public Integer getNrBloc() {
        return nrBloc;
    }

    public void setNrBloc(Integer nrBloc) {
        this.nrBloc = nrBloc;
    }

    public String getScara() {
        return scara;
    }

    public void setScara(String scara) {
        this.scara = scara;
    }

    public Integer getNumarApartament() {
        return numarApartament;
    }

    public void setNumarApartament(Integer numarApartament) {
        this.numarApartament = numarApartament;
    }

    @Override
    public String toString() {
        return " strada: " + strada  +
                 ", nr. bloc: " + nrBloc +
                ", scara: " + scara +
                ", numar apartament: " + numarApartament;
    }
}
