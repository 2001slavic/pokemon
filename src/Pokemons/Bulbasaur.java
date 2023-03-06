package Pokemons;

import Other.Ability;
import Other.Attack;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        this.HP = 42;
        this.attack = new Attack(5, 1);
        this.defense = 3;
        this.specialDefense = 1;
        this.stunned = false;
        this.abilities = Stream.of(new Ability(6, false, false, 4),
                                   new Ability(5, false, false, 3))
                                   .collect(Collectors.toList());
        this.items = new ArrayList<>();
    }
}
