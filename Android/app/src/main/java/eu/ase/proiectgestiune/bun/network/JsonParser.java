package eu.ase.proiectgestiune.bun.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import eu.ase.proiectgestiune.bun.util.Apartament;

public class JsonParser {

    public static HttpResponse parseJson(String json) {
        if (json == null) {
            return null;
        }
        try {


            JSONObject jsonObject = new JSONObject(json);
            List<Plata> anul2017 = getItemListFromJson(jsonObject
                    .getJSONArray("an2017"));
            List<Plata> anul2018 = getItemListFromJson(jsonObject
                    .getJSONArray("an2018"));
            List<Plata> anul2019 = getItemListFromJson(jsonObject
                    .getJSONArray("an2019"));

            return new HttpResponse(anul2017, anul2018, anul2019);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static List<Plata> getItemListFromJson(JSONArray array) throws JSONException {
        if (array == null) {
            return null;
        }

        List<Plata> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Plata plata = getPlataFromJson(array
                    .getJSONObject(i));

            if (plata != null) {
                results.add(plata);
            }
        }
        return results;
    }
    private static Plata getPlataFromJson(JSONObject object) throws JSONException {
        if (object == null) {
            return null;
        }
        String numeClient = object.getString("numeClient");
        Integer numarLuni =object.getInt("numarLuni");
        ApartamentInfo apartamentInfo = getApartamentInfoFromJson(object
                .getJSONObject("apartamentInfo"));
        return new Plata(numeClient, numarLuni, apartamentInfo);
    }
    private static ApartamentInfo getApartamentInfoFromJson(JSONObject object) throws JSONException {
        if (object == null) {
            return null;
        }
        String tipApartament = object.getString("tipApartament");
        LocatieApartament locatieinfo = getLocatieInfoFromJson(object
                .getJSONObject("locatieinfo"));
        Integer pretChirie= object.getInt("pretChirie");
        return new ApartamentInfo(tipApartament,locatieinfo,pretChirie);
    }
    private static LocatieApartament getLocatieInfoFromJson(JSONObject object) throws JSONException {
        if (object == null) {
            return null;
        }
        String strada = object.getString("strada");
       Integer nrBloc= object.getInt("nrBloc");
       String scara=object.getString("scara");
        Integer numarApartament= object.getInt("numarApartament");
        return new LocatieApartament(strada,nrBloc,scara,numarApartament);
    }
}
