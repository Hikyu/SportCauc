package sport.kyu.cauc.sportcauc.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import sport.kyu.cauc.sportcauc.R;

/**
 * Created by kyu on 2015/7/10 at 11:48.
 */
public abstract class ToolBarActivity extends ActionBarActivity {
    protected Toolbar mToolbar;


    abstract protected int getLayoutResource();

    public void onToolbarClick() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        setUpToolBar();
    }

    public void setToolBarLayoutResource() {
        mToolbar.inflateMenu(R.menu.menu_main);
    }

    protected String setToolBarTitle() {
        return new String("Cauc");
    }

    protected void changeToolBarTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void setUpToolBar() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(setToolBarTitle());

        setSupportActionBar(mToolbar);


        mToolbar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onToolbarClick();
                    }
                }
        );
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                onMenuItemClicked(item.getItemId());
                return true;
            }
        });
        // setSupportActionBar(mToolbar);
        setToolBarLayoutResource();
    }

    protected void onMenuItemClicked(int id) {

    }
}
