package Pokemons;

import Other.Ability;
import Other.Attack;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Charmander extends Pokemon {
    public Charmander() {
        this.HP = 50;
        this.attack = new Attack(4, 0);
        this.defense = 3;
        this.specialDefense = 2;
        this.stunned = false;
        this.abilities = Stream.of(new Ability(4, true, false, 4),
                                   new Ability(7, false, false, 6))
                                   .collect(Collectors.toList());
        this.items = new ArrayList<>();
    }
}
