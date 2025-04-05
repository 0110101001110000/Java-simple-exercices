
package br.unit.stack_project.repositories;

import br.unit.stack_project.db.Stack;
import br.unit.stack_project.utils.CustomResponse;
import br.unit.stack_project.models.UrlDTO;
import org.springframework.http.ResponseEntity;


// Init -------------------------------------------------------------------- //


public class BackWardDAO {


    // Attributes

    final private Stack<UrlDTO> database = new Stack<>();


    // Main methods

    public ResponseEntity<Object> createElement(UrlDTO url) {
        try {
            database.push(url);
            return CustomResponse.created("O histórico foi armazenado");
        } catch (Exception e) {
            return CustomResponse.serverError(String.format("Não foi possível armazenar o histórico. Erro: %s.", e.getMessage()));
        }
    }

    public ResponseEntity<Object> getElement() {
        try {
            return CustomResponse.success(database.peek());
        } catch (Exception e) {
            return CustomResponse.serverError(String.format("Não foi possível obter o histórico. Erro: %s.", e.getMessage()));
        }
    }

    public ResponseEntity<Object> deleteElement() {
        try {
            return CustomResponse.success(database.pop());
        } catch (Exception e) {
            return CustomResponse.serverError(String.format("Não foi possível deletar o histórico. Erro: %s.", e.getMessage()));
        }
    }
}
