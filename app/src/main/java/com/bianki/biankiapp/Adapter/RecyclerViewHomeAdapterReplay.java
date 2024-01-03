package com.bianki.biankiapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bianki.biankiapp.ASK.ASKPresenter;
import com.bianki.biankiapp.ASK.HomeView;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.puplicQuestion;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.R;
import com.bianki.biankiapp.Update.updatereplay;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterReplay extends RecyclerView.Adapter<RecyclerViewHomeAdapterReplay.RecyclerViewHolder> implements HomeView {

    private List<puplicQuestion.Question.Replaie> categories;
    private Context context;
    private static ClickListener clickListener;
    boolean ismine ;
    List<String> list ;
    int listsize;
    String idQuestion ;
    ASKPresenter askPresenter;
    Helper helper;
    String cursor;
    public RecyclerViewHomeAdapterReplay(List<puplicQuestion.Question.Replaie> categories, String idQuestion, Context context) {
        this.categories = categories;
        this.context = context;
        this.idQuestion = idQuestion;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterReplay.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.desofquestionreplay,
                viewGroup, false);

        list  = new ArrayList<>();
        list.add("Edit");
        list.add("Delete");
        list.add("");
        listsize = list.size() - 1;

        helper = new Helper(context);
        cursor = helper.getiddata();
        askPresenter = new ASKPresenter(this);
        askPresenter.getcurrentprofile(cursor);



        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterReplay.RecyclerViewHolder viewHolder, int i) {


        String content = categories.get(i).getContent();
        String photo = categories.get(i).getCreator().getProfileImage();
        String username = categories.get(i).getCreator().getUserName();
        String type = categories.get(i).getUserType();
        ismine = categories.get(i).getMine();



        if (ismine)
        {
            viewHolder.spinner.setVisibility(View.VISIBLE);
        }else
        {
            viewHolder.spinner.setVisibility(View.GONE);

        }




        if (type.equals("annonymous"))
        {

            viewHolder.content.setText(content);
            viewHolder.owner.setText("annonymous");
            try {
                Picasso.get().load("photo").placeholder(R.drawable.avatar).into(viewHolder.imageView);

            } catch (Exception e) {
            }

        }else
        {

            viewHolder.content.setText(content);
            viewHolder.owner.setText(username);
            try {
                Picasso.get().load(photo).placeholder(R.drawable.avatar).into(viewHolder.imageView);

            } catch (Exception e) {
            }

        }



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, list) {
            @Override
            public int getCount() {
                return(listsize); // Truncate the list
            }
        };

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewHolder.spinner.setAdapter(dataAdapter);
        viewHolder.spinner.setSelection(listsize);


        viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                {
                    viewHolder.spinner.setAdapter(dataAdapter);
                    viewHolder.spinner.setSelection(listsize);

                    Intent intent = new Intent(context , updatereplay.class );
                    intent.putExtra("text" , categories.get(i).getContent());
                    intent.putExtra("id" , categories.get(i).getId());

                    context.startActivity(intent);


                }else if (position == 1)
                {
                    askPresenter.deleteAskReplay(idQuestion , categories.get(i).getId() , cursor);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        viewHolder.spinner.getBackground().setColorFilter(context.getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);





    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void Result(Boolean message) {

    }

    @Override
    public void puplicQuestion(List<puplicQuestion.Question> questions) {

    }

    @Override
    public void Resultofreplay(String res) {

    }

    @Override
    public void getcurrentprofile(getcurrentprofile.User users) {

    }

    @Override
    public void deleteQuestion(String deleteQuestion) {

        Toast.makeText(context, deleteQuestion, Toast.LENGTH_SHORT).show();


    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imageView)
        CircularImageView imageView;

        @BindView(R.id.owner)
        TextView owner;

        @BindView(R.id.content)
        TextView content;

        @BindView(R.id.spinner)
        Spinner spinner;


        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

           // clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewHomeAdapterReplay.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
