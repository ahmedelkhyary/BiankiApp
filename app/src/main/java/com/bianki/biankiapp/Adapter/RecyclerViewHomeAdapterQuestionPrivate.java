package com.bianki.biankiapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bianki.biankiapp.ASK.ASKPresenter;
import com.bianki.biankiapp.ASK.HomeView;
import com.bianki.biankiapp.ClassModel.getcurrentprofile;
import com.bianki.biankiapp.ClassModel.puplicQuestion;
import com.bianki.biankiapp.Database.Helper;
import com.bianki.biankiapp.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterQuestionPrivate extends RecyclerView.Adapter<RecyclerViewHomeAdapterQuestionPrivate.RecyclerViewHolder> implements HomeView {

    private List<puplicQuestion.Question> categories;
    private Context context;
    private static ClickListener clickListener;
    RecyclerView recyclerView;
    ASKPresenter askPresenter;
    Helper helper;
    String cursor;
    EditText text;
    ImageView post;
    Dialog bottomSheetDialog;
    SwitchCompat switchbutton;
    Boolean isreplaybefor ;
    boolean ismine;
    List<String> list ;
    int listsize;
    public RecyclerViewHomeAdapterQuestionPrivate(List<puplicQuestion.Question> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterQuestionPrivate.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.desofquestion,
                viewGroup, false);

        helper = new Helper(context);
        cursor = helper.getiddata();
        askPresenter = new ASKPresenter(this);
        askPresenter.getcurrentprofile(cursor);

        list  = new ArrayList<>();
        list.add("Delete");

        list.add("");
        listsize = list.size() - 1;


        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterQuestionPrivate.RecyclerViewHolder viewHolder, int i) {


        String content = categories.get(i).getQuestionContent();
        String photo = categories.get(i).getCreator().getProfileImage();
        String username = categories.get(i).getCreator().getUserName();
        List<puplicQuestion.Question.Replaie> replay = categories.get(i).getReplaies();
         isreplaybefor = categories.get(i).getIsRepliedBefore();
        ismine = categories.get(i).getIsMine();
        String type = categories.get(i).getUserType();



        viewHolder.content.setText(content);
            viewHolder.owner.setText(username);
            try {
                Picasso.get().load(photo).placeholder(R.drawable.avatar).into(viewHolder.imageView);

            } catch (Exception e) {
            }




        if (ismine)
        {
            viewHolder.spinner.setVisibility(View.VISIBLE);
        }else
        {
            viewHolder.spinner.setVisibility(View.GONE);

        }



        if (type.equals("annonymous")) {

            viewHolder.content.setText(content);
            viewHolder.owner.setText("annonymous");
            try {
                Picasso.get().load("photo").placeholder(R.drawable.avatar).into(viewHolder.imageView);

            } catch (Exception e) {
            }

        } else {

            viewHolder.content.setText(content);
            viewHolder.owner.setText(username);
            try {
                Picasso.get().load(photo).placeholder(R.drawable.avatar).into(viewHolder.imageView);

            } catch (Exception e) {
            }

        }

        viewHolder.replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetDialog = new Dialog(context, android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
                View view1 = LayoutInflater.from(context).inflate(R.layout.askreplayprivate, null);

                recyclerView = view1.findViewById(R.id.recyclerView);
                text = view1.findViewById(R.id.writereplay);
                post = view1.findViewById(R.id.addrepaly);
                LinearLayout linearLayout6 = view1.findViewById(R.id.linearLayout6);


                if (categories.get(i).getIsMine()) {
                    linearLayout6.setVisibility(View.GONE);
                }

                if (categories.get(i).getIsRepliedBefore())
                {
                    linearLayout6.setVisibility(View.GONE);
                }





                RecyclerViewHomeAdapterReplay headerAdapter = new RecyclerViewHomeAdapterReplay(replay , categories.get(i).getId(), context);
                recyclerView.setAdapter(headerAdapter);
                GridLayoutManager layoutManager = new GridLayoutManager(context, 1,
                        GridLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setNestedScrollingEnabled(true);
                headerAdapter.notifyDataSetChanged();


                post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        askPresenter.privateReplay(text.getText().toString(), categories.get(i).getId(), cursor);
                    }
                });

                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();


            }
        });




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

                    askPresenter.deleteQuestion(categories.get(i).getId() , cursor);


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
        Toast.makeText(context, res, Toast.LENGTH_SHORT).show();
        if (res.equals("replay Added Successfully"))
        {
            bottomSheetDialog.dismiss();
        }

    }

    @Override
    public void getcurrentprofile(getcurrentprofile.User users) {

    }

    @Override
    public void deleteQuestion(String deleteQuestion) {

    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imageView)
        CircularImageView imageView;

        @BindView(R.id.owner)
        TextView owner;

        @BindView(R.id.content)
        TextView content;

        @BindView(R.id.replay)
        LinearLayout replay;

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
        RecyclerViewHomeAdapterQuestionPrivate.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
