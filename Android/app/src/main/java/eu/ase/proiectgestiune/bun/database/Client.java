package eu.ase.proiectgestiune.bun.database;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import eu.ase.proiectgestiune.bun.util.Apartament;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Client",foreignKeys = @ForeignKey(entity = Apartament.class,
        parentColumns = "codUnic",
        childColumns = "codUnic",
        onDelete = CASCADE))
public class Client  implements Parcelable {
    String numeCient;
    Long numarTelefon;
    @PrimaryKey
    Long cnpClient;
    Integer pozaClient;
    @ColumnInfo(name = "codUnic", index = true)
    Integer codUnic;


    public Client(String numeCient, Long numarTelefon, Long cnpClient, Integer pozaClient, Integer codUnic) {
        this.numeCient = numeCient;
        this.numarTelefon = numarTelefon;
        this.cnpClient = cnpClient;
        this.pozaClient = pozaClient;
        this.codUnic = codUnic;
    }

    protected Client(Parcel in) {
        numeCient = in.readString();
        numarTelefon = in.readLong();
        cnpClient = in.readLong();
        if (in.readByte() == 0) {
            pozaClient = null;
        } else {
            pozaClient = in.readInt();
        }
        if (in.readByte() == 0) {
            codUnic = null;
        } else {
            codUnic = in.readInt();
        }
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    public Integer getcodUnic() {
        return codUnic;
    }

    public void setcodUnic(Integer codUnic) {
        this.codUnic = codUnic;
    }

    @Ignore
    public Client(String numeCient, Long numarTelefon, Long cnpClient, Integer pozaClient) {
        this.numeCient = numeCient;
        this.numarTelefon = numarTelefon;
        this.cnpClient = cnpClient;
        this.pozaClient = pozaClient;
    }

    public String getNumeCient() {
        return numeCient;
    }

    public void setNumeCient(String numeCient) {
        this.numeCient = numeCient;
    }

    public Long getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(Long numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public Long getCnpClient() {
        return cnpClient;
    }

    public void setCnpClient(Long cnpClient) {
        this.cnpClient = cnpClient;
    }

    public Integer getCodUnic() {
        return codUnic;
    }

    public void setCodUnic(Integer codUnic) {
        this.codUnic = codUnic;
    }

    public Integer getPozaClient() {
        return pozaClient;
    }

    public void setPozaClient(Integer pozaClient) {
        this.pozaClient = pozaClient;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(numeCient);
        dest.writeLong(numarTelefon);
        dest.writeLong(cnpClient);
        if (pozaClient == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(pozaClient);
        }
        if (codUnic == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(codUnic);
        }
    }


}
