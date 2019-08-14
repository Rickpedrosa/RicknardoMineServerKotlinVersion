import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final List<Enchantment> armor_enchants = Stream.of(MineItem.ENCHANTS_ARMOR, MineItem.ENCHANTS_ANYTHING)
            .flatMap(Collection::stream).collect(Collectors.toList());
    private static final List<Enchantment> sword_enchants = Stream.of(MineItem.ENCHANTS_SWORD, MineItem.ENCHANTS_ANYTHING)
            .flatMap(Collection::stream).collect(Collectors.toList());
    private static final List<Enchantment> bow_enchants = Stream.of(MineItem.ENCHANTS_BOW, MineItem.ENCHANTS_ANYTHING)
            .flatMap(Collection::stream).collect(Collectors.toList());
    private static final List<Enchantment> tools_enchants = Stream.of(MineItem.ENCHANTS_TOOL, MineItem.ENCHANTS_ANYTHING)
            .flatMap(Collection::stream).collect(Collectors.toList());
    private static final List<Enchantment> trident_enchants = Stream.of(MineItem.ENCHANTS_TRIDENT, MineItem.ENCHANTS_ANYTHING)
            .flatMap(Collection::stream).collect(Collectors.toList());

    public static void main(String[] args) {
        //rare, tools, armor, enchanted books, weapons
        Random rng = new Random();
        int option = rng.nextInt(5);
        int playerIndex = 0;

        switch (option) {
            case 0:
                obtainRareItem(rng, playerIndex);
                break;
            case 1:
                obtainTool(rng, playerIndex);
                break;
            case 2:
                obtainArmor(rng, playerIndex);
                break;
            case 3:
                obtainEnchantedBook(rng, playerIndex);
                break;
            case 4:
                obtainWeapons(rng, playerIndex);
                break;
        }
    }

    private static void obtainWeapons(Random rng, int playerIndex) {
        List<String> weapons = MineItem.WEAPONS;
        switch (weapons.get(rng.nextInt(weapons.size()))) {
            case "bow":
                showTheWeapon("bow", rng, bow_enchants, playerIndex);
                break;
            case "diamond_sword":
                showTheWeapon("diamond_sword", rng, sword_enchants, playerIndex);
                break;
            case "trident":
                showTheWeapon("trident", rng, trident_enchants, playerIndex);
                break;
        }
    }

    private static void showTheWeapon(String weapon, Random rng, List<Enchantment> enchantments, int playerIndex) {
        int number_of_enchants = rng.nextInt(enchantments.size()) + 2;
        HashSet<Enchantment> mEnchantments = new HashSet<>();
        for (int i = 0; i < number_of_enchants; i++) {
            mEnchantments.add(enchantments.get(rng.nextInt(enchantments.size())));
        }
        System.out.println(MineItem.giveCommand(playerIndex, 1, weapon, new ArrayList<>(mEnchantments)));
    }

    private static void obtainTool(Random rng, int playerIndex) {
        List<String> tools = MineItem.TOOLS;
        String selected_item = tools.get(rng.nextInt(tools.size()));

        System.out.println(MineItem.giveCommand(playerIndex, 1, selected_item,
                new ArrayList<>(getEnchantmentsExclusive(rng, tools_enchants))));
    }

    private static HashSet<Enchantment> getEnchantmentsExclusive(Random rng, List<Enchantment> enchants) {
        int number_of_enchants = rng.nextInt(enchants.size()) + 2;
        HashSet<Enchantment> enchantments = new HashSet<>();

        Enchantment enchantment;
        for (int i = 0; i < number_of_enchants; i++) {
            enchantment = enchants.get(rng.nextInt(enchants.size()));
            enchantments.add(enchantment);
            if (enchantments.contains(MineItem.ENCHANTS_TOOL.get(1))) {
                if (enchantment.equals(MineItem.ENCHANTS_TOOL.get(1))) {
                    enchantments.remove(MineItem.ENCHANTS_TOOL.get(2));
                }
            } else if (enchantments.contains(MineItem.ENCHANTS_TOOL.get(2))) {
                if (enchantment.equals(MineItem.ENCHANTS_TOOL.get(2))) {
                    enchantments.remove(MineItem.ENCHANTS_TOOL.get(1));
                }
            }
        }
        return enchantments;
    }

    private static void obtainEnchantedBook(Random rng, int playerIndex) {
        List<List<Enchantment>> enchant_categories = new ArrayList<>();
        enchant_categories.add(armor_enchants);
        enchant_categories.add(sword_enchants);
        enchant_categories.add(bow_enchants);
        enchant_categories.add(tools_enchants);
        enchant_categories.add(trident_enchants);

        //elegir randomly la categoria
        //en base al size de esa categoria, construir el libro encantado
        List<Enchantment> elected = enchant_categories.get(rng.nextInt(enchant_categories.size()));

        System.out.println(MineItem.giveEnchantedBook(playerIndex, new ArrayList<>(getEnchantmentsExclusive(rng, elected))));
    }

    private static void obtainArmor(Random rng, int playerIndex) {
        List<String> armor_items = MineItem.ARMOR;
        int armor_index = rng.nextInt(armor_items.size());
        generateArmor(rng, playerIndex, armor_index);
    }

    private static void generateArmor(Random rng, int playerIndex, int armor_index) {
        HashSet<Enchantment> enchants_for_helmet = new HashSet<>();
        int enchants_number;
        int enchant_index;
        switch (armor_index) {
            case 0:  //helmet
                enchants_number = rng.nextInt(3) + 2;
                break;
            case 1: //chest
                enchants_number = rng.nextInt(3) + 2;
                break;
            case 2: //leggings
                enchants_number = rng.nextInt(3) + 2;
                break;
            case 3: //boots
                enchants_number = rng.nextInt(4) + 2;
                break;
            default:
                enchants_number = rng.nextInt(3) + 2;
                break;
        }

        for (int i = 0; i < enchants_number; i++) {
            enchant_index = rng.nextInt(Main.armor_enchants.size());
            if (armor_index == 0 || armor_index == 1 || armor_index == 2) {
                if (BooleanUtils.armorValidation(Main.armor_enchants, enchant_index)) {
                    enchants_for_helmet.add(Main.armor_enchants.get(enchant_index));
                }
            } else {
                if (BooleanUtils.bootsValidation(Main.armor_enchants, enchant_index)) {
                    enchants_for_helmet.add(Main.armor_enchants.get(enchant_index));
                }
            }
        }

        System.out.println(MineItem.giveCommand(
                playerIndex,
                1,
                MineItem.ARMOR.get(armor_index),
                new ArrayList<>(enchants_for_helmet)));
    }

    private static void obtainRareItem(Random rng, int playerIndex) {
        List<String> rare_items = MineItem.RARE;
        int item_index = rng.nextInt(rare_items.size());
        switch (item_index) {
            case 0: //elytra
                int twice_prob = rng.nextInt(10) + 1;
                List<Enchantment> rare_enchants = new ArrayList<>();
                if (twice_prob >= 1 && twice_prob <= 7) {
                    rare_enchants.add(MineItem.ENCHANTS_ANYTHING.get(rng.nextInt(2)));
                } else {
                    rare_enchants.add(MineItem.ENCHANTS_ANYTHING.get(0));
                    rare_enchants.add(MineItem.ENCHANTS_ANYTHING.get(1));
                }
                System.out.println(MineItem.giveCommand(playerIndex, 1, rare_items.get(item_index), rare_enchants));
                break;
            case 1: //beacon
                System.out.println(MineItem.giveCommand(playerIndex, 1, rare_items.get(item_index), null));
                break;
            case 2: //shulkerbox
                System.out.println(MineItem.giveCommand(playerIndex, 3, rare_items.get(item_index), null));
                break;
            case 3: //diamond blocks
                System.out.println(MineItem.giveCommand(playerIndex, 7, rare_items.get(item_index), null));
                break;
            case 4: //wither skulls
                System.out.println(MineItem.giveCommand(playerIndex, 3, rare_items.get(item_index), null));
                break;
            case 5: //tnt
                System.out.println(MineItem.giveCommand(playerIndex, 192, rare_items.get(item_index), null));
                break;
            case 6: //conduit
                System.out.println(MineItem.giveCommand(playerIndex, 1, rare_items.get(item_index), null));
                break;
            case 7: //nether pack
                System.out.println(MineItem.giveCommand(playerIndex, 64, "quartz_block", null));
                System.out.println(MineItem.giveCommand(playerIndex, 64, "blaze_rod", null));
                System.out.println(MineItem.giveCommand(playerIndex, 64, "ghast_tear", null));
                break;
            case 8: //overworld pack
                System.out.println(MineItem.giveCommand(playerIndex, 64, "slime_ball", null));
                System.out.println(MineItem.giveCommand(playerIndex, 64, "blue_ice", null));
                System.out.println(MineItem.giveCommand(playerIndex, 64, "sea_lantern", null));
                break;
        }
    }
}
