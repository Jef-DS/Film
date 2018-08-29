package org.betavzw;

import org.betavzw.film.BetaFilm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String[] menuItems= {"Film toevoegen", "Lijst tonen", "Film zoeken", "Stoppen"};
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
	    List<BetaFilm> films = new ArrayList<>();
	    int keuze;
	    do{
	        for(int i= 0; i<menuItems.length-1;i++){
	            System.out.printf("%d. %s.%n", i+1,menuItems[i]);
            }
            System.out.printf("%d. %s.%n", 0, menuItems[menuItems.length-1]);
	        System.out.print("U keuze: ");
	        keuze = Integer.parseInt(scanner.nextLine());
	        switch(keuze){
                case 1: filmToevoegen(films);
                break;
                case 2: filmsTonen(films);
                break;
                case 3: filmZoeken(films);
                break;
                case 0:
                    break;
                default:
                    System.out.println("Ongeldige keuze");
            }
        }while(keuze != 0);
    }

    private static void filmToevoegen(List<BetaFilm> films){
        System.out.print("Geef titel: ");
        String titel = scanner.nextLine();
        System.out.print("Geef regisseur: ");
        String regisseur = scanner.nextLine();
        System.out.print("Geef jaartal: ");
        int jaartal = Integer.parseInt(scanner.nextLine());
        BetaFilm film = new BetaFilm(films.size(),titel, regisseur,jaartal);
        films.add(film);
    }
    private static void filmsTonen(List<BetaFilm> films){
        System.out.printf("%3s %25s %20s%10s%n","id", "titel", "regisseur", "jaartal");
        for(BetaFilm film: films){
            System.out.printf("%3d %25s %20s%10d%n", film.getId(), fittoLength(film.getTitel(), 25), fittoLength(film.getRegisseur(),20), film.getJaartal());
        }
        System.out.print("Druk <RETURN> om verder te gaan.");
        scanner.nextLine();
    }
    private static String fittoLength(String tekst, int maxlen){
        if (tekst.length()> maxlen){
            return tekst.substring(0, maxlen-4) + "... ";
        }else{
            return tekst;
        }
    }
    private static void filmZoeken(List<BetaFilm> films){

    }
}
