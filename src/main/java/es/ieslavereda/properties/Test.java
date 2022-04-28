package es.ieslavereda.properties;

import es.ieslavereda.model.Model;
import es.ieslavereda.model.Person;

public class Test {
    public static void main(String[] args) {
        Model model = new Model();
        //model.updatePerson(new Person("4444", "Leon", "Gimenez Gimenez", 30));
        System.out.println(model.authenticate("pepa@mordor.es", "1111"));
        //System.out.println(model.getEmpleados());
    }
}
