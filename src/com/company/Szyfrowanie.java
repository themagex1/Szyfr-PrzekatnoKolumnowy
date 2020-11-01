package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class Szyfrowanie
{
    public static void Szyfruj(String klucz, String tekst)
    {
        int k = 0,
            n = klucz.length(),
            m = tekst.length() / n+1;
        System.out.println("n: " + n + " m: "+ m);
        var tablicaWartosciKlucza = new int[n];
        var tablicaTekstu = tekst.toCharArray();
        var tablicaKlucza = klucz.toCharArray();
        var alfabet = new char[26];
        var tabelaDoZaszyfrowania = new char[m][n];
        var tabelaZaszyfrowana = new char[n][n+m-1];

        //indeksowanie klucza
        int i = 0;
        for(char l = 'a'; l <='z'; l++)
        {
            alfabet[i++] = l;
        }
        System.out.println();

        var tablicaPonumerowanaKlucza= new Hashtable<Integer, Character>();
        Arrays.sort(tablicaKlucza);

        int dex = 1;

        for (var element:tablicaKlucza)
        {
            tablicaPonumerowanaKlucza.put(dex++,element);
        }


        for(i=0; i<m;i++)
        {
            for (int j=0;j<n;j++)
            {
                if(k>=tablicaTekstu.length) tabelaDoZaszyfrowania[i][j] = 'X';
                else tabelaDoZaszyfrowania[i][j] = tablicaTekstu[k];
                k++;
            }
        }

        //wyswietlanie tekstu jawnego z macierzy

        System.out.println("MACIERZ Z WPISANYM TEKSTEM JAWNYM");
        System.out.println("=================================");
        for (i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(tabelaDoZaszyfrowania[i][j] == ' ')
                    System.out.print("_");
                else System.out.print(tabelaDoZaszyfrowania[i][j]);
            }
            System.out.println();
        }
        System.out.println("=================================");
        //macierz wzdluz szerokosci i dlugosci do listy
        var shifted = new ArrayList<Character>();
        int maxKolumna = n-1;

        for(i =0;i<m;i++)
        {
            for (int j=0;j<n;j++)
            {
                if(j == maxKolumna)
                {
                    for (int li = i; li<m; li++)
                        shifted.add(tabelaDoZaszyfrowania[li][j]);
                    maxKolumna--;
                    break;
                }
                shifted.add(tabelaDoZaszyfrowania[i][j]);
            }
        }

        //lista do piramidy
        int shiftInt = 0;
        for(i=0;i<m;i++)
        {
            for(int j=0;j<n+m-1;j++)
                if(j>=i && j<=n+m-2-i && shiftInt < shifted.size())
                {
                    tabelaZaszyfrowana[i][j] = shifted.get(shiftInt);
                    shiftInt++;
                }
        }

        //otrzymanie szyfru
        var result = "";
        var usunietyKlucz = klucz.toCharArray();
        int indeksSzyfrowania = -1;
        for(int l = 0; l < tablicaKlucza.length; l++)
        {

            for(int z = 0; z < usunietyKlucz.length;z++)
                if(usunietyKlucz[z] == tablicaKlucza[l])
                {
                    indeksSzyfrowania = z;
                    break;
                }
            //
            usunietyKlucz[indeksSzyfrowania] = '?';

            while(indeksSzyfrowania < n + m - 1)
            {
                for(i = 0;i<n;i++)
                {
                    if(tabelaZaszyfrowana[i][indeksSzyfrowania] == 0) break;

                    if(tabelaZaszyfrowana[i][indeksSzyfrowania] == ' ') result +='_';
                    else result += tabelaZaszyfrowana[i][indeksSzyfrowania];
                }
                indeksSzyfrowania +=n;
            }

        }
        System.out.println("Szyfr: "+ result);
    }
}
