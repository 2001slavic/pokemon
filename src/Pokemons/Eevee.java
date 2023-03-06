package Pokemons;

import Other.Ability;
import Other.Attack;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Eevee extends Pokemon {
    public Eevee() {
        this.HP = 39;
        this.attack = new Attack(4, 1);
        this.defense = 3;
        this.specialDefense = 3;
        this.stunned = false;
        this.abilities = Stream.of(new Ability(5, false, false, 3),
                                   new Ability(3, true, false, 3))
                                   .collect(Collectors.toList());
        this.items = new ArrayList<>();
    }
}
