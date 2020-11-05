package eu.ase.proiectgestiune.bun.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class EvidentaCheltuieli implements Parcelable {
    String idCh;
    String descriereCh;
    Integer sumaCh;
    String datach;

    public EvidentaCheltuieli(String idCh, String descriereCh, Integer sumaCh, String datach) {
        this.idCh = idCh;
        this.descriereCh = descriereCh;
        this.sumaCh = sumaCh;
        this.datach = datach;
    }

    public String getIdCh() {
        return idCh;
    }

    public void setIdCh(String idCh) {
        this.idCh = idCh;
    }
//    public EvidentaCheltuieli(String descriereCh, Integer sumaCh, String datach) {
//        this.descriereCh = descriereCh;
//        this.sumaCh = sumaCh;
//        this.datach = datach;
//    }
public EvidentaCheltuieli(){};
    protected EvidentaCheltuieli(Parcel in) {
        descriereCh = in.readString();
        if (in.readByte() == 0) {
            sumaCh = null;
        } else {
            sumaCh = in.readInt();
        }
        datach = in.readString();
    }

    public static final Creator<EvidentaCheltuieli> CREATOR = new Creator<EvidentaCheltuieli>() {
        @Override
        public EvidentaCheltuieli createFromParcel(Parcel in) {
            return new EvidentaCheltuieli(in);
        }

        @Override
        public EvidentaCheltuieli[] newArray(int size) {
            return new EvidentaCheltuieli[size];
        }
    };

    public String getDescriereCh() {
        return descriereCh;
    }

    public void setDescriereCh(String descriereCh) {
        this.descriereCh = descriereCh;
    }

    public Integer getSumaCh() {
        return sumaCh;
    }

    public void setSumaCh(Integer sumaCh) {
        this.sumaCh = sumaCh;
    }

    public String getDatach() {
        return datach;
    }

    public void setDatach(String datach) {
        this.datach = datach;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(descriereCh);
        if (sumaCh == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sumaCh);
        }
        dest.writeString(datach);
    }
}
