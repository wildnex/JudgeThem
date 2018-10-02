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


public class MainActivity extends AppCompatActivity {
    final int dataSetNum=7;
    private List<Integer> list = new ArrayList<>();
    final static int answerData[][] = new int[10][5]; //номер картинки+bio, свайп влево(тюрьма), правильный ответ, распространенность ответа
    private int counter=1;
    private int currentAvatar;
    private boolean leftSwiped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

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
                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
                } else {
                    myHolder.dislikeImageView.setAlpha(0f);
                    myHolder.likeImageView.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Integer o, int direction) {

                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myHolder.dislikeImageView.setAlpha(0f);
                myHolder.likeImageView.setAlpha(0f);
              //  Toast.makeText(MainActivity.this, direction == CardConfig.SWIPED_LEFT ? "swiped left" : "swiped right", Toast.LENGTH_SHORT).show();
            //    if (CardConfig.SWIPED_LEFT==direction) {Toast.makeText(MainActivity.this, "лево", Toast.LENGTH_SHORT).show();}

                leftSwiped=(direction == CardConfig.SWIPED_LEFT);
                gameDataSwiped();
            }

            @Override
            public void onSwipedClear() {
                Intent intent = new Intent(MainActivity.this, Result.class);
                startActivity(intent);
             //  Toast.makeText(MainActivity.this, "data clear", Toast.LENGTH_SHORT).show();
             //  recyclerView.postDelayed(new Runnable() {
             //        @Override
             //       public void run() {
             //           initData();
             //          recyclerView.getAdapter().notifyDataSetChanged();
             //      }
             //   }, 3000L);
            }
        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
    }


    private void setCurrentAvatar(int position) {
        currentAvatar=list.get(position);
    }

    private void gameDataSwiped() {
            for (int i=1; i < 8; i++){
                if (currentAvatar==getResId("img_avatar_0"+i,R.drawable.class)) {
                    answerData[counter][1] = leftSwiped ? 1 : 0;
                    answerData[counter++][0] = i;
                }
            }
        setProgressBar(counter-1);
    }


    private void setProgressBar(int v) {

        ProgressBar progress =  findViewById(R.id.progress);
        int b = (100 / dataSetNum) * v;
        progress.setProgress(b);
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

     private void initData() {
        list.add(R.drawable.img_avatar_01);
        list.add(R.drawable.img_avatar_02);
        list.add(R.drawable.img_avatar_03);
        list.add(R.drawable.img_avatar_04);
        list.add(R.drawable.img_avatar_05);
        list.add(R.drawable.img_avatar_06);
        list.add(R.drawable.img_avatar_07);
        Collections.shuffle(list);
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
            setCurrentAvatar(position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView avatarImageView;
            ImageView likeImageView;
            ImageView dislikeImageView;

            MyViewHolder(View itemView) {
                super(itemView);
                avatarImageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
                likeImageView = (ImageView) itemView.findViewById(R.id.iv_like);
                dislikeImageView = (ImageView) itemView.findViewById(R.id.iv_dislike);
            }

        }
    }



}
