
package main;

import dao.ProductoDAO;
import model.Polo;
//import java.util.List;

public class Main {

    public static void main(String[] args) {

        ProductoDAO dao = new ProductoDAO();
        Polo p = new Polo();

        p.setNombre("Camisa Holden");
        p.setTipo("Camisa");
        p.setPrecio(89.90);
        p.setStock(25);
        p.setStockMinimo(5);
        p.setTalla("L");
        p.setColor("Azul");
        p.setAtributo("Cuello Corbata");
        dao.registrar(p);
    }
}

/* 
package main;

import config.ConexionBD;

public class Main {

    public static void main(String[] args) {

        ConexionBD.conectar();

    }
}

*/

