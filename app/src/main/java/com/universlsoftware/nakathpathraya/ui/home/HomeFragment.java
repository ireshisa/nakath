package com.universlsoftware.nakathpathraya.ui.home;


import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.universlsoftware.nakathpathraya.MainActivity;
import com.universlsoftware.nakathpathraya.MapsActivity;
import com.universlsoftware.nakathpathraya.MySharedPreferences;
import com.universlsoftware.nakathpathraya.R;

import com.universlsoftware.nakathpathraya.databinding.FragmentHomeBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment implements SensorEventListener{

    private FragmentHomeBinding binding;


    private ImageView imageView;
    private float[] mGravity = new float[3];
    private float[] mGeomagnetic = new float[3];
    private float azimuth = 0f;
    private float correctAzimuth = 0f;
    public TextView textViewDate,daye,name;
    public static   CardView poya, aurudunakath, rahukalaya, aywaya, subamurtha, marusitinadishwa, niwadudina, suanpalapala,jothirya;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        imageView = binding.compass;

        poya = binding.poya;
        aurudunakath = binding.aurudunakath;
        rahukalaya = binding.rahukalaya;
        aywaya = binding.aywaya;
        subamurtha = binding.subamurtha;
        marusitinadishwa = binding.marusitinadishwa;
        niwadudina = binding.niwadudina;
        suanpalapala = binding.subamurtha;
        jothirya = binding.jothirya;
        textViewDate= binding.textViewDate;
        daye= binding.day;
        name= binding.name;

        niwadudina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.nav_niwadudina);

            }
        });

        aywaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.nav_gallery);
            }
        });




        jothirya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event here
                startActivity(new Intent(getContext(), MapsActivity.class));
            }
        });


        rahukalaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.nav_rahu);

            }
        });


        marusitinadishwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.nav_marusitina);

            }
        });


        MySharedPreferences sharedPreferences = new MySharedPreferences(getActivity().getBaseContext());
        name.setText("ආයුබෝවන් "+ sharedPreferences.getUser());


//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        getDate();
        return root;


    }

    private void getDate() {

        Calendar calendar = Calendar.getInstance();
        Locale locale = new Locale("si"); // create Sinhala locale
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMMM dd", locale); // create Sinhala date format
        String sinhalaDate = sdf.format(calendar.getTime());


        SimpleDateFormat sd2f = new SimpleDateFormat("EEEE", locale); // set Sinhala date format with day of the week
        String formattedDate = sd2f.format(calendar.getTime()); // format the date as a string
    // output the formatted date
        daye.setText(formattedDate);
        textViewDate.setText(sinhalaDate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onResume() {
        super.onResume();
        MainActivity.mSensorManager.registerListener(this, MainActivity.mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
        MainActivity.mSensorManager.registerListener(this, MainActivity.mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity.mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        final float alpha = 0.97f;
        synchronized (this) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                mGravity[0] = alpha * mGravity[0] + (1 - alpha) * sensorEvent.values[0];
                mGravity[1] = alpha * mGravity[1] + (1 - alpha) * sensorEvent.values[1];
                mGravity[2] = alpha * mGravity[2] + (1 - alpha) * sensorEvent.values[2];
            }

            if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                mGeomagnetic[0] = alpha * mGeomagnetic[0] + (1 - alpha) * sensorEvent.values[0];
                mGeomagnetic[1] = alpha * mGeomagnetic[1] + (1 - alpha) * sensorEvent.values[1];
                mGeomagnetic[2] = alpha * mGeomagnetic[2] + (1 - alpha) * sensorEvent.values[2];
            }
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                azimuth = (float) Math.toDegrees(orientation[0]);
                azimuth = (azimuth + 360) % 360;
                //
                Animation anim = new RotateAnimation(-correctAzimuth, -azimuth, Animation.RELATIVE_TO_SELF, 0.5f
                        , Animation.RELATIVE_TO_SELF, 0.5f);
                correctAzimuth = azimuth;
                anim.setDuration(500);
                anim.setRepeatCount(0);
                anim.setFillAfter(true);
                imageView.startAnimation(anim);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


}