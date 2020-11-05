package eu.ase.proiectgestiune.bun.util;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "Apartament")
public class Apartament  implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private Integer codUnic;
    private String locatieAp;
    private Integer suprafata;
    private Integer anInfiintare;
    private Integer pretChirie;
    private String tipApartament;
    private String structura;
    private Integer disponibilitate;


    public Apartament(Integer codUnic, String locatieAp, Integer suprafata, Integer anInfiintare, Integer pretChirie, String tipApartament, String structura) {
        this.codUnic = codUnic;
        this.locatieAp = locatieAp;
        this.suprafata = suprafata;
        this.anInfiintare = anInfiintare;
        this.pretChirie = pretChirie;
        this.tipApartament = tipApartament;
        this.structura = structura;
        this.disponibilitate = 1;
    }

    @Ignore
    public Apartament( String locatieAp, Integer suprafata, Integer anInfiintare, Integer pretChirie, String tipApartament, String structura, Integer disponibilitate) {

        this.locatieAp = locatieAp;
        this.suprafata = suprafata;
        this.anInfiintare = anInfiintare;
        this.pretChirie = pretChirie;
        this.tipApartament = tipApartament;
        this.structura = structura;
        this.disponibilitate = disponibilitate;
    }



    @Override
    public String toString() {
        return "Apartament{" +
                "locatieAp='" + locatieAp + '\'' +
                ", suprafata=" + suprafata +
                ", anInfiintare=" + anInfiintare +
                ", pretChirie=" + pretChirie +
                ", tipApartament='" + tipApartament + '\'' +
                ", structura='" + structura + '\'' +
                '}';
    }

    protected Apartament(Parcel in) {
        if (in.readByte() == 0) {
            codUnic = null;
        } else {
            codUnic = in.readInt();
        }
        locatieAp = in.readString();
        if (in.readByte() == 0) {
            suprafata = null;
        } else {
            suprafata = in.readInt();
        }
        if (in.readByte() == 0) {
            anInfiintare = null;
        } else {
            anInfiintare = in.readInt();
        }
        if (in.readByte() == 0) {
            pretChirie = null;
        } else {
            pretChirie = in.readInt();
        }
        tipApartament = in.readString();
        structura = in.readString();
    }

    public static final Creator<Apartament> CREATOR = new Creator<Apartament>() {
        @Override
        public Apartament createFromParcel(Parcel in) {
            return new Apartament(in);
        }

        @Override
        public Apartament[] newArray(int size) {
            return new Apartament[size];
        }
    };

    public Integer getCodUnic() {
        return codUnic;
    }

    public void setCodUnic(Integer codUnic) {
        this.codUnic = codUnic;
    }

    public Integer getDisponibilitate() {
        return disponibilitate;
    }

    public void setDisponibilitate(Integer disponibilitate) {
        this.disponibilitate = disponibilitate;
    }

    public String getLocatieAp() {
        return locatieAp;
    }

    public void setLocatieAp(String locatieAp) {
        this.locatieAp = locatieAp;
    }

    public Integer getSuprafata() {
        return suprafata;
    }

    public void setSuprafata(Integer suprafata) {
        this.suprafata = suprafata;
    }

    public Integer getAnInfiintare() {
        return anInfiintare;
    }

    public void setAnInfiintare(Integer anInfiintare) {
        this.anInfiintare = anInfiintare;
    }

    public Integer getPretChirie() {
        return pretChirie;
    }

    public void setPretChirie(Integer pretChirie) {
        this.pretChirie = pretChirie;
    }

    public String getTipApartament() {
        return tipApartament;
    }

    public void setTipApartament(String tipApartament) {
        this.tipApartament = tipApartament;
    }

    public String getStructura() {
        return structura;
    }

    public void setStructura(String structura) {
        this.structura = structura;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (codUnic == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(codUnic);
        }
        dest.writeString(locatieAp);
        if (suprafata == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(suprafata);
        }
        if (anInfiintare == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(anInfiintare);
        }
        if (pretChirie == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(pretChirie);
        }
        dest.writeString(tipApartament);
        dest.writeString(structura);
    }
}