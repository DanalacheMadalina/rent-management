package eu.ase.proiectgestiune.bun.network;

import java.util.List;

public class HttpResponse {
    private List<Plata> an2017;
    private List<Plata> an2018;
    private List<Plata> an2019;

    public HttpResponse(List<Plata> an2017, List<Plata> an2018, List<Plata> an2019) {
        this.an2017 = an2017;
        this.an2018 = an2018;
        this.an2019 = an2019;
    }

    public List<Plata> getAn2017() {
        return an2017;
    }

    public void setAn2017(List<Plata> an2017) {
        this.an2017 = an2017;
    }

    public List<Plata> getAn2018() {
        return an2018;
    }

    public void setAn2018(List<Plata> an2018) {
        this.an2018 = an2018;
    }

    public List<Plata> getAn2019() {
        return an2019;
    }

    public void setAn2019(List<Plata> an2019) {
        this.an2019 = an2019;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "an2017=" + an2017 +
                ", an2018=" + an2018 +
                ", an2019=" + an2019 +
                '}';
    }
}
