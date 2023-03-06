package Main;

import Arena.Arena;
import InputOutput.Read;
import Other.Coach;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Helper {
    /**
     * Listeaza fisierele disponibile pentru alegere.
     * @return Lista de fisiere.
     */
    public List<String> list() {
        List<String> fileNames = new ArrayList<>();

        File folder = new File("./src/InputOutput/in");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            String filename = file.getName();
            if (filename.endsWith(".xml") || filename.endsWith(".XML"))
                fileNames.add(filename);
        }
        return fileNames;
    }

    /**
     * Alege un fisier din lista
     * @return Lista de antrenori.
     */
    public List<Coach> chooseOne() {
        Read read = new Read();
        List<String> fileNames = list();
        System.out.println("Alegeti unul din fisierele:");
        for (String name : fileNames) System.out.println(name);

        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        if (!fileNames.contains(choice)) {
            System.out.println("Nume invalid, incercati din nou.");
            return chooseOne();
        }
        return read.read(choice);
    }

    /**
     * Afiseaza meniul initial de alegere.
     * @throws ParserConfigurationException
     * @throws IOException
     */
    protected void menu() throws ParserConfigurationException, IOException {
        Arena arena = Arena.getInstance();
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Alege din fisierele test existente.");
        System.out.println("2. Ruleaza toate fisierele din in.");
        System.out.println("3. Genereaza un test random (test.xml).");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> arena.prepBattle(0);
            case 2 -> arena.prepBattle(1);
            case 3 -> arena.prepBattle(2);
            default -> {
                System.out.println("Varianta invalida, incercati din nou.");
                menu();
            }
        }
    }
}
