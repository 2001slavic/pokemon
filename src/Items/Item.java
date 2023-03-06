package Items;

import Pokemons.Pokemon;

public interface Item {
    void equip(Pokemon pokemon);
    void divest(Pokemon pokemon);
    String getName();
}
