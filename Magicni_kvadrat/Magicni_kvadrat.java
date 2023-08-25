package Magicni_kvadrat;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;

public class Magicni_kvadrat {
    String red;
    ArrayList<ArrayList<Integer>> matrica = new ArrayList<>();

    public void ucitavanje_brojeva() {
        try (BufferedReader in = new BufferedReader(new FileReader("kvadrat_1.txt"))) {
            int index = 0;
            while ((red = in.readLine()) != null) {
                String[] linija = red.split("\t");
                matrica.add(new ArrayList<>());
                for (String broj : linija) {
                    matrica.get(index).add(Integer.parseInt(broj));
                }
                ++index;
                in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int zbir_prvog_reda() {
        int prvi_red = 0;

        for (int i = 0; i < matrica.get(0).size(); ++i) {
            prvi_red += matrica.get(0).get(i);
        }

        return prvi_red;
    }

    private boolean provjeri_jednakost_redova(int prvi_red) {
        for (int i = 0; i < matrica.size(); ++i) {
            int zbir = 0;
            for (int j = 0; j < matrica.get(i).size(); ++j) {
                zbir += matrica.get(i).get(j);
            }
            if (zbir != prvi_red)
                return false;
        }
        return true;
    }

    private boolean provjeri_jednakost_kolona(int prvi_red) {
        for (int i = 0; i < matrica.size(); ++i) {
            int zbir = 0;
            for (int j = 0; j < matrica.get(i).size(); ++j) {
                zbir += matrica.get(j).get(i);
            }
            if (zbir != prvi_red)
                return false;
        }
        return true;
    }

    private boolean zbir_glavne_dijagonale(int prvi_red) {
        int zbir = 0;
        for (int i = 0; i < matrica.size(); ++i) {
            zbir += matrica.get(i).get(i);
        }
        if (zbir != prvi_red)
            return false;
        return true;
    }

    private boolean zbir_sporedne_dijagonale(int prvi_red) {
        int zbir = 0;
        for (int i = matrica.size() - 1, j = 0; j < matrica.size(); --i, ++j) {
            zbir += matrica.get(i).get(j);
        }
        if (zbir != prvi_red)
            return false;
        return true;
    }

    public String provjeri_jednakost() {
        int prvi_red = zbir_prvog_reda();
        if(!provjeri_jednakost_redova(prvi_red) || !provjeri_jednakost_kolona(prvi_red) || !zbir_glavne_dijagonale(prvi_red) || !zbir_sporedne_dijagonale(prvi_red))
            return "Zbir nije jednak!";
        else 
            return "Jednak zbir!";
    }
}
