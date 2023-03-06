package Pokemons;

import Other.Ability;
import Other.Attack;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Snorlax extends Pokemon {
    public Snorlax() {
        this.HP = 62;
        this.attack = new Attack(3, 0);
        this.defense = 6;
        this.specialDefense = 4;
        this.stunned = false;
        this.abilities = Stream.of(new Ability(4, true, false, 5),
                                   new Ability(0, false, true, 5))
                                   .collect(Collectors.toList());
        this.items = new ArrayList<>();
    }
}
