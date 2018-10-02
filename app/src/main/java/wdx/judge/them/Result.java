package wdx.judge.them;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Result extends MainActivity {

    private LinearLayout llMain;
    private TextView bio;
    private ImageView avatar,box;
    String yourAnswer;
    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.result);
              for(int i=1;i<5;i++) answerData[i][2]=1;
              for(int i=5;i<8;i++) answerData[i][2]=0;

            llMain = (LinearLayout) findViewById(R.id.llMain);
            LayoutInflater inflater = getLayoutInflater();

           for(int i=1;i<8;i++) {
               View view = inflater.inflate(R.layout.result_item, null, false);
               bio = (TextView) view.findViewById(R.id.bio);
               avatar = (ImageView) view.findViewById(R.id.avatar);
               box = (ImageView) view.findViewById(R.id.box);
               if ((answerData[i][1]==answerData[answerData[i][0]][2])&&(answerData[answerData[i][0]][2]==1)){ yourAnswer= "Как удачно что ты его отправил на нары"; box.setImageResource(R.drawable.right_card_background);}
               if ((answerData[i][1]==answerData[answerData[i][0]][2])&&(answerData[answerData[i][0]][2]==0)){ yourAnswer= "Ему очень повезло что ты его оправдал"; box.setImageResource(R.drawable.right_card_background);}
               if (!(answerData[i][1]==answerData[answerData[i][0]][2])&&(answerData[answerData[i][0]][2]==1)){ yourAnswer= "Это была ужасная ошибка, теперь убийца на воле"; box.setImageResource(R.drawable.wrong_card_background);}
               if (!(answerData[i][1]==answerData[answerData[i][0]][2])&&(answerData[answerData[i][0]][2]==0)){ yourAnswer= "Ну что ж, теперь в тюрьме сидит еще один невиновный"; box.setImageResource(R.drawable.wrong_card_background);}
               bio.setText(setBio(answerData[i][0])+"\n"+yourAnswer+"\n"+setFrequency(answerData[i][0])+"% ответили также");
               avatar.setImageResource(getResId("img_avatar_0"+ answerData[i][0],R.drawable.class));
               llMain.addView(view);
           }

            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);
            lParams.gravity = Gravity.CENTER_HORIZONTAL;
            Button btnNew = new Button(this);
            btnNew.setText("Заново?");
            llMain.addView(btnNew, lParams);
            btnNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    restartGame();
                }
            });
        }



        private String setBio(int b){
             final String bioText[] = {" ","Жуткий убийца по кличке 'большой Тонни'", "Весьма жестокий убийца со злой рожей", "Задумчивый убийца рахит", "Грустный убийца челкарь, убивал в 2007 году",
                    "Добрый, девственно чистый кузен гитлера", "Невероятно законопослушный гражданин Карл", "Грустный невиновный перец с бабочкой"};
             return(bioText[b]);
        }

    private String setFrequency(int b){
        final String frequencyText[] = {" ","75", "86", "67", "57",
                "77", "57", "84"};
        return(frequencyText[b]);
    }

    public void restartGame() {
        Intent intent = new Intent(Result.this, MainActivity.class);
        startActivity(intent);
    }

}

