package Pokemons;

import Other.Ability;
import Other.Attack;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Squirtle extends Pokemon {
    public Squirtle() {
        this.HP = 60;
        this.attack = new Attack(3, 1);
        this.defense = 5;
        this.specialDefense = 5;
        this.stunned = false;
        this.abilities = Stream.of(new Ability(4, false, false, 3),
                                   new Ability(2, true, false, 2))
                                   .collect(Collectors.toList());
        this.items = new ArrayList<>();
    }
}
