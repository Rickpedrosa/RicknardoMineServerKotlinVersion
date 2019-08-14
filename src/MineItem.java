import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MineItem {
    private static final String sEnchantFormat = "{id: %s, lvl: %s}";
    private static final String PREFIX_ITEM = "minecraft:";
    private static final String CATEGORY_SWORD = "sword";
    private static final String CATEGORY_TRIDENT = "trident";
    private static final String CATEGORY_BOW = "bow";
    private static final String CATEGORY_TOOL = "tool";
    static final String CATEGORY_EQUIPMENT_ALL = "equipment";
    static final String CATEGORY_EQUIPMENT_BOOTS = "boots";
    static final String CATEGORY_ANYTHING = "anything";
    private static final String ENCHANT_I = "1";
    private static final String ENCHANT_III = "3";
    private static final String ENCHANT_IV = "4";
    private static final String ENCHANT_V = "5";
    private static final List<String> PLAYERS = new ArrayList<>(Arrays.asList(
            "ricknardo", "kaisernano", "mapache_furioso", "Snake", "YuriKnites", "Fede"
    ));

    static List<String> ARMOR = new ArrayList<>(Arrays.asList(
            "diamond_helmet", "diamond_chestplate", "diamond_leggings", "diamond_boots"
    ));

    static List<String> TOOLS = new ArrayList<>(Arrays.asList(
            "diamond_pickaxe", "diamond_axe", "diamond_shovel"
    ));

    static List<String> WEAPONS = new ArrayList<>(Arrays.asList(
            "bow", "diamond_sword", "trident"
    ));

    static List<String> RARE = new ArrayList<>(Arrays.asList(
            "elytra", "beacon", "shulker_box", "diamond_block", "wither_skeleton_skull", "tnt", "conduit", "nether pack", "overworld pack"
    ));

    static String giveCommand(int playerIndex, int amount, String item, List<Enchantment> enchant) {
        String format = "/give %s %s%s %d"; //player, item, amount, enchant
        return String.format(format, PLAYERS.get(playerIndex), PREFIX_ITEM.concat(item), enchantedItem(enchant), amount);
    }

    static String giveEnchantedBook(int playerIndex, List<Enchantment> enchants) {
        String format = "/give %s minecraft:enchanted_book{StoredEnchantments:[%s]} 1";
        String sEnchant = "";
        sEnchant = getEnchantments(enchants, sEnchant);
        return String.format(format, PLAYERS.get(playerIndex), sEnchant);
    }

    private static String getEnchantments(List<Enchantment> enchants, String sEnchant) {
        for (int i = 0; i < enchants.size(); i++) {
            if (i == enchants.size() - 1) {
                sEnchant = sEnchant.concat(String.format(sEnchantFormat, enchants.get(i).getName(), enchants.get(i).getLevel()));
            } else {
                sEnchant = sEnchant.concat(String.format(sEnchantFormat, enchants.get(i).getName(), enchants.get(i).getLevel())).concat(", ");
            }
        }
        return sEnchant;
    }

    private static String enchantedItem(List<Enchantment> enchants) {
        final String formatGlobal = "{Enchantments:[%s]}";
        String sEnchant = "";
        if (enchants == null) {
            return "";
        } else {
            if (enchants.size() == 1) {
                return String.format(formatGlobal, String.format(sEnchantFormat, enchants.get(0).getName(), enchants.get(0).getLevel()));
            } else if (enchants.size() > 1) {
                sEnchant = getEnchantments(enchants, sEnchant);
                return String.format(formatGlobal, sEnchant);
            } else {
                return "";
            }
        }
    }

    static List<Enchantment> ENCHANTS_ARMOR = new ArrayList<>(Arrays.asList(
            new Enchantment(CATEGORY_EQUIPMENT_ALL, "protection", ENCHANT_IV),
            new Enchantment(CATEGORY_EQUIPMENT_BOOTS, "feather_falling", ENCHANT_IV)
    ));

    static List<Enchantment> ENCHANTS_SWORD = new ArrayList<>(Arrays.asList(
            new Enchantment(CATEGORY_SWORD, "sharpness", ENCHANT_V),
            new Enchantment(CATEGORY_SWORD, "smite", ENCHANT_V),
            new Enchantment(CATEGORY_SWORD, "bane_of_arthropods", ENCHANT_V),
            new Enchantment(CATEGORY_SWORD, "looting", ENCHANT_III),
            new Enchantment(CATEGORY_SWORD, "sweeping_edge", ENCHANT_III)
    ));

    static List<Enchantment> ENCHANTS_BOW = new ArrayList<>(Arrays.asList(
            new Enchantment(CATEGORY_BOW, "power", ENCHANT_V),
            new Enchantment(CATEGORY_BOW, "flame", ENCHANT_I),
            new Enchantment(CATEGORY_BOW, "infinity", ENCHANT_I)
    ));

    static List<Enchantment> ENCHANTS_TRIDENT = new ArrayList<>(Arrays.asList(
            new Enchantment(CATEGORY_TRIDENT, "loyalty", ENCHANT_III),
            new Enchantment(CATEGORY_TRIDENT, "channeling", ENCHANT_I),
            new Enchantment(CATEGORY_TRIDENT, "impaling", ENCHANT_V)
    ));

    static List<Enchantment> ENCHANTS_TOOL = new ArrayList<>(Arrays.asList(
            new Enchantment(CATEGORY_TOOL, "efficiency", ENCHANT_V),
            new Enchantment(CATEGORY_TOOL, "silk_touch", ENCHANT_I),
            new Enchantment(CATEGORY_TOOL, "fortune", ENCHANT_III)
    ));

    static List<Enchantment> ENCHANTS_ANYTHING = new ArrayList<>(Arrays.asList(
            new Enchantment(CATEGORY_ANYTHING, "unbreaking", ENCHANT_III),
            new Enchantment(CATEGORY_ANYTHING, "mending", ENCHANT_I)));
}
