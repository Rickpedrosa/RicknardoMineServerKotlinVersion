fun armorValidation(armor_enchants: List<Enchantment>, enchant_index: Int): Boolean {
    return (armor_enchants[enchant_index].category === MineItem.CATEGORY_EQUIPMENT_ALL)
            || (armor_enchants[enchant_index].category === MineItem.CATEGORY_ANYTHING)
}

fun bootsValidation(armor_enchants: List<Enchantment>, enchant_index: Int): Boolean {
    return (armor_enchants[enchant_index].category === MineItem.CATEGORY_EQUIPMENT_BOOTS)
            || (armor_enchants[enchant_index].category === MineItem.CATEGORY_ANYTHING)
}

