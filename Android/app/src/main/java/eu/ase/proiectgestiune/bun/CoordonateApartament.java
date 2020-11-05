package eu.ase.proiectgestiune.bun;

import android.os.Parcel;
import android.os.Parcelable;

public class CoordonateApartament implements Parcelable {
    private Double latitude;
    private Double longitude;
    private String strada;

    public CoordonateApartament(Double latitude, Double longitude, String strada) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.strada = strada;
    }

    protected CoordonateApartament(Parcel in) {
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
        strada = in.readString();
    }

    public static final Creator<CoordonateApartament> CREATOR = new Creator<CoordonateApartament>() {
        @Override
        public CoordonateApartament createFromParcel(Parcel in) {
            return new CoordonateApartament(in);
        }

        @Override
        public CoordonateApartament[] newArray(int size) {
            return new CoordonateApartament[size];
        }
    };

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longitude);
        }
        dest.writeString(strada);
    }
}