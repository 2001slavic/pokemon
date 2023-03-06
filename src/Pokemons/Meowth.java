package Pokemons;

import Other.Ability;
import Other.Attack;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Meowth extends Pokemon {
    public Meowth() {
        this.HP = 41;
        this.attack = new Attack(3, 0);
        this.defense = 4;
        this.specialDefense = 2;
        this.stunned = false;
        this.abilities = Stream.of(new Ability(5, false, true, 4),
                                   new Ability(1, false, true, 3))
                                   .collect(Collectors.toList());
        this.items = new ArrayList<>();
    }
}
