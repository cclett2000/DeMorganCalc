/*
Programmer: Charles Lett Jr.
Function: Calculates sets using DeMorgan's law
Date: 8/4/22
 */

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.net.Inet4Address;
import java.util.ArrayList;

public class DeMorganCalculator {
    // main method
    public static void main(String[] args) throws IOException {
        int universeMax = 999;      // set max range for number universe
        ArrayList<Integer> set1 = new ArrayList<>();
        ArrayList<Integer> set2 = new ArrayList<>();
        setData(set1, set2);

        // calculations
        unionCalc(set1, set2, universeMax);
        intersectionCalc(set1, set2, universeMax);

    }

    // file handler -- [0] = set1, [1] = set2
    private static void setData(ArrayList<Integer> set1, ArrayList<Integer> set2) throws IOException {
        int inc = 0;
        File setFile1 = new File("sample_10_1.txt");
        File setFile2 = new File("sample_10_2.txt");
        BufferedReader reader1 = new BufferedReader(new FileReader(setFile1));
        BufferedReader reader2 = new BufferedReader(new FileReader(setFile2));

        // populate lists from file
        while(inc < 10){
            set1.add(Integer.parseInt(reader1.readLine()));
            set2.add(Integer.parseInt(reader2.readLine()));
            inc ++;
        }
    }

    // union method
    private static void unionCalc(ArrayList<Integer> set1, ArrayList<Integer> set2, int universeMax) throws IOException {
        ArrayList<Integer> setUnion = new ArrayList<>();
        ArrayList<Integer> numbersNotInSet = new ArrayList<>();
        setUnion = set1;
        setUnion.addAll(set2);

        for (int i = 0; i < universeMax; i++){
            if(!setUnion.contains(i)){
                numbersNotInSet.add(i);
            }
        }

        System.out.println("'NOT' Union List: " + numbersNotInSet
                + "\n\t List Size: " + numbersNotInSet.size());
    }

    // intersection method
    private static void intersectionCalc(ArrayList<Integer> set1, ArrayList<Integer> set2, int universeMax){
        // not storage
        ArrayList<Integer> interSet1 = new ArrayList<>();
        ArrayList<Integer> interSet2 = new ArrayList<>();
        ArrayList<Integer> interSetMain = new ArrayList<>();

        // not in sets
        for (int i = 0; i < universeMax; i++){
            if (!set1.contains(i) && !set2.contains(i)){
                interSet1.add(i);
            }
        }

        // get intersection
        interSetMain.addAll(interSet1);

        for (int j = 0; j < interSet2.size(); j++){
            for (int k = 0; k < interSetMain.size(); k++) {
                // value is in both sets - remove
                if (interSet2.get(j) == interSetMain.get(k)) {
                    interSetMain.remove(k);
                }
                // value unique - add
                else if(k == interSetMain.size()){
                    interSetMain.add(j);
                }
            }
        }

        System.out.println("Intersection list: " + interSetMain
                + "\n\t List Size: " + interSetMain.size());
    }
}
