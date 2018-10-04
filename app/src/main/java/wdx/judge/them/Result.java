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
    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.result);
              answerData[0][2]=0;
              for(int i=1;i<5;i++) answerData[i][2]=1;
              for(int i=5;i<8;i++) answerData[i][2]=0;

            LinearLayout llMain = findViewById(R.id.llMain);
            LayoutInflater inflater = getLayoutInflater();

           for(int i=0;i<dataSetNum;i++) {
               View view = inflater.inflate(R.layout.result_item, null, false);
               TextView bio = view.findViewById(R.id.bio);
               ImageView avatar = view.findViewById(R.id.avatar);
               ImageView box = view.findViewById(R.id.box);
               boolean right=(answerData[i][1]==answerData[answerData[i][0]][2]);
               boolean special=(answerData[answerData[i][0]][2]==1);
               if (right) box.setImageResource(R.drawable.right_card_background); else  box.setImageResource(R.drawable.wrong_card_background);
               bio.setText(setBioText(answerData[i][0])+"\n"+setAnswerText(special,right)+"\n"+setFrequencyText(answerData[i][0])+"% ответили также");
               avatar.setImageResource(getResId(avatarByCategory()+ answerData[i][0],R.drawable.class));
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



        private String setBioText(int b) {
            String[] bioText1 = {"Грустный невиновный перец с бабочкой", "Жуткий убийца по кличке 'большой Тонни'", "Весьма жестокий убийца со злой рожей", "Задумчивый убийца рахит", "Грустный убийца челкарь, убивал в 2007 году",
                    "Добрый, девственно чистый кузен гитлера", "Невероятно законопослушный гражданин Карл"};
            String[] bioText2 = {" ", "1Жуткий убийца по кличке 'большой Тонни'", "1Весьма жестокий убийца со злой рожей", "1Задумчивый убийца рахит", "1Грустный убийца челкарь, убивал в 2007 году",
                    "1Добрый, девственно чистый кузен гитлера"};
            switch (chosenCategory) {
                case 1:
                    return (bioText1[b]);
                case 2:
                    return (bioText2[b]);
            }
            return(null);
        }

    private String setAnswerText(boolean special,boolean right) {
        switch (chosenCategory) {
            case 1:
                if (special && right) return ("Как удачно что ты его отправил на нары");
                if (!special && right) return ("Ему очень повезло что ты его оправдал");
                if (special && !right) return ("Это была ужасная ошибка, теперь убийца на воле");
                if (!special && !right) return ("Ну что ж, теперь в тюрьме сидит еще один невиновный");
            case 2:
                if (special && right) return ("1Как удачно что ты его отправил на нары");
                if (!special && right) return ("1Ему очень повезло что ты его оправдал");
                if (special && !right) return ("1Это была ужасная ошибка, теперь убийца на воле");
                if (!special && !right) return ("1Ну что ж, теперь в тюрьме сидит еще один невиновный");
        }
        return(null);
    }


    private String avatarByCategory() {
        switch (chosenCategory) {
            case 1:
                return ("img");
            case 2:
                return ("img");
        }
        return(null);
    }

        private String setFrequencyText ( int b){
            final String frequencyText[] = {" ", "75", "86", "67", "57",
                    "77", "57", "84"};
            return (frequencyText[b]);
        }


    public void restartGame() {
        Intent intent = new Intent(Result.this, MainActivity.class);
        startActivity(intent);
    }

}

