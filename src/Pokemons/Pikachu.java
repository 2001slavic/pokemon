package Pokemons;

import Other.Ability;
import Other.Attack;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pikachu extends Pokemon {
    public Pikachu() {
        this.HP = 35;
        this.attack = new Attack(4, 1);
        this.defense = 2;
        this.specialDefense = 3;
        this.stunned = false;
        this.abilities = Stream.of(new Ability(6, false, false, 4),
                                   new Ability(4, true, true, 5))
                                   .collect(Collectors.toList());
        this.items = new ArrayList<>();
    }
}
