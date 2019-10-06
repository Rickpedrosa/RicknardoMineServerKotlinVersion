import java.util.stream.Collectors
import kotlin.streams.asSequence

internal object MineItem {
    private const val PREFIX_ITEM = "minecraft:"
    private const val CATEGORY_SWORD = "sword"
    private const val CATEGORY_TRIDENT = "trident"
    private const val CATEGORY_BOW = "bow"
    private const val CATEGORY_TOOL = "tool"
    const val CATEGORY_EQUIPMENT_ALL = "equipment"
    const val CATEGORY_EQUIPMENT_BOOTS = "boots"
    const val CATEGORY_ANYTHING = "anything"
    private const val ENCHANT_I = "1"
    private const val ENCHANT_III = "3"
    private const val ENCHANT_IV = "4"
    private const val ENCHANT_V = "5"
    val PLAYERS = listOf("ricknardo", "kaisernano", "mapache_furioso", "Snake", "YuriKnites", "Fede")

    val ARMOR: List<String> = listOf(
            "diamond_helmet", "diamond_chestplate", "diamond_leggings", "diamond_boots"
    )

    val TOOLS: List<String> = listOf(
            "diamond_pickaxe", "diamond_axe", "diamond_shovel"
    )

    val WEAPONS: List<String> = listOf(
            "bow", "diamond_sword", "trident"
    )

    val RARE: List<String> = listOf(
            "elytra", "beacon", "shulker_box", "diamond_block", "wither_skeleton_skull", "tnt", "conduit", "nether pack", "overworld pack"
    )

    val ENCHANTS_ARMOR: List<Enchantment> = listOf(
            Enchantment(CATEGORY_EQUIPMENT_ALL, "protection", ENCHANT_IV),
            Enchantment(CATEGORY_EQUIPMENT_BOOTS, "feather_falling", ENCHANT_IV)
    )

    val ENCHANTS_SWORD: List<Enchantment> = listOf(
            Enchantment(CATEGORY_SWORD, "sharpness", ENCHANT_V),
            Enchantment(CATEGORY_SWORD, "smite", ENCHANT_V),
            Enchantment(CATEGORY_SWORD, "bane_of_arthropods", ENCHANT_V),
            Enchantment(CATEGORY_SWORD, "looting", ENCHANT_III),
            Enchantment(CATEGORY_SWORD, "sweeping_edge", ENCHANT_III)
    )

    val ENCHANTS_BOW: List<Enchantment> = listOf(
            Enchantment(CATEGORY_BOW, "power", ENCHANT_V),
            Enchantment(CATEGORY_BOW, "flame", ENCHANT_I),
            Enchantment(CATEGORY_BOW, "infinity", ENCHANT_I)
    )

    val ENCHANTS_TRIDENT: List<Enchantment> = listOf(
            Enchantment(CATEGORY_TRIDENT, "loyalty", ENCHANT_III),
            Enchantment(CATEGORY_TRIDENT, "channeling", ENCHANT_I),
            Enchantment(CATEGORY_TRIDENT, "impaling", ENCHANT_V)
    )

    val ENCHANTS_TOOL: List<Enchantment> = listOf(
            Enchantment(CATEGORY_TOOL, "efficiency", ENCHANT_V),
            Enchantment(CATEGORY_TOOL, "silk_touch", ENCHANT_I),
            Enchantment(CATEGORY_TOOL, "fortune", ENCHANT_III)
    )

    val ENCHANTS_ANYTHING: List<Enchantment> = listOf(
            Enchantment(CATEGORY_ANYTHING, "unbreaking", ENCHANT_III),
            Enchantment(CATEGORY_ANYTHING, "mending", ENCHANT_I))

    fun giveCommand(playerIndex: Int, amount: Int, item: String, enchant: List<Enchantment>?): String {
        //player, item, amount, enchant
        return "/give ${PLAYERS[playerIndex]} ${PREFIX_ITEM.plus(item)}${enchantedItem(enchant)} $amount"
    }

    fun giveEnchantedBook(playerIndex: Int, enchants: List<Enchantment>): String {
        return "/give ${PLAYERS[playerIndex]} minecraft:enchanted_book{StoredEnchantments:[${getEnchantments(enchants)}]} 1"
    }

    private fun getEnchantments(enchants: List<Enchantment>): String {
        return enchants.stream()
                .map { value -> value.toString() }
                .collect(Collectors.joining(","))
    }

    private fun enchantedItem(enchants: List<Enchantment>?): String {
        return when {
            enchants == null -> ""
            enchants.isEmpty() -> ""
            enchants.size == 1 -> "{Enchantments:[${enchants[0]}]}"
            enchants.size > 1 -> "{Enchantments:[${getEnchantments(enchants)}]}"
            else -> ""
        }
    }
}
