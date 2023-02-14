package MAD.fa_sonia_c0872364_android.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

import MAD.fa_sonia_c0872364_android.R;
import MAD.fa_sonia_c0872364_android.databinding.ProductRowBinding;
import MAD.fa_sonia_c0872364_android.model.MainViewModel;
import MAD.fa_sonia_c0872364_android.model.Product;

public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter<ProductListRecyclerViewAdapter.ViewHolder> {

    final private Context context;
    private OnItemClickListener onItemClickListener;
    private List<Product> productList ;

    private MainViewModel viewModel;
    private AlertDialog dialog;


    public ProductListRecyclerViewAdapter(List<Product> productList, Context context, OnItemClickListener onItemClickListener, MainViewModel viewModel){
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.productList = productList;
        this.viewModel = viewModel;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductRowBinding binding = ProductRowBinding.inflate(
                LayoutInflater.from(context),parent,false
        );

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(productList.get(position));
//        int randomIndex = new Random().nextInt(backgroundImages.length);
//        holder.itemView.setBackgroundResource(backgroundImages[randomIndex]);


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Show the action popup
                int position = holder.getAdapterPosition();
                showPopupMenu(v,position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ProductRowBinding binding;
        public ViewHolder(ProductRowBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Product model){
            int id = model.getId();
            binding.productName.setText(model.getName());
            binding.productDescription.setText(model.getName());
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setDataList(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }

    // pop menu

    private void showPopupMenu(View view, int position) {
        Product product = productList.get(position);
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        popup.inflate(R.menu.product_context_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_edit:
                        // Handle the edit action
                        showEditCategoryDialog(product);
                        return true;
                    case R.id.action_delete:
                        // Handle the delete action
                        showDeleteCategoryDialog(product);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }

    // edit dialog box

    private void showEditCategoryDialog(Product product) {

        // Create the dialog using an AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Inflate the layout for the dialog
        View dialogView = LayoutInflater.from(context).inflate(R.layout.product_edit_dialog, null);

        // Get references to the dialog's views
        EditText productTitleEditText = dialogView.findViewById(R.id.product_name);
        productTitleEditText.setText(product.getName());

        // Set the dialog's view
        builder.setView(dialogView);

        // Get references to the dialog's views
        Button btnNegative = dialogView.findViewById(R.id.cancel_button);
        Button btnPositive = dialogView.findViewById(R.id.add_button);

        // Add a positive button for saving the changes
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the category object with the new title
                String newProduct = productTitleEditText.getText().toString();
                int id = product.getId();
                //viewModel.updateProductName(newCategory,id);
                dialog.dismiss();
                Toast.makeText(context, "Category updated successfully!", Toast.LENGTH_SHORT).show();
                // Notify the adapter that the data has changed
                setDataList(viewModel.getAllProducts());
            }
        });

        // Add a negative button for cancelling the changes
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on click
                dialog.dismiss();
            }
        });

        // Show the dialog
        builder.show();
    }

    // delete dialog box
    private void showDeleteCategoryDialog(Product product) {

        // Create the dialog using an AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Inflate the layout for the dialog
        View dialogView = LayoutInflater.from(context).inflate(R.layout.product_delete_dialog, null);
        // Set the dialog's view
        builder.setView(dialogView);
        dialog = builder.create();

        // Get references to the dialog's views
        Button btnNegative = dialogView.findViewById(R.id.btn_negative);
        Button btnPositive = dialogView.findViewById(R.id.btn_positive);
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on click
                dialog.dismiss();
            }
        });

        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on click
                //viewModel.deleteProduct(product);
                dialog.dismiss();
                Toast.makeText(context, "Category removed successfully!", Toast.LENGTH_SHORT).show();
                // Notify the adapter that the data has changed
                setDataList(viewModel.getAllProducts());
            }
        });

        dialog.show();
    }

    private void setBackgroundOpacity(View view, float opacity) {
        AlphaAnimation alpha = new AlphaAnimation(opacity, opacity);
        alpha.setDuration(0);
        alpha.setFillAfter(true);
        view.startAnimation(alpha);
    }
}
