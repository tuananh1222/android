package utt.cntt.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Food_Adaper extends RecyclerView.Adapter<Food_Adaper.ViewHolder> implements Filterable {
    private List<Food> listFood;
    private List<Food> listFoodAll;
    private Context context;
    public Food_Adaper(List listFood, Context context) {
        this.listFood = listFood;
        this.context = context;
        listFoodAll = new ArrayList<>();
        listFoodAll.addAll(listFood);
    }
    @Override
    public int getItemCount() {
        return listFood.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context ct = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ct);
        View foodView = inflater.inflate(R.layout.activitty_home, parent, false);

        ViewHolder viewHolder = new ViewHolder(foodView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = listFood.get(position);
        holder.foodname.setText(food.getName());
        holder.imgbtnfood.setImageResource(food.getImg());
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }
    private Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Food> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(listFoodAll);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Food food : listFoodAll) {
                    if (food.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(food);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listFood.clear();
            listFood.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        public TextView foodname;
        public ImageButton imgbtnfood;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            foodname = itemView.findViewById(R.id.txtvName);
            imgbtnfood = itemView.findViewById(R.id.imgbtnFood);
            //Xử lý khi nút Chi tiết được bấm
            imgbtnfood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            foodname.getText(), Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }
    }
}
