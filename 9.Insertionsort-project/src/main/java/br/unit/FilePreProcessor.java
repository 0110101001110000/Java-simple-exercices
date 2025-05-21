
package br.unit;

import br.unit.entities.RaceLapEntity;

import java.util.ArrayList;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


/**
 * ...
 * @author 01101010-01110000
 * @since 1.0
 */
public class FilePreProcessor {


    // Attributes

    private final Logger logger = Logger.getLogger(FilePreProcessor.class.getName());
    private final String filePath;
    private final String separator;


    // Constructors

    /**
     * ...
     * @since 1.0
     */
    public FilePreProcessor(String filePath, String separator) {
        this.filePath = filePath;
        this.separator = separator;
    }


    // Main methods

    public String[][] process() {
        ArrayList<String[]> arrayList = new ArrayList<>();

        arrayList.addFirst(new String[] {"asdfklhjgv", "askldçjfh"});

        return arrayList.toArray(new String[0][0]);
    }


    // Validation methods

    // ...


    // Util methods

    // ...
}
