package com.shery.foodmenuscreen.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.shery.foodmenuscreen.R;
import com.shery.foodmenuscreen.adapter.MenusAdapter;
import com.shery.foodmenuscreen.constants.Constant;
import com.shery.foodmenuscreen.model.MenuCategoryAndItems;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    TabLayout tabLayout;
    ArrayList<MenuCategoryAndItems> list = new ArrayList<>();
    RecyclerView rcv_menus;
    MenusAdapter menusAdapter;

    CardView cardView;
    ImageView background_image;
    private AppBarLayout mAppBarLayout;
    LinearLayoutManager layoutManager;
    LinearLayout lay_toolbar_up, lay_toolbar_down, lay_search;
    ImageView favourite, favourite2, ic_search;

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.6f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;
    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;
    private boolean isFavourite = true;
    private NestedScrollView nestedScrollingView;
    private boolean isScrollStoped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setEnterTransition(new Explode());
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_main);


        initView();
        initListeners();

        setMarginTopAccordingDisplayCutout(this, lay_toolbar_down, convertDpToPixel(20), 0);
        setMarginTopAccordingDisplayCutout(this, lay_toolbar_up, convertDpToPixel(20), 0);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;
        toolbarVisibility(percentage);
    }

    private void initView() {
        tabLayout = findViewById(R.id.tabLayout);
        rcv_menus = findViewById(R.id.rcv_menus);
        cardView = findViewById(R.id.cardView);
        background_image = findViewById(R.id.background_image);
        mAppBarLayout = findViewById(R.id.main_appbar);
        lay_toolbar_down = findViewById(R.id.lay_toolbar_down);
        lay_toolbar_up = findViewById(R.id.lay_toolbar_up);
        favourite = findViewById(R.id.favourite);
        favourite2 = findViewById(R.id.favourite2);
        ic_search = findViewById(R.id.ic_search);
        lay_search = findViewById(R.id.lay_search);
        list = Constant.generateData();


        for (int i = 0; i < list.size(); i++) {
            TabLayout.Tab tab = tabLayout.newTab(); // Create a new Tab names
            tab.setText(list.get(i).getCategoryName()); // set the Text for the first Tab
            tabLayout.addTab(tab);
        }


        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_menus.setLayoutManager(layoutManager);
        menusAdapter = new MenusAdapter(this, list);
        rcv_menus.setHasFixedSize(true);
        rcv_menus.setAdapter(menusAdapter);

    }

    private void initListeners() {

        lay_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ic_search.performClick();
            }
        });

        mAppBarLayout.addOnOffsetChangedListener(this);

        cardView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cardView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int margin = ((cardView.getHeight()) / 2) - 10;
                setMargins(background_image, 0, 0, 0, margin);

            }
        });


        rcv_menus.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    isScrollStoped = true;
                }/* else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    Log.i("newState", "SCROLL_STATE_DRAGGING");
                } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    Log.i("newState", "SCROLL_STATE_SETTLING");
                } else {
                    Log.i("newState", "undefined : " + newState);
                }*/
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isScrollStoped = false;
                int itemPoition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                tabLayout.getTabAt(itemPoition).select();
            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                if (isScrollStoped)
                    rcv_menus.scrollToPosition(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavourite) {
                    favourite.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.heart_empty));
                    favourite2.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.heart_empty_black));
                    isFavourite = false;
                } else {
                    favourite.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.heart_filled));
                    favourite2.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.heart_filled));
                    isFavourite = true;
                }
            }
        });
        favourite2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavourite) {
                    favourite.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.heart_empty));
                    favourite2.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.heart_empty_black));
                    isFavourite = false;
                } else {
                    favourite.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.heart_filled));
                    favourite2.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.heart_filled));
                    isFavourite = true;
                }
            }
        });


        ic_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

    }


    private void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    private void toolbarVisibility(float percentage) {
        if (percentage < 1.0) {
            lay_toolbar_up.setVisibility(View.VISIBLE);
            lay_toolbar_down.setVisibility(View.GONE);
//            startAlphaAnimation(lay_toolbar_up, View.VISIBLE);
//            startAlphaAnimation(lay_toolbar_down, View.GONE);


        } else if (percentage == 1.0) {
            lay_toolbar_up.setVisibility(View.GONE);
            lay_toolbar_down.setVisibility(View.VISIBLE);
//            startAlphaAnimation(lay_toolbar_up, View.GONE);
//            startAlphaAnimation(lay_toolbar_down, View.VISIBLE);

        }


    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if (!mIsTheTitleVisible) {
                startAlphaAnimation(lay_toolbar_up, View.VISIBLE);
                startAlphaAnimation(lay_toolbar_down, View.GONE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(lay_toolbar_down, View.VISIBLE);
                startAlphaAnimation(lay_toolbar_up, View.GONE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(lay_toolbar_up, View.VISIBLE);
                startAlphaAnimation(lay_toolbar_down, View.GONE);
                mIsTheTitleContainerVisible = false;
            }
        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(lay_toolbar_up, View.GONE);
                startAlphaAnimation(lay_toolbar_down, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    private void startAlphaAnimation(final View v, final int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE) ? new AlphaAnimation(0f, 1f) : new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(MainActivity.ALPHA_ANIMATIONS_DURATION);
        alphaAnimation.setFillAfter(true);
        if (v == lay_toolbar_down) {
            if (visibility != View.VISIBLE) {
                tabLayout.animate().translationY(0).setDuration(200).start();
            } else {
                tabLayout.animate().translationY(90).setDuration(MainActivity.ALPHA_ANIMATIONS_DURATION).start();
            }
        }

        v.startAnimation(alphaAnimation);
    }


    private void setMarginTopAccordingDisplayCutout(Context context, View view, int extraTopWithoutCutout, int extraTopWithCutout) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        if (statusBarHeight > convertDpToPixel(24)) {
            final ViewGroup.MarginLayoutParams[] layoutParams = new ViewGroup.MarginLayoutParams[1];
            layoutParams[0] = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams[0].topMargin = statusBarHeight + extraTopWithCutout;
            view.setLayoutParams(layoutParams[0]);
        } else {
            final ViewGroup.MarginLayoutParams[] layoutParams = new ViewGroup.MarginLayoutParams[1];
            layoutParams[0] = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams[0].topMargin = extraTopWithoutCutout;
            view.setLayoutParams(layoutParams[0]);
        }
    }

    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }
}

