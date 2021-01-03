package com.example.finalproject.activities;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.finalproject.R;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private final String items[] = {"Everyone", "Teen", "Mature 17+"};
    private final String types[] = {"Free", "Paid"};
    String result, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainMenuActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_bar);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview =navigationView.getHeaderView(0);
        SharedPreferences sharedPreferences = getSharedPreferences("PREFRENCE", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String gmail = sharedPreferences.getString("gmail", "");
        TextView name = headerview.findViewById(R.id.username);
        TextView Gmail = headerview.findViewById(R.id.gmail);
        name.setText(username);
        Gmail.setText(gmail);

        SliderLayout sliderLayout = findViewById(R.id.imageSlider);
        for (int i = 0; i < 8; i++) {

            SliderView sliderView = new SliderView(this);
            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.piceight);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.picfive);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.picfour);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.picone);
                    break;
                case 4:
                    sliderView.setImageDrawable(R.drawable.picsix);
                    break;
                case 5:
                    sliderView.setImageDrawable(R.drawable.pictree);
                    break;
                case 6:
                    sliderView.setImageDrawable(R.drawable.pictwo);
                    break;
                case 7:
                    sliderView.setImageDrawable(R.drawable.picten);
                    break;

            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(MainMenuActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });
            sliderLayout.addSliderView(sliderView);

        }


        //////////////////////////////////////////////////////////////////////////
        ViewPager viewPager = findViewById(R.id.pager);
        Myadapter myadapter = new Myadapter(getSupportFragmentManager());
        myadapter.addFragment(new MyFragment1(), "ONE", "image1");
        myadapter.addFragment(new MyFragment2(), "TWO", "image2");
        myadapter.addFragment(new MyFragment3(), "THREE", "image3");
        myadapter.addFragment(new MyFragment4(), "FOUR", "image4");
        myadapter.addFragment(new MyFragment5(), "FIVE", "image5");
        myadapter.addFragment(new MyFragment6(), "SIX", "image6");
        myadapter.addFragment(new MyFragment7(), "SEVEN", "image7");
        myadapter.addFragment(new MyFragment8(), "EIGHT", "image8");
        myadapter.addFragment(new MyFragment9(), "NINE", "image9");
        viewPager.setAdapter(myadapter);
    }

    ///////////////////////////////////////NavigationBar & Option menu////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_option:
                startActivity(new Intent(MainMenuActivity.this, SearchActivity.class));
                break;
            case R.id.content_rating:
                new AlertDialog.Builder(this)
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result = items[which];
                            }
                        })
                        .setPositiveButton("show Apps", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (result) {
                                    case "Everyone":
                                        Intent intent1 = new Intent(MainMenuActivity.this, ContentRatingActivity.class);
                                        intent1.putExtra("content_rating", "Everyone");
                                        startActivity(intent1);
                                        break;
                                    case "Teen":
                                        Intent intent2 = new Intent(MainMenuActivity.this, ContentRatingActivity.class);
                                        intent2.putExtra("content_rating", "Teen");
                                        startActivity(intent2);
                                        break;
                                    case "Mature 17+":
                                        Intent intent3 = new Intent(MainMenuActivity.this, ContentRatingActivity.class);
                                        intent3.putExtra("content_rating", "Mature 17+");
                                        startActivity(intent3);
                                        break;
                                }
                            }
                        })
                        .create()
                        .show();
                break;
            case R.id.type:
                new AlertDialog.Builder(this)
                        .setSingleChoiceItems(types, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                type = types[which];
                            }
                        })
                        .setPositiveButton("show Apps", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Intent intent = new Intent(MainMenuActivity.this, AppTypeActivity.class);
                                intent.putExtra("types", type);
                                startActivity(intent);
                            }
                        })
                        .create()
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account3:

                Intent intent1 = new Intent(MainMenuActivity.this, AccountInfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.favorites:

                Intent intent2 = new Intent(MainMenuActivity.this, FavoritesActivity.class);
                startActivity(intent2);
                break;
        }
        //  drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();

        }

    }
/////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////
    public static class Myadapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentListList = new ArrayList<>();
        private final List<String> titleList = new ArrayList<>();
        private final List<String> imageList = new ArrayList<>();

        public Myadapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title, String image) {
            fragmentListList.add(fragment);
            titleList.add(title);
            imageList.add(image);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentListList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentListList.size();
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static class MyFragment1 extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view1 = inflater.inflate(R.layout.viewpager, container, false);
            ImageView imageView1 = view1.findViewById(R.id.image1);
            TextView textView1 = view1.findViewById(R.id.text1);
            ImageView imageView2 = view1.findViewById(R.id.image2);
            TextView textView2 = view1.findViewById(R.id.text2);
            ImageView imageView3 = view1.findViewById(R.id.image3);
            TextView textView3 = view1.findViewById(R.id.text3);
            ImageView imageView4 = view1.findViewById(R.id.image4);
            TextView textView4 = view1.findViewById(R.id.text4);
            imageView1.setImageResource(R.drawable.art);
            textView1.setText("Art & Design");
            imageView1.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "ART_AND_DESIGN");
                startActivity(intent);
            });
            imageView2.setImageResource(R.drawable.car);
            textView2.setText("Auto & Vehicles");
            imageView2.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "AUTO_AND_VEHICLES");
                startActivity(intent);
            });
            imageView3.setImageResource(R.drawable.makeup);
            textView3.setText("Beauty");
            imageView3.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "BEAUTY");
                startActivity(intent);
            });
            imageView4.setImageResource(R.drawable.book);
            textView4.setText("Books & Reference");
            imageView4.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "BOOKS_AND_REFERENCE");
                startActivity(intent);
            });
            return view1;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static class MyFragment2 extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view2 = inflater.inflate(R.layout.viewpager2, container, false);
            ImageView imageView1 = view2.findViewById(R.id.image1);
            TextView textView1 = view2.findViewById(R.id.text1);
            ImageView imageView2 = view2.findViewById(R.id.image2);
            TextView textView2 = view2.findViewById(R.id.text2);
            ImageView imageView3 = view2.findViewById(R.id.image3);
            TextView textView3 = view2.findViewById(R.id.text3);
            ImageView imageView4 = view2.findViewById(R.id.image4);
            TextView textView4 = view2.findViewById(R.id.text4);
            imageView1.setImageResource(R.drawable.education);
            textView1.setText("Education");
            imageView1.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "EDUCATION");
                startActivity(intent);
            });
            imageView2.setImageResource(R.drawable.entertainment);
            textView2.setText("Entertainment");
            imageView2.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "ENTERTAINMENT");
                startActivity(intent);
            });
            imageView3.setImageResource(R.drawable.events);
            textView3.setText("Events");
            imageView3.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "EVENTS");
                startActivity(intent);
            });
            imageView4.setImageResource(R.drawable.finance);
            textView4.setText("Finance");
            imageView4.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "FINANCE");
                startActivity(intent);
            });
            return view2;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public static class MyFragment3 extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view3 = inflater.inflate(R.layout.viewpager3, container, false);
            ImageView imageView1 = view3.findViewById(R.id.image1);
            TextView textView1 = view3.findViewById(R.id.text1);
            ImageView imageView2 = view3.findViewById(R.id.image2);
            TextView textView2 = view3.findViewById(R.id.text2);
            ImageView imageView3 = view3.findViewById(R.id.image3);
            TextView textView3 = view3.findViewById(R.id.text3);
            ImageView imageView4 = view3.findViewById(R.id.image4);
            TextView textView4 = view3.findViewById(R.id.text4);
            imageView1.setImageResource(R.drawable.lifestyle);
            textView1.setText("Lifestyle");
            imageView1.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "LIFESTYLE");
                startActivity(intent);
            });
            imageView2.setImageResource(R.drawable.game);
            textView2.setText("Game");
            imageView2.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "GAME");
                startActivity(intent);
            });
            imageView3.setImageResource(R.drawable.family);
            textView3.setText("Family");
            imageView3.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "FAMILY");
                startActivity(intent);
            });
            imageView4.setImageResource(R.drawable.medical);
            textView4.setText("Medical");
            imageView4.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "MEDICAL");
                startActivity(intent);
            });
            return view3;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static class MyFragment4 extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view4 = inflater.inflate(R.layout.viewpager4, container, false);
            ImageView imageView1 = view4.findViewById(R.id.image1);
            TextView textView1 = view4.findViewById(R.id.text1);
            ImageView imageView2 = view4.findViewById(R.id.image2);
            TextView textView2 = view4.findViewById(R.id.text2);
            ImageView imageView3 = view4.findViewById(R.id.image3);
            TextView textView3 = view4.findViewById(R.id.text3);
            ImageView imageView4 = view4.findViewById(R.id.image4);
            TextView textView4 = view4.findViewById(R.id.text4);
            imageView1.setImageResource(R.drawable.travel);
            textView1.setText("Travel & Local");
            imageView1.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "TRAVEL_AND_LOCAL");
                startActivity(intent);
            });
            imageView2.setImageResource(R.drawable.toolbox);
            textView2.setText("Tools");
            imageView2.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "TOOLS");
                startActivity(intent);
            });
            imageView3.setImageResource(R.drawable.person);
            textView3.setText("Personalization");
            imageView3.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "PERSONALIZATION");
                startActivity(intent);
            });
            imageView4.setImageResource(R.drawable.efficiency);
            textView4.setText("Productivity");
            imageView4.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "PRODUCTIVITY");
                startActivity(intent);
            });
            return view4;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static class MyFragment5 extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view5 = inflater.inflate(R.layout.viewpager5, container, false);
            ImageView imageView1 = view5.findViewById(R.id.image1);
            TextView textView1 = view5.findViewById(R.id.text1);
            ImageView imageView2 = view5.findViewById(R.id.image2);
            TextView textView2 = view5.findViewById(R.id.text2);
            ImageView imageView3 = view5.findViewById(R.id.image3);
            TextView textView3 = view5.findViewById(R.id.text3);
            ImageView imageView4 = view5.findViewById(R.id.image4);
            TextView textView4 = view5.findViewById(R.id.text4);
            imageView1.setImageResource(R.drawable.commiunication);
            textView1.setText("Communication");
            imageView1.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "COMMUNICATION");
                startActivity(intent);
            });
            imageView2.setImageResource(R.drawable.dating);
            textView2.setText("Dating");
            imageView2.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "DATING");
                startActivity(intent);
            });
            imageView3.setImageResource(R.drawable.house);
            textView3.setText("House & Home");
            imageView3.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "HOUSE_AND_HOME");
                startActivity(intent);
            });
            imageView4.setImageResource(R.drawable.library);
            textView4.setText("Libraries & Demo");
            imageView4.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "LIBRARIES_AND_DEMO");
                startActivity(intent);
            });
            return view5;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static class MyFragment6 extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view6 = inflater.inflate(R.layout.viewpager6, container, false);
            ImageView imageView1 = view6.findViewById(R.id.image1);
            TextView textView1 = view6.findViewById(R.id.text1);
            ImageView imageView2 = view6.findViewById(R.id.image2);
            TextView textView2 = view6.findViewById(R.id.text2);
            ImageView imageView3 = view6.findViewById(R.id.image3);
            TextView textView3 = view6.findViewById(R.id.text3);
            ImageView imageView4 = view6.findViewById(R.id.image4);
            TextView textView4 = view6.findViewById(R.id.text4);
            imageView1.setImageResource(R.drawable.business);
            textView1.setText("Business");
            imageView1.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "BUSINESS");
                startActivity(intent);
            });
            imageView2.setImageResource(R.drawable.comics);
            textView2.setText("Comics");
            imageView2.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "COMICS");
                startActivity(intent);
            });
            imageView3.setImageResource(R.drawable.food);
            textView3.setText("Food & Drink");
            imageView3.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "FOOD_AND_DRINK");
                startActivity(intent);
            });
            imageView4.setImageResource(R.drawable.fitness);
            textView4.setText("Health & Fitness");
            imageView4.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "HEALTH_AND_FITNESS");
                startActivity(intent);
            });
            return view6;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    public static class MyFragment7 extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view7 = inflater.inflate(R.layout.viewpager7, container, false);
            ImageView imageView1 = view7.findViewById(R.id.image1);
            TextView textView1 = view7.findViewById(R.id.text1);
            ImageView imageView2 = view7.findViewById(R.id.image2);
            TextView textView2 = view7.findViewById(R.id.text2);
            ImageView imageView3 = view7.findViewById(R.id.image3);
            TextView textView3 = view7.findViewById(R.id.text3);
            ImageView imageView4 = view7.findViewById(R.id.image4);
            TextView textView4 = view7.findViewById(R.id.text4);
            imageView1.setImageResource(R.drawable.social);
            textView1.setText("Social");
            imageView1.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "SOCIAL");
                startActivity(intent);
            });
            imageView2.setImageResource(R.drawable.shopping);
            textView2.setText("Shopping");
            imageView2.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "SHOPPING");
                startActivity(intent);
            });
            imageView3.setImageResource(R.drawable.videoplayer);
            textView3.setText("Video players");
            imageView3.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "VIDEO_PLAYERS");
                startActivity(intent);
            });
            imageView4.setImageResource(R.drawable.magazine);
            textView4.setText("News & Magazines");
            imageView4.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "NEWS_AND_MAGAZINES");
                startActivity(intent);
            });
            return view7;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    public static class MyFragment8 extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view8 = inflater.inflate(R.layout.viewpager8, container, false);
            ImageView imageView1 = view8.findViewById(R.id.image1);
            TextView textView1 = view8.findViewById(R.id.text1);
            ImageView imageView2 = view8.findViewById(R.id.image2);
            TextView textView2 = view8.findViewById(R.id.text2);
            ImageView imageView3 = view8.findViewById(R.id.image3);
            TextView textView3 = view8.findViewById(R.id.text3);
            ImageView imageView4 = view8.findViewById(R.id.image4);
            TextView textView4 = view8.findViewById(R.id.text4);
            imageView1.setImageResource(R.drawable.parenting);
            textView1.setText("Parenting");
            imageView1.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "PARENTING");
                startActivity(intent);
            });
            imageView2.setImageResource(R.drawable.cloudy);
            textView2.setText("Weather");
            imageView2.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "WEATHER");
                startActivity(intent);
            });
            imageView3.setImageResource(R.drawable.camera);
            textView3.setText("Photography");
            imageView3.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "PHOTOGRAPHY");
                startActivity(intent);
            });
            imageView4.setImageResource(R.drawable.bicycle);
            textView4.setText("Sports");
            imageView4.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "SPORTS");
                startActivity(intent);
            });
            return view8;

        }
    }

    ////////////////////////////////////////////////////////////////////////
    public static class MyFragment9 extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view9 = inflater.inflate(R.layout.viewpager9, container, false);
            ImageView imageView1 = view9.findViewById(R.id.image1);
            TextView textView1 = view9.findViewById(R.id.text1);
            imageView1.setImageResource(R.drawable.navigation);
            textView1.setText("Maps & Navigation");
            imageView1.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CategoryOrAllActivity.class);
                intent.putExtra("cat", "MAPS_AND_NAVIGATION");
                startActivity(intent);
            });
            return view9;
        }
    }

}