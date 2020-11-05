package eu.ase.proiectgestiune.bun.network;

public class ApartamentInfo {
    private String tipApartament;
    private LocatieApartament locatieinfo;
    private  Integer pretChirie;

    public ApartamentInfo(String tipApartament, LocatieApartament locatieinfo, Integer pretChirie) {
        this.tipApartament = tipApartament;
        this.locatieinfo = locatieinfo;
        this.pretChirie = pretChirie;
    }

    public String getTipApartament() {
        return tipApartament;
    }

    public void setTipApartament(String tipApartament) {
        this.tipApartament = tipApartament;
    }

    public LocatieApartament getLocatieinfo() {
        return locatieinfo;
    }

    public void setLocatieinfo(LocatieApartament locatieinfo) {
        this.locatieinfo = locatieinfo;
    }

    public Integer getPretChirie() {
        return pretChirie;
    }

    public void setPretChirie(Integer pretChirie) {
        this.pretChirie = pretChirie;
    }

    @Override
    public String toString() {
        return "Tip: " + tipApartament  +
                "\n"+"Locatie: " + locatieinfo +
                "\n"+ "Pret chirie: " + pretChirie +" euro" ;
    }
}
