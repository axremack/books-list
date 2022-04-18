import java.io.*;
import java.util.*;
import java.text.*;

// Ajout d'une classe mère commune pour améliorer la maintenabilité du code (rajouter publications scientifiques, journaux, etc...)
public abstract class Ouvrage {
    private String titre;
    private double prix;
    private Date date_sortie;
    private String cat;
    private double note;

    // Constructeur
    public Ouvrage(String t, double p, Date d, String c, double n) {
        titre = t;
        prix = p;
        date_sortie = d;
        cat = c;
        note = n;
    }

    // Getters et setters (oui c'est pas utile mais je voulais m'entrainer à les faire à la mano)
    public final String getTitre() {
        return titre;
    }

    public final double getPrix() {
        return prix;
    }

    public final Date getDateSortie() {
        return date_sortie;
    }

    public final String getCategorie() {
        return cat;
    }

    public final double getNote() {
        return note;
    }

    public final void setTitre(String t) {
        titre = t;
    }

    public final void setPrix(double p) {
        prix = p;
    }

    public final void setDateSortie(Date d) {
        date_sortie = d;
    }

    public final void setCategorie(String c) {
        cat = c;
    }

    public final void setNote(double n) {
        note = n;
    }

    // Méthodes
    public String toString() {
        StringBuilder builder = new StringBuilder(getTitre());
        builder.append(" | ");
        builder.append(getPrix());
        builder.append(" | ");
        builder.append(new SimpleDateFormat("dd/MM/yyyy").format(getDateSortie()));
        builder.append(" | ");
        builder.append(getCategorie());
        builder.append(" | ");
        builder.append(getNote());
        builder.append("/10");

        return builder.toString();
    }

    // Comparateurs
    public static class CompareTitre implements Comparator<Ouvrage> {
        public int compare(Ouvrage o1, Ouvrage o2) {
          return o1.getTitre().compareTo(o2.getTitre());
        }
    }

    public static class ComparePrix implements Comparator<Ouvrage> {
        public int compare(Ouvrage o1, Ouvrage o2) {
          return Double.compare(o1.getPrix(), o2.getPrix());
        }
    }

    public static class CompareDate implements Comparator<Ouvrage> {
        public int compare(Ouvrage o1, Ouvrage o2) {
            return o1.getDateSortie().compareTo(o2.getDateSortie());
        }
    }

    public static class CompareNote implements Comparator<Ouvrage> {
        public int compare(Ouvrage o1, Ouvrage o2) {
          return Double.compare(o1.getNote(), o2.getNote());
        }
    }
}
