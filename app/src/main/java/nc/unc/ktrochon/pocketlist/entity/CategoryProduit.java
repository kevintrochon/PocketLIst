package nc.unc.ktrochon.pocketlist.entity;


public class CategoryProduit {

    int categoryId = 0;
    String categoryName;

    public CategoryProduit() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
