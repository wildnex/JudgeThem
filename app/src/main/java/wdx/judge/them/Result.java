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
import android.widget.Toast;


public class Result extends MainActivity {
    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
    String temp;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.result);

              for(int i=0;i<10;i++) answerData[i][2]=1;
              for(int i=10;i<20;i++) answerData[i][2]=0;

            LinearLayout llMain = findViewById(R.id.llMain);
            LayoutInflater inflater = getLayoutInflater();

           for(int i=0;i<10;i++) {
               View view = inflater.inflate(R.layout.result_item, null, false);
               TextView bio = view.findViewById(R.id.bio);
               ImageView avatar = view.findViewById(R.id.avatar);
               ImageView box = view.findViewById(R.id.box);
               boolean right=(answerData[i][1]==answerData[answerData[i][0]][2]);
               boolean special=(answerData[answerData[i][0]][2]==1);
               if (right) box.setImageResource(R.drawable.right_card_background); else  box.setImageResource(R.drawable.wrong_card_background);
               bio.setText(setBioText(answerData[i][0])+" "+setAnswerText(special,right));
               avatar.setImageResource(getResId(avatarByCategory() + answerData[i][0],R.drawable.class));
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
            String[] bioText1 = {
                    "Деннис Рейдер «БТК» убил 10 человек в Канзасе. Он преследовал своих жертв перед вторжением в их жилища, связывал их и истязал.",
                    "Джеффри Дамер «Милуокский каннибал» американский серийный убийцеа, а также некрофил и каннибал, убил и расчленил 17 мужчин и мальчиков.",
                    "Томми Линн Сэлс «Coast to Coast» был признан виновным в нескольких жестоких убийствах. Утверждал, что убил, по меньшей мере, 70 человек.",
                    "Гарольд Шипман «Доктор Смерть» врач, а также серийный убийцеа, на счету которого, доказано 250 убийств. Он также подделывал документы по завещанию.",
                    "Рикардо Рамирес «Ночной охотник» серийный убийца, поклоняющийся Сатане и терроризировавший Лос-Анджелес. Рисовал пентаграммы в домах своих жертв.",
                    "Анатолий Седых «Липецкий Чикатило» серийный убийца с 1998 года по 2004 год на территории Липецкой области совершил 12 убийств девушек в возрасте до 28 лет.",
                    "Карла Хомолка «Жена Скарборского насильника» вместе с мужем, Полом Бернардо, убила двух девочек и собственную сестру. Они долго насиловали и пытали своих жертв.",
                    "Дэвид Берковиц «Сын Сэма» Используя револьвер 44 калибра, он убил 6 человек и ранил 7. Сообщил, что убивал не по собственному желанию, а по воле собаки-демона.",
                    "Кипленд Филип Кинкл в возрасте 15 лет убил своих родителей, а на следующий день устроил бойню в школе города Спрингфилд, застрелив двоих учеников и ранив 24.",
                    "Честер Тернер серийный убийца. Обвиняется в убийстве десяти женщин, которые он осуществлял в Лос-Анджелесе, а также в смерти будущего ребёнка одной из жертв.",
                    "Жерар Муру Нобелевский лауреат по физике руководил в Нижнем Новгороде реализацией мегагранта, работал в ННГУ имени Лобачевского в 2010 году.",
                    "Шабалатов Виталий Алексеевич с 16 декабря 2016 года заместитель председателя Правительства Воронежской области. Родился в городе Калач Воронежской области.",
                    "Юрий Николаевич Бутусов театральный режиссёр. Главный режиссёр и художественныы руководитель Санкт-Петербургского академического театра имени Ленсовета.",
                    "Кип Торн Нобелевский лауреат. Именно Торн был научным консультантом и главным вдохновителем создания голливудского блокбастера «Интерстеллар»",
                    "Броимшо Мамадшоев хирург из Таджикистана работает в одной из престижных больниц Москвы. Свободное время он посвящает семье и благотворительности.",
                    "Михаил Сватковский кандидат медицинских наук, хирург, флеболог с обширной практикой. Главврач центра флебологии и восстановительной медицины Гермес",
                    "Дмитрий Динзе адвокат. В 2011 году в Гааге награжден в международном конкурсе проектов, связанных с защитой прав и свобод граждан, «Инновационное правосудие».",
                    "Джейсон Мур Американец работает в Казахстане преподавателем. В планах американца посетить все крупные города, чтобы увидеть полную картину жизни в Казахстане.",
                    "Олег Алексеевич Гаркуша советский и российский музыкант, певец и телеведущий, шоумен, участник группы «АукцЫон». Автор текстов и исполнитель, поэт, актёр.",
                    "Пабло Пинеда первый в Европе человек с синдромом Дауна, получивший высшее образование. Преподаватель при муниципалитете Малаги",
                    "Андрей Павлович Щербань директор ТЭЦ  работает уже более 15 лет. Начинал с начальника смены, был начальником турбинного участка, главным инженером."
            };
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
                if (special && right) return ("Как удачно что ты его отправил на нары.");
                if (!special && right) return ("Ему очень повезло что ты его оправдал.");
                if (special && !right) return ("Это была ужасная ошибка, теперь убийца на воле.");
                if (!special && !right) return ("Ну что ж, теперь в тюрьме сидит еще один невиновный.");
            case 2:
                if (special && right) return ("1Как удачно что ты его отправил на нары.");
                if (!special && right) return ("1Ему очень повезло что ты его оправдал.");
                if (special && !right) return ("1Это была ужасная ошибка, теперь убийца на воле.");
                if (!special && !right) return ("1Ну что ж, теперь в тюрьме сидит еще один невиновный.");
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

