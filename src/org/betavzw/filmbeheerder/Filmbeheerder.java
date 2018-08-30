package org.betavzw.filmbeheerder;

import org.betavzw.film.BetaFilm;
import org.betavzw.html.HtmlTable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Filmbeheerder {
    private static final String[] menuItems = {"Film toevoegen", "Lijst tonen", "Film zoeken", "Exporteer naar HTML", "Stoppen"};
    private List<BetaFilm> films;
    private Scanner scanner;

    public Filmbeheerder(List<BetaFilm> films, Scanner scanner) {
        this.films = films;
        this.scanner = scanner;
        toonMenu();
    }

    private void toonMenu() {
        int keuze;
        do {
            for (int i = 0; i < menuItems.length - 1; i++) {
                System.out.printf("%d. %s.%n", i + 1, menuItems[i]);
            }
            System.out.printf("%d. %s.%n", 0, menuItems[menuItems.length - 1]);
            System.out.print("U keuze: ");
            keuze = Integer.parseInt(scanner.nextLine());
            switch (keuze) {
                case 1:
                    filmToevoegen();
                    break;
                case 2:
                    filmsTonen(films);
                    break;
                case 3:
                    filmZoeken();
                    break;
                case 4:
                    filmsExporteren(films);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Ongeldige keuze");
            }
        } while (keuze != 0);

    }

    private void filmsExporteren(List<BetaFilm> films) {
        String htmlfile = "test.html";
        try {
            Path templateFile = vraagTemplateFile();
            List<String> lijnen = Files.lines(templateFile).collect(Collectors.toList());
            for(int i=0;i<lijnen.size();i++){
                lijnen.set(i, lijnen.get(i).replace("%TITLE%","Filmlijst"));
            }
            String htmlTabel = maakhtmlTabel(films);
            for(int i=0;i<lijnen.size();i++){
                lijnen.set(i, lijnen.get(i).replace("%BODY%",htmlTabel));
            }
            try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(htmlfile)))){
                for (String lijn: lijnen){
                    writer.println(lijn);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String maakhtmlTabel(List<BetaFilm> films) {
        HtmlTable tabel = new HtmlTable(new String[]{"Titel","Regisseur","Jaartal"});
        for(BetaFilm film: films){
            String[] elementen = new String[3];
            elementen[0] = film.getTitel();
            elementen[1] = film.getRegisseur();
            elementen[2] = film.getJaartal()+"";
            tabel.AddRow(elementen);
        }
        return tabel.getHTML();
    }

    private Path vraagTemplateFile() throws IOException {
        Path currentDirectory = Paths.get("").toAbsolutePath();
        List<Path> bestanden = Files.walk(currentDirectory, 1)
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".thtml"))
                .collect(Collectors.toList());
        if (bestanden.size() == 1) {
            return bestanden.get(0);
        }
        for (Path p : bestanden) {
            System.out.println(p);
        }
        return null;
    }

    private void filmToevoegen() {
        System.out.print("Geef titel: ");
        String titel = scanner.nextLine();
        System.out.print("Geef regisseur: ");
        String regisseur = scanner.nextLine();
        System.out.print("Geef jaartal: ");
        int jaartal = Integer.parseInt(scanner.nextLine());
        BetaFilm film = new BetaFilm(films.size(), titel, regisseur, jaartal);
        films.add(film);
    }

    private void filmsTonen(List<BetaFilm> films) {
        System.out.printf("%3s %25s %20s%10s%n", "id", "titel", "regisseur", "jaartal");
        for (BetaFilm film : films) {
            System.out.printf("%3d %25s %20s%10d%n", film.getId(), fittoLength(film.getTitel(), 25), fittoLength(film.getRegisseur(), 20), film.getJaartal());
        }
        System.out.print("Druk <RETURN> om verder te gaan.");
        scanner.nextLine();
    }

    private String fittoLength(String tekst, int maxlen) {
        if (tekst.length() > maxlen) {
            return tekst.substring(0, maxlen - 4) + "... ";
        } else {
            return tekst;
        }
    }

    private void filmZoeken() {
        System.out.print("Geef een deel van de titel die je wil zoeken: ");
        String zoektekst = scanner.nextLine();
        List<BetaFilm> gevonden = new ArrayList<>();
        for (BetaFilm film : films) {
            if (film.getTitel().contains(zoektekst)) {
                gevonden.add(film);
            }
        }
        filmsTonen(gevonden);

    }

}
