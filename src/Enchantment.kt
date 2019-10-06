data class Enchantment(var category: String?, var name: String?, var level: String?) {
    override fun toString(): String {
        return "{id:${name},level:${level}}"
    }
}