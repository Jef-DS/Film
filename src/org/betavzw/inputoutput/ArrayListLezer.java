package org.betavzw.inputoutput;

import org.betavzw.film.BetaFilm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListLezer {
    private String bestandsnaam;

    public ArrayListLezer(String bestandsnaam) {
        this.bestandsnaam = bestandsnaam;
    }
    public List<BetaFilm> lezen() throws IOException {
        List<BetaFilm> films = new ArrayList<>();
        try(Scanner invoer = new Scanner(new BufferedReader(new FileReader(bestandsnaam)))){
            while(invoer.hasNextLine()){
                String regel = invoer.nextLine();
                BetaFilm film = new BetaFilm(regel);
                films.add(film);
            }
        }
        return films;
    }
}
