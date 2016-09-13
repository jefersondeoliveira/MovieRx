package com.example.jeferson.movierx.ui;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.example.jeferson.movierx.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_content, MoviesFragment.newInstance(), null)
                    .commit();
        }

        setupToolbar();

    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);

        final MaterialMenuDrawable mMaterialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        mToolbar.setNavigationIcon(mMaterialMenu);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMaterialMenu.getIconState() == MaterialMenuDrawable.IconState.ARROW) {
                    onBackPressed();
                }
            }
        });

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getSupportFragmentManager().getBackStackEntryCount() > 0) { // show arrow
                    mMaterialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW);
                } else { // show burger
                    mMaterialMenu.animateIconState(MaterialMenuDrawable.IconState.BURGER);
                }
            }
        });
    }

}
