package Items;

import org.jetbrains.annotations.NotNull;

public class ItemFactory {
    public Item getItem(@NotNull String name) {
        switch (name) {
            case "Shield" -> {return Shield.getInstance();}
            case "Vest" -> {return Vest.getInstance();}
            case "Sword" -> {return Sword.getInstance();}
            case "Magic wand" -> {return MagicWand.getInstance();}
            case "Vitamins" -> {return Vitamins.getInstance();}
            case "Christmas tree" -> {return ChristmasTree.getInstance();}
            case "Cape" -> {return Cape.getInstance();}
            default -> {return null;}
        }
    }
}
