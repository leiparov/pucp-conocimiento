/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pucp.informatica.hdo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

/**
 *
 * @author leibnitzpavel
 */
public class Ejemplo01 {

    private static Resource crearRecurso(String NS, String id, Model model) {
        String resourceURI = NS + id;
        return model.createResource(resourceURI);
    }

    public static void main(String[] args) {
        String NS = "http://www.pucp.edu.pe/informatica#";
        Model model = ModelFactory.createDefaultModel();

        Resource mensWear = crearRecurso(NS, "MensWear", model);
        Resource shirts = crearRecurso(NS, "Shirts", model);
        Resource tshirts = crearRecurso(NS, "Tshirts", model);
        Resource henleys = crearRecurso(NS, "Henleys", model);
        Resource oxfords = crearRecurso(NS, "Oxfords", model);
        Resource chamoisHenley = crearRecurso(NS, "ChamoisHenley", model);
        Resource classicOxford = crearRecurso(NS, "ClassicOxford", model);

        model.add(shirts, RDFS.subClassOf, mensWear);
        model.add(tshirts, RDFS.subClassOf, shirts);
        model.add(henleys, RDFS.subClassOf, shirts);
        model.add(oxfords, RDFS.subClassOf, shirts);

        model.add(chamoisHenley, RDF.type, henleys);
        model.add(classicOxford, RDF.type, shirts);
        model.add(classicOxford, RDF.type, oxfords);

        FileOutputStream output = null;
        try {
            output = new FileOutputStream("camisas.rdf");
        } catch (FileNotFoundException e) {
            System.out.println(" Ocurrió un error al crear el archivo.");
        }
        model.write(output, "RDF/XML-ABBREV");

    }
}
