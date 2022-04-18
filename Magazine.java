import java.io.*;
import java.util.*;

public class Magazine extends Ouvrage {
    private String periodicite;

    // Constructeur
    public Magazine(String t, double p, Date d, String c, double n, String perio) {
        super(t, p, d, c, n);
        periodicite = perio;
    }

    // Getters et setters
    public final String getPeriodicite() {
        return periodicite;
    }

    public final void setPeriodicite(String perio) {
        periodicite = perio;
    }

    // Méthode


    public String toString() {
        return super.toString() + " | " + getPeriodicite();
    }
}

