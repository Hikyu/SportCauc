package sport.kyu.cauc.sportcauc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.dd.CircularProgressButton;
import com.truizlop.fabreveallayout.FABRevealLayout;
import com.truizlop.fabreveallayout.OnRevealChangeListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import me.relex.circleindicator.CircleIndicator;
import sport.kyu.cauc.sportcauc.R;
import sport.kyu.cauc.sportcauc.adapter.DemoPagerAdapter;
import sport.kyu.cauc.sportcauc.constant.Constant;

public class MainActivity extends ToolBarActivity {

    FABRevealLayout fabRevealLayout;
    private ViewPager mRollPicViewpager;
    private int currentItem = 0;// 当前图片的索引号
    private ScheduledExecutorService mScheduledExecutorService;
    private DrawerLayout mDrawerLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        init();

    }

    @Override
    protected void onStop() {
        // 当Activity不可见的时候停止切换  
        mScheduledExecutorService.shutdown();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {

        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒钟切换一次图片显示  
        mScheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, Constant.ROLL_PIC_INTERVAL, TimeUnit.SECONDS);
        super.onStart();

    }

    /**
     * 执行切换图片任务
     */
    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (mRollPicViewpager) {

                currentItem = (currentItem + 1) % Constant.ROLL_PIC_COUNT;
                handler.obtainMessage().sendToTarget();// 通过Handler切换图片
            }
        }

    }
    private void findViews() {
        fabRevealLayout = (FABRevealLayout) findViewById(R.id.fab_reveal_layout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main_drawer);
        mRollPicViewpager = (ViewPager) findViewById(R.id.viewpager_default);

    }

    private void init() {
        setupViewPager();
        setupSearch();

        NavigationView navigationView =
                (NavigationView) findViewById(R.id.nv_main_navigation);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }
    private Handler handlerP = new Handler();
    /*初始化搜索界面*/
    private void setupSearch() {
         /*serach界面点击事件*/
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.main_search_layout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareBackTransition(fabRevealLayout);
            }
        });

         /*搜索按钮点击事件*/
        final CircularProgressButton circularButton1 = (CircularProgressButton) findViewById(R.id.circularButton1);
        circularButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                circularButton1.setProgress(0);
                new Thread(new Runnable() {
                    int i = 0;

                    @Override
                    public void run() {
                        while (i < 110) {
                            i += 1;
                            try {
                                Thread.sleep(50); //让当前线程休眠1000毫秒
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            handlerP.post(new Runnable() {
                                @Override
                                public void run() {
                                    circularButton1.setProgress(i);
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        //configureFABReveal(fabRevealLayout);
    }
    private void prepareBackTransition(final FABRevealLayout fabRevealLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fabRevealLayout.revealMainView();
            }
        }, 100);
    }
    /**
     * 可以在此处设置一些进入动画
     *
     * @param fabRevealLayout
     */
    private void configureFABReveal(FABRevealLayout fabRevealLayout) {
        fabRevealLayout.setOnRevealChangeListener(new OnRevealChangeListener() {
            @Override
            public void onMainViewAppeared(FABRevealLayout fabRevealLayout, View mainView) {
            }

            @Override
            public void onSecondaryViewAppeared(final FABRevealLayout fabRevealLayout, View secondaryView) {
                //  prepareBackTransition(fabRevealLayout);
            }
        });
    }
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mRollPicViewpager.setCurrentItem(currentItem);// 切换当前显示的图片  
        }
    };
    /*轮播图片初始化*/
    private void setupViewPager() {

        CircleIndicator defaultIndicator = (CircleIndicator) findViewById(R.id.indicator_default);
        DemoPagerAdapter defaultPagerAdapter = new DemoPagerAdapter(getSupportFragmentManager());
        mRollPicViewpager.setAdapter(defaultPagerAdapter);
        defaultIndicator.setViewPager(mRollPicViewpager);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    public void mainGradebtnOnclick(View v) {
        switch (v.getId()) {
            case R.id.main_small_btn:
                startActivity(new Intent(MainActivity.this, SportItemActivity.class));
                break;
            case R.id.main_middle_btn:
                break;
            case R.id.main_large_btn:
                break;
        }
    }

}
