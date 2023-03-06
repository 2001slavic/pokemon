package Arena;

import Other.Ability;
import Other.Coach;
import Pokemons.Pokemon;
import Pokemons.PokemonFactory;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Battle {

    /**
     * Afiseaza output-ul initial pentru lupta vs Neutrel, si porneste lupta.
     * @param coach Antrenorul care se va lupta impotriva Neutrelului.
     * @param type Neutrel de tip 1 sau 2.
     * @return Output-ul metodei si luptei.
     */
    public String neutrelBattle(Coach coach, int type) {
        PokemonFactory pokemonFactory = new PokemonFactory();
        Pokemon neutrel;
        String output = "";
        // daca Neutrel tip 1
        if (type == 0) {
            output += "Coach " + coach.getName() + " to fight with Neutrel1...\n";
            neutrel = pokemonFactory.getPokemon("Neutrel1");
        }
        // daca Neutrel tip 2
        else {
            output += "Coach " + coach.getName() + " to fight with Neutrel2...\n";
            neutrel = pokemonFactory.getPokemon("Neutrel2");
        }
        // selecteaza un Pokemon random
        Random rand = new Random();
        Pokemon pokemon = coach.getPokemons().get(rand.nextInt(3));
        output += "Coach " + coach.getName() + " picked " + pokemon.getName() + ".\n";
        output += "Pokemon stats:\n";
        output += pokemon.toString();
        output += "Starting battle...\n";

        // intra in batalie
        output += battle(pokemon, neutrel);
        return output;
    }

    /**
     * Selecteaza Pokemonii si afiseaza output-ul initial pentru batalia antrenor vs antrenor.
     * @param coach1 Antrenorul 1.
     * @param coach2 Antrenorul 2.
     * @return Output-ul metodei si bataliilor.
     */
    public String coachesBattle(@NotNull Coach coach1, @NotNull Coach coach2) {
        String output = "Coach " + coach1.getName() + " to fight with coach " + coach2.getName() + "...\n";
        // initializeaza output pentru multi-threading
        String[] out = new String[3];
        Arrays.fill(out, "");
        // lanseasa 3 fire de executie pentru lupte Pokemon[i] vs Pokemon[i]
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < coach1.getPokemons().size(); i++) {
            out[i] += "Battle " + (i + 1) + "...\n";
            // selecteaza Pokemonii i.
            Pokemon pokemon1 = coach1.getPokemons().get(i);
            Pokemon pokemon2 = coach2.getPokemons().get(i);
            out[i] += "Pokemon " + pokemon1.getName() + " to fight with " + pokemon2.getName() + "...\n";
            out[i] += pokemon1.getName() + "'s stats:\n" + pokemon1;
            out[i] += pokemon2.getName() + "'s stats:\n" + pokemon2;
            int finalI = i;
            executorService.execute(() -> out[finalI] += battle(pokemon1, pokemon2));

        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String s : out) output += s;

        // incepe batalia finala
        output += "Final battle...\n";
        // selecteaza cei mai buni Pokemoni
        Pokemon pokemon1 = Pokemon.getBest(coach1);
        Pokemon pokemon2 = Pokemon.getBest(coach2);
        output += pokemon1.getName() + " with score: " + pokemon1.getScore() + " to fight " + pokemon2.getName() +
                " with score: " + pokemon2.getScore() + "...\n";
        output += battle(pokemon1, pokemon2);

        // verifica castigatorul
        if (pokemon1.getHP() <= 0 && pokemon2.getHP() <= 0)
            output += "Adventure resulted in a draw!\n";
        else if (pokemon1.getHP() <= 0)
            output += "Coach " + coach2.getName() + " is the winner of the adventure!\n";
        else
            output += "Coach " + coach1.getName() + " is the winner of the adventure!\n";
        return output;
    }

    /**
     * Metoda de rulare a luptei.
     * @param pokemon1 Pokemon 1.
     * @param pokemon2 Pokemon 2. (Posibil Neutrel).
     * @return Output-ul luptei.
     */
    private String battle(@NotNull Pokemon pokemon1, @NotNull Pokemon pokemon2) {
        String output = "";
        // creeaza copiile pokemonilor
        Pokemon pokemon1copy = pokemon1.copy();
        Pokemon pokemon2copy = pokemon2.copy();
        int tick = 0; // momentul de timp a luptei (mai mult pentru debugging)
        while (pokemon1copy.getHP() > 0 && pokemon2copy.getHP() > 0) {
            output += "tick: " + tick + "...\n";
            // seteaza stun-ul pentru Pokemoni, util pentru a realiza stun-ul pentru urmatorul tick
            boolean pokemon1stunned = pokemon1copy.isStunned();
            boolean pokemon2stunned = pokemon2copy.isStunned();
            Ability pokemon1Ability = null;
            Ability pokemon2Ability = null;

            // primeste abilitatile care vor folosi Pokemonii (null - pentru Neutrel)
            if (!pokemon1stunned)
                pokemon1Ability = pokemon1copy.getAbility();

            if (pokemon1Ability != null)
                output += pokemon1copy.getName() + " attempts to cast ability on " + pokemon2copy.getName() +
                        " with damage: " + pokemon1Ability.getDamage() + "...\n";

            if (!pokemon2stunned)
                pokemon2Ability = pokemon2copy.getAbility();

            if (pokemon2Ability != null)
                output += pokemon2copy.getName() + " attempts to cast ability on " + pokemon1copy.getName() +
                        " with damage: " + pokemon2Ability.getDamage() + "...\n";

            // apeleaza metoda de atac
            output += pokemon1copy.attack(pokemon2copy, pokemon1Ability, pokemon2Ability);
            output += pokemon2copy.attack(pokemon1copy, pokemon2Ability, pokemon1Ability);

            // seteaza stun pe Pokemonul 1, daca e cazul
            if (!pokemon2stunned && pokemon2Ability != null && pokemon2Ability.isStun() &&
                    (pokemon1Ability == null || !pokemon1Ability.isDodge())) {
                pokemon1copy.setStunned(true);
                output += "\t" + pokemon1copy.getName() + " stunned.\n";
            }
            // seteaza stun pe Pokemonul 2, daca e cazul
            if (!pokemon1stunned && pokemon1Ability != null && pokemon1Ability.isStun() &&
                    (pokemon2Ability == null || !pokemon2Ability.isDodge())) {
                pokemon2copy.setStunned(true);
                output += "\t" + pokemon2copy.getName() + " stunned.\n";
            }
            // afiseaza HP-ul curent a Pokemonilor (debugging)
            output += pokemon1copy.getName() + " HP: " + pokemon1copy.getHP() + ".\n";
            output += pokemon2copy.getName() + " HP: " + pokemon2copy.getHP() + ".\n";
            // actualizeaza timpul ramas pentru cooldown a abilitatilor Pokemonilor
            pokemon1copy.refreshCooldown();
            pokemon2copy.refreshCooldown();
            // seteaza abilitatea ca utilizata, si seteaza cooldown-ul curent, daca e cazul
            if (pokemon1Ability != null) pokemon1Ability.setCurrCooldown(pokemon1Ability.getCooldown());
            if (pokemon2Ability != null) pokemon2Ability.setCurrCooldown(pokemon2Ability.getCooldown());
            // urmatoarea iteratie
            tick++;
        }
        // sfarist de batalie

        // caz de egalitate
        if (pokemon1copy.getHP() <= 0 && pokemon2copy.getHP() <= 0) {
            output += announceDraw(pokemon1, pokemon2);
        }
        // castiga Pokemon2
        else if (pokemon1copy.getHP() <= 0) {
            output += announceWinner(pokemon2);
        }
        // castiga Pokemon1
        else if (pokemon2copy.getHP() <= 0) {
            output += announceWinner(pokemon1);
        }
        // reseteaza cooldown-ul curent a Pokemonilor
        pokemon1.resetAbilitiesCooldown();
        pokemon2.resetAbilitiesCooldown();
        return output;
    }

    /**
     * Afiseaza la output informatiile despre castigator.
     * @param winner Pokemon-ul castigator.
     * @return Output-ul.
     */
    private String announceWinner(Pokemon winner) {
        // +1 la caracteristicile castigatorului
        winner.win();
        String output = winner.getName() + " WINS!\n";
        output += winner.getName() + " buffed!\n";
        output += "New stats:\n" + winner;
        return output;
    }

    /**
     * Anunta egalitatea.
     * @param pokemon1 Pokemonul 1.
     * @param pokemon2 Pokemonul 2.
     * @return Output-ul.
     */
    private String announceDraw(Pokemon pokemon1, Pokemon pokemon2) {
        String output = "DRAW!\n";
        output += pokemon1.getName() + "'s score: " + pokemon1.getScore() + ".\n";
        output += pokemon2.getName() + "'s score: " + pokemon2.getScore() + ".\n";
        return output;
    }
}
