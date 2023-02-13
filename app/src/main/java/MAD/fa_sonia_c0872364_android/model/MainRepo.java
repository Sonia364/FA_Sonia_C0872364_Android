package MAD.fa_sonia_c0872364_android.model;


import java.util.List;

import javax.inject.Inject;

import MAD.fa_sonia_c0872364_android.db.DbDao;

public class MainRepo {

    DbDao dbDao;

    @Inject
    public MainRepo(DbDao dbDao) {
        this.dbDao = dbDao;
    }

    void addProduct(Product product) {
        dbDao.addCategory(product);
    }

    public List<Product> getAllProducts(){
        return dbDao.getAllProducts();
    }
}
