package kodlamaio.northwind.api.controllers;

import kodlamaio.northwind.business.abstracts.CategoryService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // SEN BİR CONTROLLERSIN
@RequestMapping("/api/categories")
public class CategoriesController {
    private CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getall") // GET İSTEĞİ
    public DataResult<List<Category>> getAll() {
        return this.categoryService.getAll();
    }

    @PostMapping("/addCategory")
    public Result addCategory(@RequestBody Category category) {
        return this.categoryService.addCategory(category);
    }
}
