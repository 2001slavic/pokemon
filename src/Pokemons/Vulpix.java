package Pokemons;

import Other.Ability;
import Other.Attack;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Vulpix extends Pokemon {
    public Vulpix() {
        this.HP = 36;
        this.attack = new Attack(5, 0);
        this.defense = 2;
        this.specialDefense = 4;
        this.stunned = false;
        this.abilities = Stream.of(new Ability(8, true, false, 6),
                                   new Ability(2, false, true, 7))
                                   .collect(Collectors.toList());
        this.items = new ArrayList<>();
    }
}
