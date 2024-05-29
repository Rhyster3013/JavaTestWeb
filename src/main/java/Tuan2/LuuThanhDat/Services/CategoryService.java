package Tuan2.LuuThanhDat.Services;

import Tuan2.LuuThanhDat.Entities.Book;
import Tuan2.LuuThanhDat.Entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final List<Category> categories;

    public List<Category> getAllCategories(){
        return categories;
    }

    public Optional<Category> getCategoryById(Long id){
        return categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst();
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public void updateCategory(Category category) {
        var categoryOptional = getCategoryById(category.getId());
        if (categoryOptional.isPresent()) {
            Category categoryUpdate = categoryOptional.get();
            categoryUpdate.setTitle(category.getTitle());
            categoryUpdate.setDescription(category.getDescription());
        }
    }

    public void deleteCategoryById(Long id) {
        getCategoryById(id).ifPresent(categories::remove);
    }
}
