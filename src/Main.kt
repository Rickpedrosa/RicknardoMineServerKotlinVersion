import java.util.*
import kotlin.collections.HashSet

private val armorEnchants = MineItem.ENCHANTS_ARMOR.plus(MineItem.ENCHANTS_ANYTHING)

private val swordEnchants = MineItem.ENCHANTS_SWORD.plus(MineItem.ENCHANTS_ANYTHING)

private val bowEnchants = MineItem.ENCHANTS_BOW.plus(MineItem.ENCHANTS_ANYTHING)

private val toolsEnchants = MineItem.ENCHANTS_TOOL.plus(MineItem.ENCHANTS_ANYTHING)

private val tridentEnchants = MineItem.ENCHANTS_TRIDENT.plus(MineItem.ENCHANTS_ANYTHING)

fun main() {
    //rare, tools, armor, enchanted books, weapons
    val rng = Random()
    val option = rng.nextInt(5)
    val playerIndex = rng.nextInt(MineItem.PLAYERS.size - 1)

    println("Option -> $option - PlayerIndex -> $playerIndex")
    when (option) {
        0 -> obtainRareItem(rng, playerIndex)
        1 -> obtainTool(rng, playerIndex)
        2 -> obtainArmor(rng, playerIndex)
        3 -> obtainEnchantedBook(rng, playerIndex)
        4 -> obtainWeapons(rng, playerIndex)
    }
}

private fun obtainWeapons(rng: Random, playerIndex: Int) {
    val weapons = MineItem.WEAPONS
    when (weapons[rng.nextInt(weapons.size)]) {
        "bow" -> showTheWeapon("bow", rng, bowEnchants, playerIndex)
        "diamond_sword" -> showTheWeapon("diamond_sword", rng, swordEnchants, playerIndex)
        "trident" -> showTheWeapon("trident", rng, tridentEnchants, playerIndex)
    }
}

private fun showTheWeapon(weapon: String, rng: Random, enchantments: List<Enchantment>, playerIndex: Int) {
    val numberOfEnchants = rng.nextInt(enchantments.size) + 2
    val mEnchantments: HashSet<Enchantment> = HashSet()
    for (i in 0 until numberOfEnchants) mEnchantments.add(enchantments[rng.nextInt(enchantments.size)])
    println(MineItem.giveCommand(playerIndex, 1, weapon, ArrayList(mEnchantments)))
}

private fun obtainTool(rng: Random, playerIndex: Int) {
    val tools = MineItem.TOOLS
    val selectedItem = tools[rng.nextInt(tools.size)]
    println(MineItem.giveCommand(playerIndex, 1, selectedItem,
            ArrayList(getEnchantmentsExclusive(rng, toolsEnchants))))
}

private fun getEnchantmentsExclusive(rng: Random, enchantsList: List<Enchantment>): HashSet<Enchantment> {
    val numberOfEnchants = rng.nextInt(enchantsList.size) + 2
    val enchantmentsHash: HashSet<Enchantment> = HashSet()

    var enchantment: Enchantment
    for (i in 0 until numberOfEnchants) {
        enchantment = enchantsList[rng.nextInt(enchantsList.size)]
        enchantmentsHash.add(enchantment)
        if (enchantmentsHash.contains(MineItem.ENCHANTS_TOOL[1])) {
            if (enchantment == MineItem.ENCHANTS_TOOL[1])
                enchantmentsHash.remove(MineItem.ENCHANTS_TOOL[2])

        } else if (enchantmentsHash.contains(MineItem.ENCHANTS_TOOL[2])) {
            if (enchantment == MineItem.ENCHANTS_TOOL[2]) {
                enchantmentsHash.remove(MineItem.ENCHANTS_TOOL[1])
            }
        }
    }
    return enchantmentsHash
}

private fun obtainEnchantedBook(rng: Random, playerIndex: Int) {
    val enchantCategories: MutableList<List<Enchantment>> = ArrayList()
    enchantCategories.add(armorEnchants)
    enchantCategories.add(swordEnchants)
    enchantCategories.add(bowEnchants)
    enchantCategories.add(toolsEnchants)
    enchantCategories.add(tridentEnchants)

    //elegir randomly la categoria
    //en base al size de esa categoria, construir el libro encantado
    val elected = enchantCategories[rng.nextInt(enchantCategories.size)]
    println(MineItem.giveEnchantedBook(playerIndex, ArrayList(getEnchantmentsExclusive(rng, elected))))
}

private fun obtainArmor(rng: Random, playerIndex: Int) {
    val armorItems = MineItem.ARMOR
    val armorIndex = rng.nextInt(armorItems.size)
    generateArmor(rng, playerIndex, armorIndex)
}

private fun generateArmor(rng: Random, playerIndex: Int, armor_index: Int) {
    val enchantsForHelmet: HashSet<Enchantment> = HashSet()
    val enchantsNumber: Int = when (armor_index) {
        0  //helmet
        -> rng.nextInt(3) + 2
        1 //chest
        -> rng.nextInt(3) + 2
        2 //leggings
        -> rng.nextInt(3) + 2
        3 //boots
        -> rng.nextInt(4) + 2
        else -> rng.nextInt(3) + 2
    }

    var enchantIndex: Int
    for (i in 0 until enchantsNumber) {
        enchantIndex = rng.nextInt(armorEnchants.size)
        if (armor_index == 0 || armor_index == 1 || armor_index == 2) {
            if (armorValidation(armorEnchants, enchantIndex)) {
                enchantsForHelmet.add(armorEnchants[enchantIndex])
            }
        } else {
            if (bootsValidation(armorEnchants, enchantIndex)) {
                enchantsForHelmet.add(armorEnchants[enchantIndex])
            }
        }
    }

    println(MineItem.giveCommand(
            playerIndex,
            1,
            MineItem.ARMOR[armor_index],
            ArrayList(enchantsForHelmet)))
}

private fun obtainRareItem(rng: Random, playerIndex: Int) {
    val rareItems = MineItem.RARE
    when (val itemIndex = rng.nextInt(rareItems.size - 1)) {
        0 //elytra
        -> {
            val twiceProb = rng.nextInt(10) + 1
            val rareEnchants: MutableList<Enchantment> = ArrayList()
            if (twiceProb in 1..7) {
                rareEnchants.add(MineItem.ENCHANTS_ANYTHING[rng.nextInt(2)])
            } else {
                rareEnchants.add(MineItem.ENCHANTS_ANYTHING[0])
                rareEnchants.add(MineItem.ENCHANTS_ANYTHING[1])
            }
            println(MineItem.giveCommand(playerIndex, 1, rareItems[itemIndex], rareEnchants))
        }
        1 //beacon
        -> println(MineItem.giveCommand(playerIndex, 1, rareItems[itemIndex], null))
        2 //shulkerbox
        -> println(MineItem.giveCommand(playerIndex, 3, rareItems[itemIndex], null))
        3 //diamond blocks
        -> println(MineItem.giveCommand(playerIndex, 7, rareItems[itemIndex], null))
        4 //wither skulls
        -> println(MineItem.giveCommand(playerIndex, 3, rareItems[itemIndex], null))
        5 //tnt
        -> println(MineItem.giveCommand(playerIndex, 192, rareItems[itemIndex], null))
        6 //conduit
        -> println(MineItem.giveCommand(playerIndex, 1, rareItems[itemIndex], null))
        7 //nether pack
        -> {
            println(MineItem.giveCommand(playerIndex, 64, "quartz_block", null))
            println(MineItem.giveCommand(playerIndex, 64, "blaze_rod", null))
            println(MineItem.giveCommand(playerIndex, 64, "ghast_tear", null))
        }
        8 //overworld pack
        -> {
            println(MineItem.giveCommand(playerIndex, 64, "slime_ball", null))
            println(MineItem.giveCommand(playerIndex, 64, "blue_ice", null))
            println(MineItem.giveCommand(playerIndex, 64, "sea_lantern", null))
        }
    }
}
