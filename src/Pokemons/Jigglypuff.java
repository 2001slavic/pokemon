package Pokemons;

import Other.Ability;
import Other.Attack;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Jigglypuff extends Pokemon {
    public Jigglypuff() {
        this.HP = 34;
        this.attack = new Attack(4, 0);
        this.defense = 2;
        this.specialDefense = 3;
        this.stunned = false;
        this.abilities = Stream.of(new Ability(4, true, false, 4),
                                   new Ability(3, true, false, 4))
                                   .collect(Collectors.toList());
        this.items = new ArrayList<>();
    }
}
