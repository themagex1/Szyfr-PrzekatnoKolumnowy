package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Deszyfrowanie
{
    public static void Deszyfruj(String klucz, String tekst) throws IOException {
        var dlugosc = tekst.replace("X","").length()/klucz.length()+1;
        var piramida = new char[klucz.length()][klucz.length()+dlugosc-1];
        var posortowanyKlucz = klucz.toCharArray();
        var tablicaKlucza = klucz.toCharArray();

        Arrays.sort(posortowanyKlucz);

        //wpisywanie 'X' w ksztalt piramidy do macierzy
        int shiftInt = 0;
        for(int i=0;i<klucz.length();i++)
            for(int j=0;j<klucz.length()+dlugosc-1;j++)
                if(j>=i && j<=klucz.length()+dlugosc-2-i && i < dlugosc)
                {
                    piramida[i][j] = 'X';
                    shiftInt++;
                }

        //zliczanie X oraz wpisywanie odpowiedniej liczby znakow do kolumn
        var tekstIndeks = 0;
        var indeks = 1;
        for (var c: posortowanyKlucz
             )
        {
            for(int z = 0; z < posortowanyKlucz.length;z++)
                if(tablicaKlucza[z] == c)
                {
                    indeks = z;
                    break;
                }

            tablicaKlucza[indeks]='?';

                while(indeks < klucz.length()+dlugosc-1)
                {
                    for(int i=0;i<klucz.length();i++)
                    {
                        if (piramida[i][indeks] == 'X') {
                            piramida[i][indeks] = tekst.charAt(tekstIndeks++);
                        }
                        else break;
                    }
                    indeks+=klucz.length();
                }
        }
      //

        //wpisywanie piramidy do listy
        var shifted = new ArrayList<Character>();
        for (int i=0;i<klucz.length();i++)
        {
            for (int j=0;j<klucz.length()+dlugosc-1;j++)
                if(piramida[i][j] != 0)
                {
                    shifted.add(piramida[i][j]);
                }
        }

        //wpisywanie listy w macierz
        var normalTable = new char[dlugosc][klucz.length()];
        int lastMaxColumn = klucz.length()-1;
        var shiftIndeks = 0;
        for(int i=0;i<dlugosc;i++)
        {
            for(int j=0;j<klucz.length();j++)
            {
                if(j==lastMaxColumn)
                {
                    for (int l=i;l<dlugosc;l++) {
                        normalTable[l][j] = shifted.get(shiftIndeks++);
                    }
                    lastMaxColumn--;
                    break;
                }
                if (shiftIndeks < tekst.length())
                    normalTable[i][j] = shifted.get(shiftIndeks++);
                else break;
            }
        }

        //tekst jawny
        var tekstJawny = "";
        for(int i = 0; i<dlugosc;i++)
        {
            for (int j=0;j<klucz.length();j++)
            {
                if (normalTable[i][j] == '_')
                {
                    System.out.print("_");
                    tekstJawny += " ";
                }
                else {
                    System.out.print(normalTable[i][j]);
                    tekstJawny += normalTable[i][j];
                }
            }
            System.out.println();
        }
        tekstJawny = tekstJawny.replace("X","");
        System.out.println("Tekst jawny: "+ tekstJawny);
    }
}
