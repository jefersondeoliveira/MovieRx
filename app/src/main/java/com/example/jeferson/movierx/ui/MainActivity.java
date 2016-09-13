package com.example.jeferson.movierx.ui;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.example.jeferson.movierx.R;
import com.example.jeferson.movierx.data.model.MovieGenre;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view) NavigationView mNavigationView;

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

        mNavigationView.setNavigationItemSelectedListener(this);

        final MaterialMenuDrawable mMaterialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        mToolbar.setNavigationIcon(mMaterialMenu);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMaterialMenu.getIconState() == MaterialMenuDrawable.IconState.ARROW) {
                    onBackPressed();
                }else{
                    toggleMenu();
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

    private void toggleMenu() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);

        MovieGenre movieGenre;

        switch(item.getItemId()) {
            case R.id.actionMovie:
                movieGenre = MovieGenre.ACTION;
                break;
            case R.id.adventureMovie:
                movieGenre = MovieGenre.ADVENTURE;
                break;
            case R.id.animationMovie:
                movieGenre = MovieGenre.ANIMATION;
                break;
            case R.id.comedyMovie:
                movieGenre = MovieGenre.COMEDY;
                break;
            case R.id.horrorMovie:
                movieGenre = MovieGenre.HORROR;
                break;
            case R.id.scifiMovie:
                movieGenre = MovieGenre.SCIFI;
                break;
            default:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, MoviesFragment.newInstance(), null)
                        .commit();
                return true;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, MoviesFragment.newInstance(movieGenre), null)
                .commit();

        return true;
    }

}
