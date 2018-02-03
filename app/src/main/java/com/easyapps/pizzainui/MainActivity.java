package com.easyapps.pizzainui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.glide.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentStuff();
        mDemoSlider = findViewById(R.id.slider);

        ArrayList<Integer> listResource = new ArrayList<>();
        ArrayList<String> listName = new ArrayList<>();

        listResource.add(R.drawable.image_one);
        listResource.add(R.drawable.image_two);
        listResource.add(R.drawable.image_three);

        listName.add("Zinger Burger");
        listName.add("Chrunch Burger");
        listName.add("Burger with Extra Cheese");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.placeholder);

        for (int i = 0; i < listResource.size(); i++) {
            TextSliderView sliderView = new TextSliderView(this);
            sliderView
                    .image(listResource.get(i))
                    .description(listName.get(i))
                    .setRequestOption(requestOptions)
                    .setBackgroundColor(Color.BLACK)
                    .setProgressBarVisible(false)
                    .setOnSliderClickListener(this);

            sliderView.bundle(new Bundle());
            sliderView.getBundle().putString("extra", listName.get(i));
            mDemoSlider.addSlider(sliderView);
        }

        //---------------------------------------------------
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(2000);
        mDemoSlider.addOnPageChangeListener(this);
    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    //----------------------------------
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
//---------------------------------------------------------

    //-----------------------------------------------
    private void start(Class<? extends Activity> token) {
        Intent intent = new Intent(this, token);
        startActivity(intent);
    }

    //-------------------------------
    private void fragmentStuff() {

            FragmentManager fragmentManager = getSupportFragmentManager();
            MainFragment firstFragment = new MainFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.main_fragment_mainActivity, firstFragment)
                    .commit();
    }//fragment stuff ends
}