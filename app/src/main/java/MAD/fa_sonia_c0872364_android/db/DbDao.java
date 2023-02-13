package MAD.fa_sonia_c0872364_android.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import MAD.fa_sonia_c0872364_android.model.Product;

@Dao
public interface DbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCategory(Product category);

    @Query("Select * from products")
    List<Product> getAllProducts();

    @Delete
    void deleteProduct(Product category);

    @Query("UPDATE products SET name =:productName WHERE id = :id")
    void updateProductName(String productName,int id);
}
