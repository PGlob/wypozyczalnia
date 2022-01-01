package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Osobowy> samochodyOsobowe = new ArrayList<Osobowy>();
    public static ArrayList<Dostawczy> samochodyDostawcze = new ArrayList<Dostawczy>();
    public static ArrayList<Wypozyczenia> wypozyczoneSamochody = new ArrayList<Wypozyczenia>();
    public static boolean logreg = true;
    public static int SessionID = -1;

    public static void main(String[] args) throws IOException {
        Osobowy.zaladujSamochody();
        Dostawczy.zaladujSamochody();
        Wypozyczenia.zaladujWypozyczenia();
        while (true) {
            while (logreg) {
                Scanner sc = new Scanner(System.in);
                System.out.println("1.Logowanie");
                System.out.println("2.Rejestracja");
                System.out.println("3.Zakończ program");
                int select = sc.nextInt();
                switch (select) {
                    case 1:
                        Menu.Logowanie();
                        break;
                    case 2:
                        Menu.Rejestracja();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Zły numer");
                        break;
                }
            }
            Menu menu = new Menu(SessionID);
            menu.otworz();
        }
    }
}