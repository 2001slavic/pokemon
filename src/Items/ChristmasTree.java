package Items;

import Pokemons.Pokemon;
import org.jetbrains.annotations.NotNull;

public class ChristmasTree implements Item {
    private static ChristmasTree singleton;

    public static Item getInstance() {
        if (singleton == null)
            singleton = new ChristmasTree();
        return singleton;
    }

    @Override
    public void equip(@NotNull Pokemon pokemon) {
        if (pokemon.getItems().contains(this)) return;
        if (pokemon.getAttack().getType() == 0)
            pokemon.getAttack().setValue(pokemon.getAttack().getValue() + 3);
        pokemon.setDefense(pokemon.getDefense() + 1);
        pokemon.getItems().add(this);
    }

    @Override
    public void divest(@NotNull Pokemon pokemon) {
        if (!pokemon.getItems().remove(this)) return;
        pokemon.getAttack().setValue(pokemon.getAttack().getValue() - 3);
        pokemon.setDefense(pokemon.getDefense() - 1);
    }

    @Override
    public String getName() {
        return "Christmas tree";
    }
}
