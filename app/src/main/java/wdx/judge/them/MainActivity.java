package wdx.judge.them;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toast;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wdx.judge.them.CardConfig;
import wdx.judge.them.CardItemTouchHelperCallback;
import wdx.judge.them.CardLayoutManager;
import wdx.judge.them.OnSwipeListener;

import static android.view.View.GONE;


public class MainActivity extends AppCompatActivity {
    static int dataSetNum;
    private List<Integer> list = new ArrayList<>();
    final static int answerData[][] = new int[8][5]; //номер картинки+bio, свайп влево(тюрьма), правильный ответ, распространенность ответа
    private int counter;
    private int currentAvatar;
    private boolean leftSwiped;
    ScrollView categoryViev;
    static int chosenCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

     private void initView() {
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new MyAdapter());
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), list);
        cardCallback.setOnSwipedListener(new OnSwipeListener<Integer>() {

            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                    myHolder.specialImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    myHolder.commonImageView.setAlpha(Math.abs(ratio));
                } else {
                    myHolder.specialImageView.setAlpha(0f);
                    myHolder.commonImageView.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Integer o, int direction) {

                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myHolder.specialImageView.setAlpha(0f);
                myHolder.commonImageView.setAlpha(0f);
                leftSwiped=(direction == CardConfig.SWIPED_LEFT);
                gameDataSwiped();
            }

            @Override
            public void onSwipedClear() {
                Intent intent = new Intent(MainActivity.this, Result.class);
                startActivity(intent);
            }
        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
    }



    private void gameDataSwiped() {
            for (int i=0; i < 8; i++){
                if (currentAvatar==getResId("img"+i,R.drawable.class)) {
                    answerData[counter][1] = leftSwiped ? 1 : 0;
                    answerData[counter++][0] = i;
                }
            }
        setProgressBar(counter);
    }


    private void setProgressBar(int v) {

        ProgressBar progress =  findViewById(R.id.progress);
        int b = (100 / dataSetNum) * v;
        progress.setVisibility(View.VISIBLE);
        progress.setProgress(b);
    }


    public void categoryClick(View view) {
        switch (view.getId()) {
            case R.id.category1:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                chosenCategory=1;
                initDataCat1();
                break;
            case R.id.category2:
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                chosenCategory=2;
                initDataCat2();
                break;
            case R.id.category3:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.category4:
                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                break;
        }

        categoryViev=(ScrollView)findViewById (R.id.categoryViev);
        categoryViev.setVisibility(GONE);
        setProgressBar(0);
        initView();
    }

    public static int getResId(String variableName, Class<?> с) {
        Field field;
        int resId = 0;
        try {
            field = с.getField(variableName);
            try {
                resId = field.getInt(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resId;
    }

     private void initDataCat1() {
        list.add(R.drawable.img0);
        list.add(R.drawable.img1);
        list.add(R.drawable.img2);
        list.add(R.drawable.img3);
        list.add(R.drawable.img4);
        list.add(R.drawable.img5);
        list.add(R.drawable.img6);
        Collections.shuffle(list);
        dataSetNum=list.size();
         if ( dataSetNum > 5 )
             list.subList(5, dataSetNum).clear();
        dataSetNum=list.size();
    }

    private void initDataCat2(){
        list.add(R.drawable.img0);
        list.add(R.drawable.img1);
        list.add(R.drawable.img2);
        list.add(R.drawable.img3);
        list.add(R.drawable.img4);
        Collections.shuffle(list);
        dataSetNum=list.size();
        if ( dataSetNum > 5 )
            list.subList(5, dataSetNum).clear();
        dataSetNum=list.size();
    }



    private class MyAdapter extends RecyclerView.Adapter {
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ImageView avatarImageView = ((MyViewHolder) holder).avatarImageView;
            avatarImageView.setImageResource(list.get(position));
            currentAvatar=list.get(position);
        }

        @Override
        public int getItemCount() {return list.size();}


        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView avatarImageView;
            ImageView commonImageView;
            ImageView specialImageView;

            MyViewHolder(View itemView) {
                super(itemView);
                avatarImageView = itemView.findViewById(R.id.iv_avatar);
                commonImageView = itemView.findViewById(R.id.iv_common);
                specialImageView = itemView.findViewById(R.id.iv_special);
            }

        }
    }



}
