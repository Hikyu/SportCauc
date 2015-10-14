package sport.kyu.cauc.sportcauc.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import sport.kyu.cauc.sportcauc.R;
import sport.kyu.cauc.sportcauc.fragment.SportItemFragment;


public class SportItemActivity extends ToolBarActivity {

    private Fragment mContentFragment;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_sportitem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        init();
        initEvent();

    }

//    @Override
//    public void setToolBarLayoutResource() {
//        mToolbar.inflateMenu(R.menu.menu_staggered_task);
//    }


    @Override
    protected String setToolBarTitle() {
        return new String("体育项目");
    }

    private void init() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        mContentFragment = new SportItemFragment();
        transaction.replace(R.id.sportitem_content_fragment, mContentFragment);
        transaction.commit();

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_18dp);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void findViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    private void initEvent() {


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
