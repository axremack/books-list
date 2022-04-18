import java.io.*;
import java.util.*;
import java.text.*;

public class Main {    
    private static List<Ouvrage> liste = new ArrayList<Ouvrage>();

    private static void afficherListe() {
        liste.forEach((elt) -> System.out.println(elt.toString()));
        System.out.println();
    }

    private static void ajouterOuvrageSaisie(Scanner sc) throws ParseException {
        System.out.print("Titre : ");
        String titre = sc.nextLine();

        System.out.print("Prix : ");
        double prix = sc.nextDouble();
        sc.nextLine();

        System.out.print("Date (dd/MM/yyyy) : ");
        String temp = sc.nextLine();
        Date date_sortie = new SimpleDateFormat("dd/MM/yyyy").parse(temp); 

        System.out.print("Catégorie : ");
        String categorie = sc.nextLine();

        System.out.print("Note (sur 10) : ");
        double note = sc.nextDouble();
        sc.nextLine();

        System.out.print("Périodicité (si c'est un magazine) : ");
        String periodicite = sc.nextLine();
        System.out.println();

        Ouvrage o;

        if (periodicite.replaceAll("\\s+","").length() == 0) { // Si c'est un livre
            o = new Livre(titre, prix, date_sortie, categorie, note);
        } else { // Si c'est un magazine
            o = new Magazine(titre, prix, date_sortie, categorie, note, periodicite);
        }

        liste.add(o);
    }

    private static void ajouterOuvrageDepuisFichier(Scanner scan) throws ParseException {
        String titre = scan.nextLine();
        Double prix =  Double.parseDouble(scan.nextLine());
        String temp = scan.nextLine();
        Date date_sortie = new SimpleDateFormat("dd/MM/yyyy").parse(temp);
        String categorie = scan.nextLine();
        double note =  Double.parseDouble(scan.nextLine());
        String periodicite = scan.nextLine();

        Ouvrage o;

        if (periodicite.replaceAll("\\s+","").length() == 0) { // Si c'est un livre
            o = new Livre(titre, prix, date_sortie, categorie, note);
        } else {
            o = new Magazine(titre, prix, date_sortie, categorie, note, periodicite);
        }

        liste.add(o);
    }

    private static void afficherMenuPrincipal() {
        System.out.println("Quelle fonction éxecuter ?");
        System.out.println("\t1 - Ajouter un livre ou un magazine manuellement");
        System.out.println("\t2 - Ajouter les livres et magazines d'un fichier");
        System.out.println("\t3 - Afficher le contenu de la liste");
        System.out.println("\t4 - Trier la liste");
        System.out.println("\t5 - Arrêter le programme");
    }
    
    public static void main (String[] argv) throws ParseException {
        
        boolean fin = false;

        while (!fin) {
            afficherMenuPrincipal();
            Scanner sc = new Scanner(System.in);
            String rep = sc.nextLine();
            System.out.println();

            switch (rep) {
                // Ajout de livre ou de magazine à la main
                case "1" :
                    ajouterOuvrageSaisie(sc);
                    break;
                // Ajout de livres ou magazines via un fichier
                case "2" :
                    System.out.print("Chemin du fichier : ");
                    String chemin = sc.nextLine();
                    System.out.println();

                    try {
                        Scanner scan = new Scanner(new FileReader(chemin)); // Ouverture du fichier en lecture

                        while (scan.hasNext()) { // Tant que ce n'est pas la fin du fichier
                            ajouterOuvrageDepuisFichier(scan);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                // Affichage de la liste
                case "3" :
                    System.out.print("Que voulez-vous afficher ?\n\t 1 - Tout les éléments\n\t 2 - Les livres\n\t 3 - Les magazines\n\t 4 - Les ouvrages d'une catégorie\n");
                    rep = sc.nextLine();
                    System.out.println();

                    switch(rep) {
                        // Tout les éléments de la liste
                        case "1" :
                            afficherListe();
                            break;
                        // Seulement les livres 
                        case "2" :
                            liste.forEach((elt) -> {if (elt instanceof Livre){
                                                        System.out.println(elt.toString()); 
                                                    }});
                            break;
                        // Seulement les magazines
                        case "3" :
                            liste.forEach((elt) -> {if (elt instanceof Magazine){
                                                        System.out.println(elt.toString()); 
                                                    }});
                            break;
                        // Tout les ouvrages d'une catégorie précise
                        case "4" :
                            System.out.print("Catégorie à trouver : ");
                            String cat = sc.nextLine();
                            System.out.println();

                            liste.forEach((elt) -> {if (elt.getCategorie().equals(cat)){
                                                        System.out.println(elt.toString());
                                                    }});
                    }
                    System.out.println();
                    break;
                // Tri de la liste
                case "4" : 
                    System.out.print("Comment voulez-vous trier ?\n\t 1 - Ordre alphabétique du titre\n\t 2 - Date de sortie (la plus récente en dernier)\n\t 3 - Note (croissant)\n\t 4 - Prix (croissant)\n");
                    rep = sc.nextLine();
                    System.out.println();

                    switch(rep) {
                        // Tri par ordre alphabétique
                        case "1" :
                            Collections.sort(liste, new Ouvrage.CompareTitre());
                            afficherListe();
                            break;
                        // Tri par date de sortie la plus ancienne
                        case "2" : 
                            Collections.sort(liste, new Ouvrage.CompareDate());
                            afficherListe();
                            break;
                        // Tri par note des lecteurs la plus basse
                        case "3" : 
                            Collections.sort(liste, new Ouvrage.CompareNote());
                            afficherListe();
                            break;
                        // Tri par prix le plus bas
                        case "4" : 
                            Collections.sort(liste, new Ouvrage.ComparePrix());
                            afficherListe();
                            break;
                    }
                    break;
                // Sortie du programme
                case "5" :
                    System.out.println("Adios");
                    fin = true;
                    break;
            }
        }
    }
}