
package br.unit.seatwise_project.utility;

import br.unit.seatwise_project.model.Chair;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;



// Init -------------------------------------------------------------------- //


public class JsonUtils {


    // Attributes

    private static final Gson gson = new Gson();


    // Main methods

    public static List<Chair> parseChair(String json) {
        Type listType = new TypeToken<List<Chair>>(){}.getType();
        return gson.fromJson(json, listType);
    }
}
