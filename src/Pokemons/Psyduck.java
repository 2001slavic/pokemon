package Pokemons;

import Other.Ability;
import Other.Attack;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Psyduck extends Pokemon {
    public Psyduck() {
        this.HP = 43;
        this.attack = new Attack(3, 0);
        this.defense = 3;
        this.specialDefense = 3;
        this.stunned = false;
        this.abilities = Stream.of(new Ability(2, false, false, 4),
                                   new Ability(2, true, false, 5))
                                   .collect(Collectors.toList());
        this.items = new ArrayList<>();
    }
}
