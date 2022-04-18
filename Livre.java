import java.io.*;
import java.util.*;

public class Livre extends Ouvrage {

    // Constructeur
    public Livre(String t, double p, Date d, String c, double n) {
        super(t, p, d, c, n);
    }

    // Méthode
    public String toString() {
        return super.toString(); // Ne sert pas à grand chose mais pour rajouter du contenu après ça peut être bien
    }
}

