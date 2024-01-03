package com.bianki.biankiapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bianki.biankiapp.ClassModel.viewPager;
import com.bianki.biankiapp.R;

import java.util.List;


public class ViewPagerHeaderAdapter extends PagerAdapter {

    private List<viewPager> meals;
    private Context context;
    private static ClickListener clickListener;

    public ViewPagerHeaderAdapter(List<viewPager> s, Context context) {
        this.meals = s;
        this.context = context;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ViewPagerHeaderAdapter.clickListener = clickListener;
    }

    @Override
    public int getCount() {
        return meals.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.item_view_pager_header,
                container,
                false
        );


        int img = meals.get(position).getImg();
        String name = meals.get(position).getName();


        ImageView mealThumb = view.findViewById(R.id.imagestory);
        TextView x = view.findViewById(R.id.nameofstory);

        mealThumb.setImageResource(img);
        x.setText(name);





      //  view.setOnClickListener(v -> clickListener.onClick(v, position));

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    public interface ClickListener {
        void onClick(View v, int position);
    }
}
