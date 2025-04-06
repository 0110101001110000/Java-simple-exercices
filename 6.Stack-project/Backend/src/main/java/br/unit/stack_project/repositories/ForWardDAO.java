
package br.unit.stack_project.repositories;

import br.unit.stack_project.db.Stack;
import br.unit.stack_project.models.UrlDTO;


// Init -------------------------------------------------------------------- //


/**
 * @author 0110101001110000
 */
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
        if (!database.isEmpty()) {
            database.pop();
            return "O Histórico de forward foi removido";
        } else {
            return "Erro ao remover histórico de forward. Erro: a base de dados está vazia";
        }
    }
}
