package kodlamaio.northwind.api.controllers;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // SEN BİR CONTROLLERSIN
@RequestMapping("/api/products")
//birden fazla controller olabilir. Kategori yönetimi için controller, ürün yönetimi için controller olabilir. Yani hangi adrese hangi controller karsılık gelecek onu belirticez
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getall") // GET İSTEĞİ
    public DataResult<List<Product>> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("/getAllDesc")
    public DataResult<List<Product>> getAllSorted() {
        return this.productService.getAllSorted();
    }

    @GetMapping("/getAllByPage")
    public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
        return this.productService.getAll(pageNo, pageSize);
    }

    @PostMapping("/addProduct")
    public Result add(@RequestBody Product product) {
        return this.productService.addProduct(product);
    }

    @PostMapping("/addProducts")
    public DataResult<List<Product>> addProducts(@RequestBody List<Product> products) {
        return this.productService.addProducts(products);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public Result deleteProduct(@PathVariable int productId) {
        return this.productService.deleteProduct(productId);
    }

    @PutMapping("/updateProduct")
    public Result updateProduct(@RequestBody Product product) {
        return this.productService.updateProduct(product);
    }

    @GetMapping("/getProductById")
    public DataResult<Product> getProductById(@RequestParam int productId) {
        return this.productService.getProductById(productId);
    }

    @GetMapping("/getByProductName")
    public DataResult<Product> getByProductName(@RequestParam String productName) {
        return this.productService.getByProductName(productName);
    }


    @GetMapping("/getByProductNameAndCategoryId")
    public DataResult<Product> getByProductNameAndCategoryId(@RequestParam("productName") String productName, @RequestParam("categoryId") int categoryId) {
        return this.productService.getByProductNameAndCategoryId(productName, categoryId);
    }

    @GetMapping("/getByProductNameContains")
    public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName) {
        return this.productService.getByProductNameContains(productName);
    }

    @GetMapping("/getProductWithCategoryDetails")
    public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
        return this.productService.getProductWithCategoryDetails();
    }
}
