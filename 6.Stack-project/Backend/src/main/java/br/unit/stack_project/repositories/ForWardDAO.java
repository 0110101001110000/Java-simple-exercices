
package br.unit.stack_project.repositories;

import br.unit.stack_project.db.Stack;
import br.unit.stack_project.models.UrlDTO;


// Init -------------------------------------------------------------------- //


public class ForWardDAO {


    // Attributes

    final private Stack<UrlDTO> database = new Stack<>();


    // Main methods

    public String createElement(UrlDTO url) {
        database.push(url);
        return "O Histórico de forward foi adicionado";
    }

    public UrlDTO getElement() {
        return database.peek();
    }

    public String deleteElement() {
        database.pop();
        return "O Histórico de forward foi removido";
    }
}
