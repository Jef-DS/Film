package org.betavzw;

import org.betavzw.film.BetaFilm;
import org.betavzw.filmbeheerder.Filmbeheerder;
import org.betavzw.inputoutput.ArrayListLezer;
import org.betavzw.inputoutput.ArrayListSchrijver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String BESTANDSNAAM = "films.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            List<BetaFilm> films = leesFilmbestand();
            Filmbeheerder beheerder = new Filmbeheerder(films, scanner);
            beheerder.verwerkMenu();
            schrijfFilmBestgand(films);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void schrijfFilmBestgand(List<BetaFilm> films) throws IOException {
        ArrayListSchrijver schrijver = new ArrayListSchrijver(BESTANDSNAAM);

        schrijver.schrijven(films);
    }

    private static List<BetaFilm> leesFilmbestand() throws IOException {
        Path pad = Paths.get(BESTANDSNAAM);
        List<BetaFilm> films;
        if (Files.exists(pad)) {
            ArrayListLezer lezer = new ArrayListLezer(BESTANDSNAAM);
            films = lezer.lezen();
        } else {
            films = new ArrayList<>();
        }
        return films;
    }
}
