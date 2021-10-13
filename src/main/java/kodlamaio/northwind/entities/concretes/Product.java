package kodlamaio.northwind.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // GETTER SETTER LARI HAZIR GETİRMESİ İÇİN
@Entity // PRODUCT BİR ENTITIYDIR
@Table(name = "products") // DB DE HANGİ TABLOYA KARŞILIK GELDİĞİNİ BELİRT
@AllArgsConstructor // TÜM ALANLARI KULLANARAK CONSTRUCTOR EKLE
@NoArgsConstructor // ALANLARI KULLANMADAN BOŞ CONSTRUCTOR EKLE
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID bir bir arttırılacak
    @Column(name = "product_id")
    private int id;

    /*
    @Column(name = "category_id")
    private int categoryId;
    */

    @Column(name = "product_name")
    private String productName;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "units_in_stock")
    private short unitsInStock;

    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;


}
