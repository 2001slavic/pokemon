package InputOutput;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Write {
    /**
     * Genereaza numele pentru fisier de output.
     * @param fileName Numele fisierului de intrare.
     * @return Numele fisierului de iesire.
     */
    private String generateOutputFileName(String fileName) {
        String res = fileName.replaceAll("[^0-9]", "");
        res = "out" + res + ".txt";
        return res;
    }

    /**
     * Metoda de scriere in fisiere a outputului de la batalii.
     * @param output Output-ul ce urmeaza sa fie scris.
     * @param fileName Numele fisierului in care urmeaza scris output-ul.
     * @throws IOException
     */
    public void writeToFiles(String output, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("./src/InputOutput/out/" + generateOutputFileName(fileName)));
        writer.write(output);
        writer.close();
    }

    /**
     * Scrie output-ul in stdout.
     * @param output Output-ul ce urmeaza sa fie scris.
     */
    public void writeToStdout(String output) {
        System.out.print(output);
    }
}
