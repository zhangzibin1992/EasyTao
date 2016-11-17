package zhuoxinzhiqu.easytao;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.fragement.MeFragment;
import com.android.fragement.ShopFragment;
import com.android.fragement.UnLoginFragment;
import com.android.tools.ActivityUtils;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @BindViews({R.id.tv_shop,R.id.tv_message,R.id.tv_mail_list,R.id.tv_me})
    TextView[] textViews;

    @BindView(R.id.main_toobar)
    Toolbar toolbar;
    @BindView(R.id.main_title)
    TextView tv_title;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    //点击2次返回，退出程序
    private boolean isExit = false;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_title = (TextView) findViewById(R.id.main_title);//用butterknife拿则无法设置字体
        AssetManager mgr=getAssets();//得到AssetManager
        Typeface tf=Typeface.createFromAsset(mgr, "fonts/yegenyou.ttf");//根据路径得到Typeface
        tv_title.setTypeface(tf);//设置字体 叶根友毛笔字体

        unbinder = ButterKnife.bind(this);

        ButterKnife.bind(MainActivity.this);

        activityUtils = new ActivityUtils(this);
        //设置一下actionbar
        setSupportActionBar(toolbar);
        //设置一下ActionBae标题为空，否则默认显示应用名
        getSupportActionBar().setTitle("");


        init();

    }

    private void init() {
        textViews[0].setSelected(true);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }

            @Override
            public void onPageSelected(int position) {
                for (TextView textview: textViews) {
                    textview.setSelected(false);
                }
                tv_title.setText(textViews[position].getText());
                textViews[position].setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new ShopFragment();
                case 1:
                    return new UnLoginFragment();
                case 2:
                    return new UnLoginFragment();
                case 3:
                    return new MeFragment();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    @OnClick({R.id.tv_shop,R.id.tv_message,R.id.tv_mail_list,R.id.tv_me})
    public void onClick(TextView view){
        for (int i = 0; i <textViews.length ; i++) {
            textViews[i].setSelected(false);
            textViews[i].setTag(i);
        }
        view.setSelected(true);
        viewPager.setCurrentItem((Integer) view.getTag(),false);
        tv_title.setText(textViews[(Integer) view.getTag()].getText());
    }

    @Override
    public void onBackPressed() {
        if (!isExit){
            isExit=true;
            activityUtils.showToast("再按一次退出");

            viewPager.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            },2000);
        }else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
