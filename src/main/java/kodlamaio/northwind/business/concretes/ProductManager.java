package kodlamaio.northwind.business.concretes;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.*;
import kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // BU CLASS SERVİS GÖREVİ GÖRECEK.
public class ProductManager implements ProductService {

    private ProductDao productDao;

    @Autowired
    // bağımlılıkları enjekte etmek için kullanıyoruz.Spring bize productDao'nun ne olduğunu benim sana verebilmem için bu constructor'ın üstüne autowired ekle diyor
    public ProductManager(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public DataResult<List<Product>> getAll() {
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(), "Data Listelendi");

    }

    @Override
    public DataResult<List<Product>> getAllSorted() {

        Sort sort = Sort.by(Sort.Direction.DESC, "productName");
        return new SuccessDataResult<List<Product>>(this.productDao.findAll(sort), "Başarılı");
    }

    @Override
    public DataResult<List<Product>> getAll(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return new SuccessDataResult<List<Product>>(this.productDao.findAll(pageable).getContent());
    }

    @Override
    public Result addProduct(Product product) {
        this.productDao.save(product);
        return new SuccessResult("Ürün eklendi.");
    }

    @Override
    public DataResult<List<Product>> addProducts(List<Product> products) {
        List<Product> products1 = this.productDao.saveAll(products);

        return new SuccessDataResult<List<Product>>(products1, "İşlem başarılı");
    }

    @Override
    public Result deleteProduct(int productId) {
        boolean exists = this.productDao.existsById(productId);
        if (!exists) {
            return new ErrorResult("Ürün silinme işlemi başarısız. Ürün bulunamadı.");
        }
        this.productDao.deleteById(productId);
        return new SuccessResult("Ürün Silindi");
    }

    @Override
    public Result updateProduct(Product product) {

        Product existingProduct = this.productDao.findById(product.getId()).orElse(null);

        existingProduct.setProductName(product.getProductName());
        existingProduct.setUnitPrice(product.getUnitPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setQuantityPerUnit(product.getQuantityPerUnit());
        existingProduct.setUnitsInStock(product.getUnitsInStock());

        this.productDao.save(existingProduct);
        return new SuccessResult("Ürün Güncellendi");
    }

    @Override
    public DataResult<Product> getProductById(int productId) {
        return new SuccessDataResult<Product>(this.productDao.findById(productId).orElse(null), "Data Listelendi");
    }

    @Override
    public DataResult<Product> getByProductName(String productName) {
        return new SuccessDataResult<Product>(this.productDao.getByProductName(productName), "Data Listelendi");
    }

    @Override
    public DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId) {
        //business code. şartları buraya yazıcaz daha sonra
        return new SuccessDataResult<Product>(this.productDao.getByProductNameAndCategory_CategoryId(productName, categoryId), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameOrCategory_CategoryId(productName, categoryId), "Data Listelendi");

    }

    @Override
    public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByCategory_CategoryIdIn(categories), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameContains(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameContains(productName), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameStartsWith(productName), "Data Listelendi");
    }

    @Override
    public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
        return new SuccessDataResult<List<Product>>(this.productDao.getByNameAndCategory(productName, categoryId), "Data Listelendi");
    }

    @Override
    public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
        return new SuccessDataResult<List<ProductWithCategoryDto>>(this.productDao.getProductWithCategoryDetails());
    }
}
