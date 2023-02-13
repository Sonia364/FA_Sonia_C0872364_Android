package MAD.fa_sonia_c0872364_android.model;

import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {

    MainRepo repo;

    @Inject
    public MainViewModel(MainRepo repo) {
        this.repo = repo;
    }

    public void addProduct(Product product) {
        repo.addProduct(product);
    }

    public List<Product> getAllProducts(){
        return repo.getAllProducts();
    }

}
