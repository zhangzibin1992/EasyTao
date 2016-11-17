package zhuoxinzhiqu.easytao;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tools.ActivityUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {


    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setAction();
    }


    private void setAction() {
        activityUtils = new ActivityUtils(this);
        Timer timer = new Timer();
        //1.5秒后跳转到主页面并且finish
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                activityUtils.startActivity(MainActivity.class);
                finish();
            }
        },1500);
    }



}
