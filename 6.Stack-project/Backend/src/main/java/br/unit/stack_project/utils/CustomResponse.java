
package br.unit.stack_project.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


// Init -------------------------------------------------------------------- //


public abstract class CustomResponse {


    // Main methods

    public static ResponseEntity<Object> success(Object object) {
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    public static ResponseEntity<Object> error(Object object) {
        return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> serverError(Object object) {
        return new ResponseEntity<>(object, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<Object> created(Object object) {
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }
}
