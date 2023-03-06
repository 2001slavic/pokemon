package Arena;

import InputOutput.Generate;
import InputOutput.Read;
import InputOutput.Write;
import Main.Helper;
import Other.Coach;
import org.jetbrains.annotations.NotNull;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Arena {
    private static Arena singleton;

    /**
     * Metoda DP Singleton, asigura o singura instanta a arenei.
     * @return Instanta obiectului Arena.
     */
    public static Arena getInstance() {
        if (singleton == null) singleton = new Arena();
        return singleton;
    }

    /**
     * Pregateste bataliile si decide unde va fi scris output-ul.
     * @param outputType Primeste tipul de output de la Helper din package-ul Main.
     * @throws ParserConfigurationException
     * @throws IOException
     */
    public void prepBattle(int outputType) throws ParserConfigurationException, IOException {
        Write write = new Write();
        // 1. Alege din fisierele test existente.
        if (outputType == 0) {
            Helper helper = new Helper();
            write.writeToStdout(startBattle(helper.chooseOne()));
        }
        // 2. Ruleaza toate fisierele din in.
        else if (outputType == 1) {
            Read read = new Read();
            Helper helper = new Helper();
            for (String name : helper.list()) {
                write.writeToFiles(startBattle(read.read(name)), name);
            }
        }
        // 3. Genereaza un test random (test.xml).
        else {
            Generate generate = new Generate();
            write.writeToStdout(startBattle(generate.generate()));
        }
    }

    /**
     * Lanseaza bataliile propriu-zise, se alege un eveniment aleaator din:
     * 1. Lupta unui Pokemon aleator a Antrenorului1 si 2 cu Neutrel de tip 1.
     * 2. Lupta unui Pokemon aleator a Antrenorului1 si 2 cu Neutrel de tip 2.
     * 3. Lupta dintre cei 2 antrenori.
     * @param coaches Lista formata din cei 2 antrenori.
     * @return Output-ul unificat a tuturor bataliilor.
     */
    public String startBattle(@NotNull List<Coach> coaches) {

        String output = "Initiating new battle...\n";
        // seteaza antrenorii
        Coach coach1 = coaches.get(0);
        Coach coach2 = coaches.get(1);
        Battle battle = new Battle();
        Random rand = new Random();
        while (true) {
            int battleType = rand.nextInt(3);
            // daca NU e batalie intre antrenori
            if (battleType != 2) {
                String[] out = new String[2];
                Arrays.fill(out, "");
                // ruleaza bataliile intre Pokemon si Neutrel concomitent
                ExecutorService executorService = Executors.newFixedThreadPool(2);
                // porneste batalia pentru Antrenorul1 vs Neutrel
                executorService.execute(() -> out[0] += battle.neutrelBattle(coach1, battleType));
                // porneste batalia pentru Antrenorul2 vs Neutrel
                executorService.execute(() -> out[1] += battle.neutrelBattle(coach2, battleType));
                executorService.shutdown();
                try {
                    // exclude posibilitatea de intrerupere a thread-ului
                    executorService.awaitTermination(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (String s : out) output += s; // unifica output-ul de la batalii
            }
            else {
                // porneste batalia Antrenor vs Antrenor
                output += battle.coachesBattle(coach1, coach2);
                break;
            }
        }
        return output;
    }

}
