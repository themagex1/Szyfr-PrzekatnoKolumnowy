package com.company;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
       int x;
       String tekst, klucz, zaszyfrowane;
       Scanner in = new Scanner(System.in);
       Scanner warunek = new Scanner(System.in);
       String pytanie;
       //wybor opcji
        do {
            System.out.println("1. Szyfrowanie");
            System.out.println("2. Deszyfrowanie");
            System.out.println("================================");
            System.out.print("Wybierz opcje: ");
            int wybor = in.nextInt();
            switch (wybor) {
                case 1:
                    Scanner inn = new Scanner(System.in);
                    System.out.println("=====SZYFROWANIE=====");
                    System.out.println("Podaj tekst ktory chcesz zaszyfrowac: ");
                    tekst = inn.nextLine();
                    System.out.println("Podaj klucz: ");
                    klucz = inn.nextLine();
                    Szyfrowanie.Szyfruj(klucz, tekst);
                    break;
                case 2:
                    Scanner innn = new Scanner(System.in);
                    System.out.println("=====DESZYFROWANIE=====");
                    System.out.println("Podaj tekst ktory chcesz odszyfrowac: ");
                    zaszyfrowane = innn.nextLine();
                    System.out.println("Podaj klucz:");
                    klucz = innn.nextLine();
                    Deszyfrowanie.Deszyfruj(klucz, zaszyfrowane);
                    break;
            }

            System.out.println("Czy ponowic?: ");
            pytanie = warunek.nextLine();
        }while(pytanie.equals("tak"));
    }
}
