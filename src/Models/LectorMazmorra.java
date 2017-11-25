/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author alejandro
 */
public class LectorMazmorra {

    public char[][] readFile() {
        char mazmorra[][] = new char[20][17];
        File f = new File("/home/alejandro/NetBeansProjects/Pacman/src/View/levels/level1.txt");
        try {
            Scanner scanner = new Scanner(f);
            int i = 0;
            while (scanner.hasNext()) {
                String next = scanner.next();
                for (int j = 0; j < next.length(); j++) {
                    mazmorra[i][j] = next.charAt(j);
                }
                i++;
            }
        } catch (IOException e) {
            System.err.println("No pudo ser posible la lectura: " + e.getMessage());
        }
        return mazmorra;
    }
}
