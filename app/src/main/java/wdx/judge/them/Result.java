package wdx.judge.them;

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
    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        for (int i = 0; i < 10; i++) answerData[i][2] = 1;
        for (int i = 10; i < 20; i++) answerData[i][2] = 0;

        LinearLayout llMain = findViewById(R.id.llMain);
        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < 10; i++) {
            View view = inflater.inflate(R.layout.result_item, null, false);
            TextView bio = view.findViewById(R.id.bio);
            ImageView avatar = view.findViewById(R.id.avatar);
            ImageView box = view.findViewById(R.id.box);
            boolean right = (answerData[i][1] == answerData[answerData[i][0]][2]);
            if (right) box.setImageResource(R.drawable.right_card_background);
            else box.setImageResource(R.drawable.wrong_card_background);
            bio.setText(setBioText(answerData[i][0], right));
            avatar.setImageResource(getResId(chosenCategory + answerData[i][0], R.drawable.class));
            llMain.addView(view);
        }


        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);
        lParams.gravity = Gravity.CENTER_HORIZONTAL;
        Button btnNew = new Button(this);
        btnNew.setBackgroundResource(R.drawable.restartb);
        llMain.addView(btnNew, lParams);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }


    private String setBioText(int b, boolean right) {
        String[] bioText1 = {
                "Деннис Рейдер «БТК» убил 10 человек в Канзасе. Он преследовал своих жертв перед вторжением в их жилища, связывал их и истязал. " + (right ? "Правильный выбор отправить его за решетку." : "Видимо теперь продолжит и дальше."),
                "Джеффри Дамер «Милуокский каннибал» американский серийный убийцеа, а также некрофил и каннибал, убил и расчленил 17 мужчин и мальчиков. " + (right ? "Теперь его боятся лишь сокамерники." : "Чтоже, теперь он сьест кого-нибудь еще."),
                "Томми Линн Сэлс «Coast to Coast» был признан виновным в нескольких жестоких убийствах. Утверждал, что убил, по меньшей мере, 70 человек. " + (right ? "Удачно что он теперь в тюрьме." : "Это была ужасная ошибка, теперь убийца на воле."),
                "Гарольд Шипман «Доктор Смерть» врач, а также серийный убийцеа, на счету которого, доказано 250 убийств. Он также подделывал документы по завещанию. " + (right ? "Теперь он врятли кого-то \"вылечит\"." : "К сожалению, пациенты еще в опасности."),
                "Рикардо Рамирес «Ночной охотник» серийный убийца, поклоняющийся Сатане и терроризировавший Лос-Анджелес. Рисовал пентаграммы в домах своих жертв. " + (right ? "Удачно что он теперь в тюрьме." : "Это была ужасная ошибка, теперь убийца на воле."),
                "Анатолий Седых «Липецкий Чикатило» серийный убийца с 1998 года по 2004 год на территории Липецкой области совершил 12 убийств девушек в возрасте до 28 лет. " + (right ? "В тюрьме ему самое место." : "Липецку не повезло, он все еще на свободе."),
                "Карла Хомолка «Жена Скарборского насильника» вместе с мужем, Полом Бернардо, убила двух девочек и свою сестру. Они долго насиловали и пытали своих жертв. " + (right ? "Это правильно отправить ее в тюрьму." : "Теперь она женится на другом насильнике."),
                "Дэвид Берковиц «Сын Сэма» Используя револьвер 44 калибра, он убил 6 человек и ранил 7. Сообщил, что убивал не по собственному желанию, а по воле собаки-демона. " + (right ? "Врятли без него собака продолжит убивать." : "Он уже перезарядил револьвер."),
                "Кипленд Филип Кинкл в возрасте 15 лет убил своих родителей, а на следующий день устроил бойню в школе города Спрингфилд, застрелив двоих учеников и ранив 24. " + (right ? "Псих теперь сядет в тюрьму." : "В школе и по сей день опасно."),
                "Честер Тернер серийный убийца. Обвиняется в убийстве десяти женщин, которые он осуществлял в Лос-Анджелесе, а также в смерти будущего ребёнка одной из жертв. " + (right ? "Теперь в Лос-Анджелесе спокойно." : "Убийства в Лос-Анджелесе продолжатся."),
                "Жерар Муру Нобелевский лауреат по физике руководил в Нижнем Новгороде реализацией мегагранта, работал в ННГУ имени Лобачевского в 2010 году. " + (right ? "Ему безумно повезло, что его оправдали." : "Теперь мир лишится его важных открытий в физике."),
                "Шабалатов Виталий Алексеевич с 16 декабря 2016 года заместитель председателя Правительства Воронежской области. Родился в городе Калач Воронежской области. " + (right ? "Это было правильное решение." : "Жаль что его осудили как убийцу."),
                "Юрий Николаевич Бутусов театральный режиссёр. Главный режиссёр и художественныы руководитель Санкт-Петербургского академического театра имени Ленсовета. " + (right ? "Он и дальше будет радовать нас постановками." : "Театр теперь возможно закроют."),
                "Кип Торн Нобелевский лауреат. Именно Торн был научным консультантом и главным вдохновителем создания голливудского блокбастера «Интерстеллар» " + (right ? "Он продолжит двигать науку вперед." : "Увы, теперь его исследования не увидят."),
                "Броимшо Мамадшоев хирург из Таджикистана работает в одной из престижных больниц Москвы. Свободное время он посвящает семье и благотворительности. " + (right ? "Он и дальше будет спасать людей." : "Теперь он не спасет многих людей."),
                "Михаил Сватковский кандидат медицинских наук, хирург, флеболог с обширной практикой. Главврач центра флебологии и восстановительной медицины Гермес. " + (right ? "Он будет продолжать врачебную деятельность." : "Медицинский центр лишился профессионала."),
                "Дмитрий Динзе адвокат. В 2011 году в Гааге награжден в международном конкурсе проектов, связанных с защитой прав граждан, «Инновационное правосудие». " + (right ? "Он и дальше продолжит спасать невиновных." : "Видимо его адвокат был менее профессионален."),
                "Джейсон Мур Американец работает в Казахстане преподавателем. В планах американца посетить все крупные города, чтобы увидеть полную картину жизни в Казахстане. " + (right ? "Он продолжит обогощаться культурой." : "Видимо теперь путешествие он завершил."),
                "Олег Алексеевич Гаркуша советский и российский музыкант, певец и телеведущий, шоумен, участник группы «АукцЫон». Автор текстов и исполнитель, поэт, актёр. " + (right ? "Он продолжит свой творческий путь." : "Теперь он будет петь лишь шансон."),
                "Андрей Павлович Щербань директор ТЭЦ  работает уже более 15 лет. Начинал с начальника смены, был начальником турбинного участка, главным инженером. " + (right ? "Он продолжит свою работу на ТЭЦ." : "Такого падения по карьерной лестнице он не ожидал.")
        };
        String[] bioText2 = {
                "Евгений Осин - алкогольная зависимость. Советский и российский певец, музыкант, автор песен. Известен оригинальным жанром исполнени. " + (right ? "Возможно теперь его вылечат." : "Теперь он вероятно сопьется."),
                "Джиа Каранджи - героиновая наркоманка. Родилась: 29 января 1960 г., одна из первых супермоделей в мире и первая женщина, которая умерла от СПИДа. " + (right ? "Вы правы, но уже слишком поздно." : "Жаль что вы этого не заметили."),
                "Бен Аффлек - алкогольная зависимость. Американский актёр, сценарист, кинорежиссёр и продюсер, уже не раз завязывал с алкоголем, проходил курсы лечения. " + (right ? "Возможно в этот раз ему повезет." : "Но до реабилитационного центра не видимо дошел."),
                "Кэри Муллис - LSD. Биохимик, получил Нобелевскую премию. Признал, что без регулярного употребления LSD он вряд ли добился бы таких успехов в науке. " + (right ? "Врятли LSD вызывает зависимость, но вы правы." : "Возможно теперь у него поедет крыша."),
                "Стефани Роуз Бонджови - героиновая наркоманка 19-летняя дочь рокера Джона Бон Джови была арестована за хранение после передозировки героином. " + (right ? "Вы отправили ее лечиться" : "Теперь ее ждет туманное будующее."),
                "Морган Фриман - регулярно употребляет марихуану. Американский актёр кино и озвучивания, режиссёр. Лауреат премии «Оскар» и двух «Золотых глобусов». " + (right ? "Возможно он теперь станет курить реже." : "Теперь он рискует посадить свой знаменитый голос."),
                "Энтони Хопкинс - алкогольная зависимость. Американский актёр театра и кино, кинорежиссёр, композитор. Признался, что счастлив быть алкоголиком. " + (right ? "Курс лечения пойдет ему на пользу." : "Он счастлив что им и останется."),
                "Водров Александр - героиновый наркоман. Нападал и обворовывал женщин в Заречном, ранее был судим за сбыт наркотиков и мошенничество. " + (right ? "Вероятно из него сделают человека." : "Он будет искать деньги на наркотики и дальше."),
                "Павел Лазерев - героиновый наркоман. Родной брат знаменитого певца Сергея Лазарева уже не раз побывавший в тюрьме. " + (right ? "Возможно теперь его уговорят бросить." : "Жаль, что реабилитационный центр ему не поможет."),
                "Кристоф Росснер - регулярно употребляет марихуану. Пользуется бывшей авиабазой НАТО в попытке стать ведущим производителем лекарственной конопли в Германии. " + (right ? "Теперь он будет меньше курить." : "Он будет скуривать половину от произведенного."),
                "Тимофей Жуков первый вице-президент в фонде «Город без наркотиков». Общественник, регулярно кормит бездомных и малоимущих у железнодорожного вокзала. " + (right ? "Хорошо что вы не решили что он наркоман." : "Он обиделся, что вы сочли его наркоманом."),
                "Юрий Кожемякин инвалид, лишившийся ног в своей же квартире. От переохлаждения конечностей. Живет в аварийном доме в Кирове. " + (right ? "Все верно, хотя в реабилитационном центре хотя бы тепло" : "Что ж, по крайней мере теперь он в тепле."),
                "Ислам Каримов чемпион мира по боям без правил Участник акции «Звезды спорта против наркотиков» в Ростове-на-Дону. " + (right ? "Правильно, он точно не наркоман." : "В реабилитационном центре ему теперь нечего делать."),
                "Анна-Линн Маккорд американская актриса и модель, получившая известность благодаря роли Наоми Кларк в сериале «90210: Новое поколение». " + (right ? "Она и в самом деле не наркоманка." : "На самом деле она не наркоманка."),
                "Андре Таннебергер немецкий музыкант и музыкальный продюсер, диджей, автор песен. Известный под псевдонимом ATB. " + (right ? "Все верно, а песня Ecstasy переводится как экстаз." : "Он очень опечален что вы решили что он наркоман."),
                "Леонардо Ди Каприо американский актёр и продюсер. Признался, что не принимал наркотики, не смотря на свое окружение. " + (right ? "Правильно, он один из немногих в Голливуде." : "Теперь в реабилитационном центре он встретит многих коллег."),
                "Егор Бычков в 18-лет организовал в Нижнем Тагиле филиал фонда «Город без наркотиков». Борцы с наркоманией успели вылечить 40 тагильчан. " + (right ? "Он рад, что вы его признали трезвенником." : "Он отказывается лечить себя самого."),
                "Анна Саранг закончила лондонский Imperial College и стала магистром по наркополитике. Она — социолог и один из учредителей Фонда социальной справедливости. " + (right ? "Все верно, она не наркоманка." : "Вы ошиблись."),
                "Джиджи Хадид американская супермодель и телеведущая. Является лицом нескольких линий Tom Ford. Заявила, что никогда не употребляла наркотики. " + (right ? "Отлично, что вы решили также." : "Вы всеже решили ее вылечить."),
                "Тайлер Грегори Оконма американский рэпер, продюсер, клипмейкер, художник-оформитель и модельер. Заявил, что никогда не употреблял наркотики. " + (right ? "Так решили и вы." : "Зря вы отправили его в центр реабилитации.")
        };
        String[] bioText3 = {
                "Руперт Эверетт - открытый гей. Британский актёр театра и кино. Два раза номинировался на «Золотой глобус». " + (right ? "Теперь он на своем месте." : "Вероятно он отправится к чужой жене."),
                "Риз Уизерспун американская актриса и продюсер. Прорывом для неё стала роль Элль Вудс в комедии «Блондинка в законе», принёсшая ей номинацию на премию «Золотой глобус». " + (right ? "Хорошо что вы отправили ее к семье." : "Она не хочет жить с женой."),
                "Стивен Фрай - открытый гей. Английский актёр, писатель и драматург, известность которому принесли прежде всего роли в британских комедийных телесериалах. " + (right ? "Вы ответили верно." : "Вы решили что девушки ему нравятся больше."),
                "Том Форд - открытый гей. Американский дизайнер и кинорежиссёр. Получил широкую известность во время работы в доме моды Gucci. " + (right ? "Все правильно он отправится к мужу." : "Он не сможет жить с женой."),
                "Роб Хэлфорд - открытый гей.  Британский музыкант, автор песен и продюсер, в первую очередь известный как вокалист британской хеви-метал-группы Judas Priest. " + (right ? "Все верно, и он вовсе не байкер" : "Увы девушки его не привлекают."),
                "Кэри Лоуэлл американская актриса и бывшая фотомодель. Наиболее известна по роли Девушки Джеймса Бонда в фильме 1989 года «Лицензия на убийство». " + (right ? "Она отправится в свою семью." : "Она в недоумении куда вы ее отправили."),
                "Лэнс Басс - открытый гей. американский певец и актёр, получивший известность как один из участников поп-группы «’N Sync».  " + (right ? "Вы угадали, он отправится к мужу." : "Вы не угадали к сожалению."),
                "Джонатаном Гроффом - открытый гей. американский певец и актёр, номинант на премии «Тони» и «Драма Деск». Грофф наиболее известен благодаря своим выступлениям в бродвейских мюзиклах. " + (right ? "Вы ответили верно." : "Врятли они теперь уживутся."),
                "Майкл Холбрук Пенниман - открытый гей.  Британский певец и музыкант американo-ливанского происхождения. " + (right ? "Отправился прямо к своему мужу, верно." : "Жаль, что теперь ему предстоит жить с женой."),
                "Джон Узома Эквуга Амаечи - открытый гей. британский баскетболист, бывший игрок НБА, журналист. Выступал за «Кливленд», «Орландо» и «Юту»." + (right ? "Совершенно верно, он гей." : "К сожалению тут вы ошиблись."),
                "Лиам Хемсворт австралийский актёр, наиболее известный по своим ролям Джоша Тейлора в сериале «Соседи», Гейла Хоторна в фильме «Голодные игры». " + (right ? "Все верно, он отправится к любимой" : "Что ж, он не ожидал отправиться к мужу."),
                "Тим Макгро американский певец в стиле кантри, который 23 раза поднимался на первое место кантри-чартов США в дуэте со своей женой, Фэйт Хилл. " + (right ? "Теперь они продолжат петь дуэтом." : "Похоже, дуэтом они теперь петь не смогут."),
                "Джошуа Аарон Чарльз американский актер театра, кино и телевидения. Он наиболее известен по ролям в телесериалах «Ночь спорта» и «Хорошая жена». " + (right ? "Свою жену он тоже считает хорошей." : "Похоже теперь он снимется в сериале «Хороший муж»."),
                "Райан Филлипп американский актёр. В 2007 он сыграл главную роль в фильме «Измена», сюжет которого основан на настоящей истории сотрудника ФБР Эрика О’Нэйла." + (right ? "Абсолютно правильный ответ." : "Он не хочет идти к какому-то мужу."),
                "Уилмер Эдуардо Вальдеррамма американский актёр, известный по телесериалу «От заката до рассвета». " + (right ? "Верно, его будет ждать жена." : "Зачем же вы его отправили к мужу."),
                "Арманд Дуглас Хаммер американский актёр, наиболее известный ролями близнецов Кэмерона и Тайлера Уинклвоссов в фильме Дэвида Финчера «Социальная сеть». " + (right ? "Так и есть, он отправится к любимой." : "Он против жить с мужчиной."),
                "Николай Докучаев фотограф, портфолио, портретная съемка, портреты мужчин любой сложности. " + (right ? "Верно, он отправится к семье." : "Вы ошиблись, не гей."),
                "Хоакин Феникс американский актёр, продюсер, клипмейкер и музыкант. Младший брат актёра Ривера Феникса. " + (right ? "Отлично, что вы ответили верно." : "Его жена будет скучать."),
                "Мелисса Этеридж - открытая лесбиянка. Американская рок-певица, обладательница премий «Оскар» и «Грэмми», борец за права сексуальных меньшинств. " + (right ? "Все верно, не зря боролась." : "Увы, жить с мужчиной она не хочет."),
                "Рози О`Доннелл - открытая лесбиянка. Американская телеведущая, комедийная актриса, продюсер, певица, блогерша, ЛГБТ-активистка и медийная личность. " + (right ? "Вы угадали, так и есть." : "Она отвергает ваше решение."),
        };
        String[] bioText4 = {
                "Ким Петрас - транссексуал, причем самый юный в мире перенёсший операцию по смене пола. Немецкая певица и автор песен. " + (right ? "Вас не обмануть." : "Эх, вы промахнулись."),
                "Джорджина Байер - транссексуал, актриса, политик, первая в мире открытая транссексуалка-депутат в парламенте. Начинала свою карьеру в эскорте. " + (right ? "Да все верно." : "Не верный ответ."),
                "Кандис Кейн - транссексуал, актриса и перформанс-артист. Известная на сценах ночных клубов Нью-Йорка. " + (right ? "Родилась и правда мальчиком." : "А родилась мальчиком, ошиблись."),
                "Эйприл Эшли - транссексуал, модель и администратор ресторана. Известна, как первый британец, которому была проведена операция по смене пола. " + (right ? "Все верно, это уже не новинка." : "А вы решили иначе."),
                "Джесслин Дженсен - транссексуал. При рождении Остин, культурист, любил проводить время в спортзале. " + (right ? "Верно, сбросил больше 20 кг мышечной массы." : "Распространенная ошибка."),
                "Став Страшко - транссексуал, модель, лицо марки Diesel. Родился мальчиком, но считал себя девочкой. Андрогин. Пол не менял, и пока не собирается. " + (right ? "Родился точно парнем." : "Не верно, родился парнем."),
                "Джош Хартнетт киноактер. Известность принесла роль летчика Дэнни Уолкера в военной драме 2001 года Майкла Бэя и Джерри Брукхаймера «Пёрл Харбор». " + (right ? "Верно, обычный парень." : "Ошиблись, это обычный парень."),
                "Винсент Д’Онофрио американский актёр и продюсер. В фантастической ленте «Люди в чёрном» исполнил роли реднека Эдгара. " + (right ? "Так и есть, родился мальчиком." : "Зря вы записали его в транссексуалы."),
                "Райка Ферраз - транссексуал, парикмахер, мисс транссексуал Бразилии 2013." + (right ? "И в самом деле, был мужчиной." : "Вас обманули, был мужчиной."),
                "Александр Ричард Петтифер английский актёр и модель. Известен своей ролью в фильме «Громобой», а также в фильме «Страшно красив» и «Я — четвёртый»." + (right ? "Парень с рождения." : "Ответ не верный."),
                "Райан Салланс - транссексуал, родился девочкой. Защитник прав ЛГБТ. Оратор. " + (right ? "Правильно, был девочкой." : ""),
                "Ян Харви - транссексуал, комик и актер, который часто упоминает, что он транссексуал в своих выступлениях. " + (right ? "В какойто мере юмор женский." : ""),
                "Чез Боно - транссексуал, американский ЛГБТ-правозащитник, писатель, актёр и музыкант, единственный ребёнок в семье Сонни Боно и певицы Шер. " + (right ? "Так и есть, родился девочкой." : "Обманули вас, это была девочка."),
                "Балиан Бушбаум - транссексуал, ранее выступавший за национальную сборную как прыгун с шестом. " + (right ? "Ранее выступала, теперь выступает." : "Ошибка, родилась девочкой"),
                "Лариса Сладкова челябинская ведущая прогноза погоды, яркая внешность и индивидуальная манера подачи информации стали ее визитной карточкой. " + (right ? "Вы ответили верно." : "Вы не первый человек кто назвал ее транссексуалом."),
                "Лиа Мишель американская актриса, певица и автор песен. Известность получила благодаря телесериалу «Хор», в котором исполнила роль Рейчел Берри. " + (right ? "Девушка с рождения." : "Нет, вовсе не транссексуал."),
                "Кристанна Локен американская актриса и фотомодель. Исполняла  роль киборга Т-Х  в фильме «Терминатор 3: Восстание машин». " + (right ? "Всегда была девушкой." : "Не верно, не была она мужчиной"),
                "Дениз Ричардс американская актриса. Наиболее известна благодаря одной из главных ролей в фильме Пола Верховена «Звёздный десант». " + (right ? "Верно, родилась девочкой." : "Ошиблись, она родилась девочкой."),
                "Анушка Раваншад британская актриса персидского происхождения. Снималась в основном в комедиях и мелодрамах. " + (right ? "Точно, это девушка." : "Вы не правы, это девушка с рождения."),
                "Чалита Яемваннанг тайская чемпионка конкурса красоты, представляла свою страну на Мисс Вселенная 2013 ." + (right ? "Угадали, девушка." : "Нет, она родилась девочкой."),
        };

        switch (chosenCategory) {
            case "img":
                return (bioText1[b]);
            case "imn":
                return (bioText2[b]);
            case "igg":
                return (bioText3[b]);
            case "imt":
                return (bioText4[b]);

        }
        return null;
    }
}