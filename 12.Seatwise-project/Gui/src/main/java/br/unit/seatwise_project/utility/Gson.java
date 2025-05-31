
package br.unit.seatwise_project.utility;


// Init -------------------------------------------------------------------- //


import br.unit.seatwise_project.model.Chair;

public class Gson {


    // Attributes

    private static final com.google.gson.Gson GSON = new com.google.gson.Gson();


    // Main methods

    public static Chair parseChair(String json) {
        return GSON.fromJson(json, Chair.class);
    }
}
