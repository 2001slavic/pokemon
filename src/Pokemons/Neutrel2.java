package Pokemons;

import Other.Attack;

import java.util.ArrayList;

public class Neutrel2 extends Pokemon {
    public Neutrel2() {
        this.HP = 20;
        this.attack = new Attack(4, 0);
        this.defense = 1;
        this.specialDefense = 1;
        this.stunned = false;
        this.abilities = new ArrayList<>();
        this.items = new ArrayList<>();
    }
}
