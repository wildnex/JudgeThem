package wdx.judge.them;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

import static android.view.View.GONE;


public class MainActivity extends AppCompatActivity {
    static int dataSetNum;
    private List<Integer> list = new ArrayList<>();
    final static int answerData[][] = new int[20][5]; //номер картинки+bio, свайп влево(special), правильный ответ, распространенность ответа
    private int counter;
    private int currentAvatar;
    private boolean leftSwiped;
    ScrollView categoryView;
    static String chosenCategory;
    double back_pressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
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
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
    }


    private void gameDataSwiped() {
            for (int i=0; i < dataSetNum; i++){
                if (currentAvatar==getResId(chosenCategory + i,R.drawable.class)) {
                    answerData[counter][1] = leftSwiped ? 1 : 0;
                    answerData[counter++][0] = i;
                }
            }
        setProgressBar(counter);
    }


    private void setProgressBar(int v) {

        ProgressBar progress =  findViewById(R.id.progress);
        int b = 10 * v;
        progress.setVisibility(View.VISIBLE);
        progress.setProgress(b);
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        AlertDialog.Builder adb = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        LinearLayout view1 = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.dialog, null);
        adb.setView(view1);
        adb.setNegativeButton("Начнем же!", null);
        TextView txt = view1.findViewById(R.id.textView);
        switch (chosenCategory) {
            case "img":
                txt.setText(R.string.dialog1);
                break;
            case "imn":
                txt.setText(R.string.dialog2);
                break;
            case "igg":
                txt.setText(R.string.dialog3);
                break;
            case "imt":
                txt.setText(R.string.dialog4);
                break;
        }
        return adb.create();
    }

    public void categoryClick(View view) {

        switch (view.getId()) {
            case R.id.category1:
                chosenCategory="img";
                break;
            case R.id.category2:
                chosenCategory="imn";
                break;
            case R.id.category3:
                chosenCategory="igg";
                break;
            case R.id.category4:
                chosenCategory="imt";
                break;
        }

        showDialog(1);
        for(int i=0;i<20;i++)
            list.add(getResId(chosenCategory+i,R.drawable.class));
        Collections.shuffle(list);
        dataSetNum=list.size();
        if ( dataSetNum > 10 )
            list.subList(10, dataSetNum).clear();

        categoryView= findViewById (R.id.categoryViev);
        categoryView.setVisibility(GONE);

        ImageView imagecattext =  findViewById(R.id.imagecattext);
        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        imagecattext.setImageResource(getResId("categorytext_"+chosenCategory,R.drawable.class));
        imagecattext.setVisibility(View.VISIBLE);
        mainLayout.setBackgroundResource(getResId(chosenCategory+"_bg",R.drawable.class));

        setProgressBar(0);
        initView();
    }

    public static int getResId(String variableName, Class<?> c) {
        Field field;
        int resId = 0;
        try {
            field = c.getField(variableName);
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

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Еще раз НАЗАД чтобы выйти", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
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
                commonImageView.setImageResource(getResId( chosenCategory +"_common" ,R.drawable.class));
                specialImageView.setImageResource(getResId( chosenCategory +"_special" ,R.drawable.class));

            }

        }
    }



}
