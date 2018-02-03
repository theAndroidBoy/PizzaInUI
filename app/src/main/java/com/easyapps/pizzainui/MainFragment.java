package com.easyapps.pizzainui;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainFragment extends Fragment {
    private final String TAG = "flow";
    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_fragment, container,
                false);
        final ImageView menuImage = rootView.findViewById(R.id.menu_mainActivity);
        final ImageView dealsImage = rootView.findViewById(R.id.deals_mainActivity);
        menuImage.setImageResource(R.drawable.menu);
        dealsImage.setImageResource(R.drawable.deals);
        
        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: MenuImageCicked");
            }
        });
        return rootView;
    }

}
