package Pokemons;

import Items.Item;
import Other.Ability;
import Other.Attack;
import Other.Coach;

import java.util.*;

public abstract class Pokemon {
    protected int HP;
    protected Attack attack;
    protected int defense;
    protected int specialDefense;
    protected boolean stunned;
    protected List<Ability> abilities;
    protected List<Item> items;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public boolean isStunned() {
        return stunned;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    /**
     * Calculeaza scorul Pokemonului.
     * @return Scorul Pokemonului.
     */
    public int getScore() {
        int res = this.getHP();
        res += this.getAttack().getValue();
        res += this.getDefense();
        res += this.getSpecialDefense();
        return res;
    }

    /**
     * Intoarce cel mai bun Pokemon a antrenorului.
     * @param coach Antrenorul.
     * @return Pokemon cu cel mai mare scor.
     */
    public static Pokemon getBest(Coach coach) {
        Pokemon res = null;
        int max = 0;
        List<Pokemon> pokemons = coach.getPokemons();
        pokemons.sort(Comparator.comparing(Pokemon::getName)); // sorteaza lexicografic lista de pokemoni
        coach.setPokemons(pokemons);
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getScore() > max) {
                res = pokemon;
                max = pokemon.getScore();
            }
        }
        return res;
    }

    /**
     *
     * @return Abilitate random din abilitatile disponibile a Pokemonului.
     */
    public Ability getAbility() {
        Random random = new Random();
        List<Ability> readyAbilities = new ArrayList<>();
        for (Ability ability : this.getAbilities())
            if (ability.getCurrCooldown() == 0) readyAbilities.add(ability);
        if (readyAbilities.size() == 0) return null;
        return readyAbilities.get(random.nextInt(readyAbilities.size()));
    }

    /**
     * Reseteaza cooldown-ul curent a abilitatilor Pokemonului.
     */
    public void resetAbilitiesCooldown() {
        if (this.getAbilities().size() == 0) return;
        for (Ability ability : this.getAbilities())
            ability.setCurrCooldown(0);
    }

    /**
     * Actualizeaza cooldown-ul curent a Pokemonului.
     */
    public void refreshCooldown() {
        for (Ability ability : this.getAbilities())
            if (ability.getCurrCooldown() > 0) ability.setCurrCooldown(ability.getCurrCooldown() - 1);
    }

    /**
     * Metoda de atac a Pokemonului.
     * @param opponent Pokemonul oponent.
     * @param thisAbility Abilitatea care o foloseste Pokemonul.
     * @param opponentAbility Abilitatea oponentului.
     * @return Output-ul metodei.
     */
    public String attack(Pokemon opponent, Ability thisAbility, Ability opponentAbility) {
        String output = "";
        // verifica daca Pokemonul este in stun, deci nu poate ataca
        if (this.isStunned()) {
            output += this.getName() + " is unable to attack because stunned.\n";
            this.setStunned(false);
            return output;
        }
        // verifica daca oponentul eschiveaza atacul
        if (opponentAbility != null && opponentAbility.isDodge()) {
            output += opponent.getName() + " dodges " + this.getName() + "'s attack.\n";
            return output;
        }

        // atac tip abilitate
        int attackType = 1;
        if (thisAbility == null) attackType = 0; // atac normal/special
        if (attackType == 0) {
            int damage;
            Attack attack = this.getAttack();
            if (attack.getType() == 0) {
                // calculeaza si aplica damage-ul pentru Normal Attack
                damage = this.getAttack().getValue() - opponent.getDefense();
                if (damage < 0) damage = 0;
                opponent.setHP(opponent.getHP() - damage);
                output += this.getName() + " attempts normal attack with value " + attack.getValue() + " on " +
                        opponent.getName() + " with defense value: " + opponent.getDefense() + "...\n";
            }
            else {
                // calculeaza si aplica damage-ul pentru Special Attack
                damage = this.getAttack().getValue() - opponent.getSpecialDefense();
                if (damage < 0) damage = 0;
                opponent.setHP(opponent.getHP() - damage);
                output += this.getName() + " attempts special attack with value " + attack.getValue() + " on " +
                        opponent.getName() + " with special defense value: " + opponent.getDefense() + "...\n";
            }
            output += "\tDamage dealt: " + damage + ".\n";
            return output;
        }

        // aplica damage-ul de la abilitate
        opponent.setHP(opponent.getHP() - thisAbility.getDamage());
        output += "\tDamage dealt: " + thisAbility.getDamage() + ".\n";
        return output;
    }


    /**
     * +1 la caracteristicile castigatorului
     */
    public void win() {
        this.setHP(this.getHP() + 1);
        this.getAttack().setValue(this.getAttack().getValue() + 1);
        this.setDefense(this.getDefense() + 1);
        this.setSpecialDefense(this.getSpecialDefense() + 1);
    }

    @Override
    public String toString() {
        String res = "Pokemon:\n";
        res += "\tName: " + this.getClass().getSimpleName() + ".\n";
        res += "\tHP: " + this.getHP() + ".\n";
        String aux;
        if (this.getAttack().getType() == 0) aux = "Normal";
        else aux = "Special";
        res += "\t" + aux + " attack value: " + this.getAttack().getValue() + ".\n";
        res += "\tDefense value: " + this.getDefense() + ".\n";
        res += "\tSpecial defense value: " + this.getSpecialDefense() + ".\n";
        res += "\tAbilities:\n";
        for (Ability ability : this.getAbilities()) res += ability.toString();
        res += "\tItems:\n";
        for (Item item : this.getItems()) res += "\t\t" + item.getClass().getSimpleName() + "\n";
        res += "\tScore: " + this.getScore() + ".\n";
        return res;
    }

    /**
     * Metoda de creare a copiei Pokemonului. DP: Prototype.
     * @return Copia Pokemonului
     */
    public Pokemon copy() {
        PokemonFactory pokemonFactory = new PokemonFactory();
        Pokemon pokemon = pokemonFactory.getPokemon(this.getClass().getSimpleName());
        pokemon.setHP(this.getHP());
        pokemon.setAttack(this.getAttack());
        pokemon.setDefense(this.getDefense());
        pokemon.setSpecialDefense(this.getSpecialDefense());
        pokemon.setAbilities(this.abilities);
        pokemon.setItems(this.getItems());
        return pokemon;
    }
}


