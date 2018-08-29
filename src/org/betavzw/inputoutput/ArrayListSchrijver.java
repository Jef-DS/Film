package org.betavzw.inputoutput;

import org.betavzw.film.BetaFilm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ArrayListSchrijver {
    private String bestandsnaam;

    public ArrayListSchrijver(String bestandsnaam) {
        this.bestandsnaam = bestandsnaam;
    }
    public void schrijven(List<BetaFilm> lijst) throws IOException
    {
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(bestandsnaam)))){
            for(BetaFilm film: lijst){
                writer.println(film.naarCSV());
            }
        }
    }
}
