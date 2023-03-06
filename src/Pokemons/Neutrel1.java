package Pokemons;

import Other.Attack;

import java.util.ArrayList;

public class Neutrel1 extends Pokemon {
    public Neutrel1() {
        this.HP = 10;
        this.attack = new Attack(3, 0);
        this.defense = 1;
        this.specialDefense = 1;
        this.stunned = false;
        this.abilities = new ArrayList<>();
        this.items = new ArrayList<>();
    }
}
