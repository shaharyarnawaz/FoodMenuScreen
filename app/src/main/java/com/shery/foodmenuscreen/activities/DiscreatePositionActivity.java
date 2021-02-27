package com.shery.foodmenuscreen.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.shery.foodmenuscreen.R;
import com.shery.foodmenuscreen.adapter.MenusAdapter;
import com.shery.foodmenuscreen.constants.Constant;
import com.shery.foodmenuscreen.model.MenuCategoryAndItems;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class DiscreatePositionActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    TabLayout tabLayout;
    ArrayList<MenuCategoryAndItems> list = new ArrayList<>();
    MenusAdapter menusAdapter;
    CardView cardView;
    ImageView background_image;
    private AppBarLayout mAppBarLayout;
    LinearLayout lay_toolbar_up, lay_toolbar_down, lay_search;
    ImageView favourite, favourite2, ic_search;

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.6f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;
    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;
    private boolean isFavourite = true;


    DiscreteScrollView discreteScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setEnterTransition(new Explode());
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_discreete);
        initView();
        initListeners();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;
        toolbarVisibility(percentage);
    }

    private void initView() {
        tabLayout = findViewById(R.id.tabLayout);
        discreteScrollView = findViewById(R.id.discreteScrollView);

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

        menusAdapter = new MenusAdapter(this, list/*, new TabClick() {
            @Override
            public void onTabClicked(int position) {
            }
        }*/);
        discreteScrollView.setOffscreenItems(0); //Reserve extra space equal to (childSize * count) on each side of the view


        discreteScrollView.addScrollListener(new DiscreteScrollView.ScrollListener<RecyclerView.ViewHolder>() {
            @Override
            public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable RecyclerView.ViewHolder currentHolder, @Nullable RecyclerView.ViewHolder newCurrent) {

                Log.i("onScroll", "scrollPosition : " + scrollPosition);
                if (scrollPosition == -1)
                    tabLayout.getTabAt(currentPosition).select();
            }
        });


        discreteScrollView.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
                tabLayout.getTabAt(adapterPosition).select();
            }
        });


//        discreteScrollView.scrollToPosition(0);
        discreteScrollView.setSlideOnFling(true);

        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1f)
                .setMinScale(1f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                .build());

        discreteScrollView.setAdapter(menusAdapter);

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


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(final TabLayout.Tab tab) {

                discreteScrollView.smoothScrollToPosition(tab.getPosition());


                Log.i("onTabSelected", "--> : " + tab.getText());
//                final float y = rcv_menus.getChildAt(tab.getPosition()).getY();

//                nestedScrollingView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        nestedScrollingView.fling(0);
//                        nestedScrollingView.smoothScrollTo(0, (int) y);
//                    }
//                });
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
                startActivity(new Intent(DiscreatePositionActivity.this, SearchActivity.class));
            }
        });


//        rcv_menus.setHasFixedSize(true);


//        rcv_menus.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                tabLayout.getTabAt(recyclerView.getChildAdapterPosition(recyclerView.findChildViewUnder(500, 500))).select();
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
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
        alphaAnimation.setDuration(DiscreatePositionActivity.ALPHA_ANIMATIONS_DURATION);
        alphaAnimation.setFillAfter(true);
        if (v == lay_toolbar_down) {
            if (visibility != View.VISIBLE) {
                tabLayout.animate().translationY(0).setDuration(200).start();
            } else {
                tabLayout.animate().translationY(90).setDuration(DiscreatePositionActivity.ALPHA_ANIMATIONS_DURATION).start();
            }
        }

        v.startAnimation(alphaAnimation);
    }
}
