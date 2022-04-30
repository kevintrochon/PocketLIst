package nc.unc.ktrochon.pocketlist.service;

import android.content.Context;
import java.util.List;

import nc.unc.ktrochon.pocketlist.entity.CategoryProduit;
import nc.unc.ktrochon.pocketlist.repository.CategoryRepository;

public class CategoryServices {
    private CategoryRepository repository;
    private CategoryProduit categoryProduit;

    public CategoryProduit getCategoryProduitByName(Context context, String categoryName){
        repository = new CategoryRepository(context);
        categoryProduit = repository.getCategoryByName(categoryName);
        return categoryProduit;
    }

    public List<CategoryProduit> getAllCategory(Context context){
        repository = new CategoryRepository(context);
        List<CategoryProduit> categories = repository.getAllCategory();
        return  categories;
    }

}
