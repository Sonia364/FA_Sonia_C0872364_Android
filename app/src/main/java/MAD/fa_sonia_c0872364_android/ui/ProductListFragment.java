package MAD.fa_sonia_c0872364_android.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.List;

import MAD.fa_sonia_c0872364_android.databinding.FragmentProductListBinding;
import MAD.fa_sonia_c0872364_android.model.MainViewModel;
import MAD.fa_sonia_c0872364_android.model.Product;

public class ProductListFragment extends Fragment {

    FragmentProductListBinding binding = null;

    private List<Product> productList;

    private MainViewModel viewModel;
    private AlertDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(binding == null){
            binding = FragmentProductListBinding.inflate(inflater,container, false);
        }
        return binding.getRoot();
    }
}
