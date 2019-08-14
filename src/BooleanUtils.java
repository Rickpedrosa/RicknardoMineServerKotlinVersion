import java.util.List;

class BooleanUtils {

    static boolean armorValidation(List<Enchantment> armor_enchants, int enchant_index){
        return armor_enchants.get(enchant_index).getCategory().equals(MineItem.CATEGORY_EQUIPMENT_ALL) ||
                armor_enchants.get(enchant_index).getCategory().equals(MineItem.CATEGORY_ANYTHING);
    }

    static boolean bootsValidation(List<Enchantment> armor_enchants, int enchant_index){
        return armor_enchants.get(enchant_index).getCategory().equals(MineItem.CATEGORY_EQUIPMENT_BOOTS) ||
                armor_enchants.get(enchant_index).getCategory().equals(MineItem.CATEGORY_ANYTHING);
    }

    private BooleanUtils(){}
}
