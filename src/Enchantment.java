public class Enchantment {
    private String category;
    private String name;
    private String level;

    public Enchantment(String category, String name, String level) {
        this.category = category;
        this.name = name;
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
