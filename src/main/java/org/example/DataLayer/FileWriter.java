package org.example.DataLayer;

import org.example.BusinessLayer.BaseProduct;
import org.example.BusinessLayer.MenuItem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Nemes Mihnea-Andrei
 */
public class FileWriter {

    /**
     * @return the list of menu items read from the .csv file
     * @throws IOException running exception
     */
    public static LinkedHashSet<MenuItem> readFromFile() throws IOException {
        ArrayList<String> newList = new ArrayList<>();
        try (Stream<String> readStream = Files.lines(Paths.get("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentFour\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_4\\products.csv"))) {
            newList = (ArrayList<String>) readStream
                    .skip(1)
                    .collect(Collectors.toList());
        }
        LinkedHashSet<MenuItem> newHashSet = new LinkedHashSet<>();
        for (String s : newList) {
            String path[] = new String[7];
            path = s.split(",");
            BaseProduct baseProduct = new BaseProduct(path[0], Double.parseDouble(path[1]), Integer.parseInt(path[2]), Integer.parseInt(path[3]), Integer.parseInt(path[4]), Integer.parseInt(path[5]), Double.parseDouble(path[6]));
            newHashSet.add(baseProduct);
        }
        return newHashSet;
    }
}
