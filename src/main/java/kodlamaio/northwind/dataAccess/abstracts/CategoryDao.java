package kodlamaio.northwind.dataAccess.abstracts;

import kodlamaio.northwind.entities.concretes.Category;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Short> {
}
