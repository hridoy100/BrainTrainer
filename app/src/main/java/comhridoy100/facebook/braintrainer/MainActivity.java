package comhridoy100.facebook.braintrainer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn[] = new Button[5];
    TextView timerText;
    TextView score;
    TextView dataText;
    TextView finalSore;
    TextView answerShow;
    RelativeLayout Rl ;
    Random rand;
    Button playAGain;

    int x,y;
    int correct=0,wrong=0;
    CountDownTimer timer,answerTimer;


    public void StartGame(View view){

        btn[4].setVisibility(View.INVISIBLE);


        timerText.setText("30s");
        timerText.setVisibility(View.VISIBLE);
        dataText.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        for(int i=0; i<4; i++){
            btn[i].setVisibility(View.VISIBLE);
        }



        timer.start();


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Rl = (RelativeLayout) findViewById(R.id.relative_Layout);

        finalSore = (TextView) findViewById(R.id.finalScore);
        finalSore.setEnabled(false);
        finalSore.setVisibility(View.INVISIBLE);


        btn[0] = (Button) findViewById(R.id.button);
        btn[1] = (Button) findViewById(R.id.button1);
        btn[2] = (Button) findViewById(R.id.button2);
        btn[3] = (Button) findViewById(R.id.button3);
        btn[4] = (Button) findViewById(R.id.go);
        playAGain = (Button) findViewById(R.id.playAgain);
        timerText = (TextView) findViewById(R.id.timerText);
        score = (TextView) findViewById(R.id.score);
        dataText = (TextView) findViewById(R.id.dataText);
        score.setText(Integer.toString(correct)+" \\ "+ Integer.toString(wrong));
        answerShow = (TextView) findViewById(R.id.wrngCrct);
        answerShow.setVisibility(View.INVISIBLE);

        answerShow.setBackgroundColor(Color.rgb(250,250,250));


        timer = new CountDownTimer(30000+100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisUntilFinished/=1000;
                timerText.setText(Long.toString(millisUntilFinished)+" s");
            }

            @Override
            public void onFinish() {
                finalSore.setText(score.getText());
                finalSore.setEnabled(true);
                finalSore.setVisibility(View.VISIBLE);
                for(int i=0; i<4; i++){
                    btn[i].setEnabled(false);
                    btn[i].setVisibility(View.INVISIBLE);
                }
                dataText.setVisibility(View.INVISIBLE);
                timerText.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);
                answerShow.setVisibility(View.INVISIBLE);
                playAGain.setVisibility(View.VISIBLE);
                playAGain.setEnabled(true);
                /*
                try{
                    answerTimer.cancel();
                }catch (Exception e){
                    System.out.println(e);
                } */

            }
        };
        answerTimer = new CountDownTimer(500+100,100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                answerShow.setVisibility(View.INVISIBLE);
            }
        };


        generateRandom();


        playAGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAGain.setEnabled(false);
                playAGain.setVisibility(View.INVISIBLE);
                StartGame(v);
                for(int i=0; i<4; i++){
                    btn[i].setEnabled(true);
                    btn[i].setVisibility(View.VISIBLE);
                }
                finalSore.setVisibility(View.INVISIBLE);
                correct=0;
                wrong=0;
                score.setText(Integer.toString(correct)+" \\ "+ Integer.toString(wrong));
            }
        });


    }

    int ans[] = new int[4];

    public void generateRandom(){
        rand = new Random();
        x = Math.abs(rand.nextInt(100));
        y = Math.abs(rand.nextInt(100));


        while(x==0 || y==0) {
            x = Math.abs(rand.nextInt(100));
            y = Math.abs(rand.nextInt(100));
        }
            System.out.println("x= "+x+ " y= "+y);
        dataText.setText(Integer.toString(x)+" + "+Integer.toString(y));
        int sum = x+y;
        int temp = rand.nextInt(4);
        btn[temp].setText(Integer.toString(sum));
        //ans[temp] = sum;
        int incorrect;

        for(int i=0; i<3; i++){
            incorrect = rand.nextInt(200);
            while(incorrect==sum || incorrect==0){
                incorrect = rand.nextInt(200);
            }
            btn[(++temp)%4].setText(Integer.toString(incorrect));
        }
        /*
        btn[(++temp)%4].setText(Integer.toString());

        btn[(++temp)%4].setText(Integer.toString(y/2+x*2));
        btn[(++temp)%4].setText(Integer.toString(x/2+y*2));
        */
    }


    public void checkAnswer(View view){

        //view.getTag();

        Rl.setBackgroundColor(Color.rgb(rand.nextInt()%250,rand.nextInt()%250,rand.nextInt()%250));



        answerShow.setVisibility(View.VISIBLE);
        answerTimer.start();
        for(int i=0; i<4; i++){
            if(btn[i].isPressed()){
                int sum = Integer.parseInt(btn[i].getText().toString());
                if(sum==(x+y))
                {
                    correct++;
                    answerShow.setText("Correct!");
                    answerShow.setTextColor(Color.rgb(39,180,39));

                }else{
                    answerShow.setText("Wrong!!!");
                    answerShow.setTextColor(Color.RED);
                    wrong++;
                }

            }
        }


        /*

        if(view.getTag()==btn[(x+y)%4].getTag()){
            correct++;
            System.out.println("Tag: "+view.getTag()+" and "+btn[(x+y)%4].getTag());
        }
        else{
            wrong++;
        }
        */
        score.setText(Integer.toString(correct)+" \\ "+ Integer.toString(wrong) );
        generateRandom();
        /*for(int i=0; i<4; i++)
            ans[i] =  Integer.parseInt(btn[i].getText().toString());
        */
    }
}
