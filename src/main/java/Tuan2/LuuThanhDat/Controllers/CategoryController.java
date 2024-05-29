package Tuan2.LuuThanhDat.Controllers;


import Tuan2.LuuThanhDat.Entities.Category;
import Tuan2.LuuThanhDat.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public String showAllCategories(@NotNull Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category/list";
    }

    @GetMapping("/add")
    public String addCategoryForm(@NotNull Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }
    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category) {
        if(categoryService.getCategoryById(category.getId()).isEmpty())
            categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@NotNull Model model, @PathVariable long id)
    {
        var category = categoryService.getCategoryById(id).orElse(null);
        model.addAttribute("category", category != null ? category : new Category());
        return "category/edit";
    }
    @PostMapping("/edit")
    public String editCategory(@ModelAttribute("category") Category category) {
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable long id) {
        if (categoryService.getCategoryById(id).isPresent())
            categoryService.deleteCategoryById(id);
        return "redirect:/categories";
    }
}
