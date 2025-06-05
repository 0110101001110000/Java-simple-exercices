
package br.unit.seatwise_project.utility;

import br.unit.seatwise_project.model.Chair;
import br.unit.seatwise_project.model.Client;
import br.unit.seatwise_project.model.Reserve;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


// Init -------------------------------------------------------------------- //


public class JsonUtils {


    // Attributes

    private static final Gson gson = new Gson();


    // Main methods

    public static List<Chair> parseChairs(String json) {
        Type listType = new TypeToken<List<Chair>>(){}.getType();
        return gson.fromJson(json, listType);
    }

    public static List<Reserve> parseReserves(String json) {
        Type listType = new TypeToken<List<Reserve>>(){}.getType();
        return gson.fromJson(json, listType);
    }

    public static Client parseClient(String json) {
        Type type = new TypeToken<Client>(){}.getType();
        return gson.fromJson(json, type);
    }

    public static String parseString(Object object) {
        return gson.toJson(object);
    }
}
