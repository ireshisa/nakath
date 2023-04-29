package com.universlsoftware.nakathpathraya.ui.rahu;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.universlsoftware.nakathpathraya.MainActivity;
import com.universlsoftware.nakathpathraya.R;

import com.universlsoftware.nakathpathraya.bean.Result;
import com.universlsoftware.nakathpathraya.common.JSONParser;
import com.universlsoftware.nakathpathraya.databinding.FragmentHomeBinding;
import com.universlsoftware.nakathpathraya.databinding.FragmentRahuTimeBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class RahuTime extends Fragment {

    Context thiscontext;

    private RahuTimeViewModel mViewModel;

    public static RahuTime newInstance() {
        return new RahuTime();
    }

    private FragmentRahuTimeBinding binding;

    private static final String SELECTED_COLOR = "#90000000";
    private static final String UNSELECTED_COLOR = "#FFE5AC04";
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private TextView sunriseTextView, sunsetTextView, dateTextView, goodDirection, badDirection, rahuTimeRange;
    private Button dayButton, nightButton, todayButton, mondayButton, tuesdayButton, wednesdayButton, thursdayButton, fridayButton, saturdayButton, sundayButton;
    private List<Result> resultList;
    private int selectedDay = 0;
    String URL = "https://universlsoftware.com/appsadmin/appspanel/index.php/astro_rahu/feed";

    //https://universlsoftware.com/universlsoftware/appspanel/index.php/astro_rahu/feed
    @TargetApi(Build.VERSION_CODES.KITKAT)

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = FragmentRahuTimeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        thiscontext = container.getContext();
        sunriseTextView = binding.sunRaiseTime;
        dayButton = binding.day;
        fridayButton =  binding.friday;
        sunsetTextView =  binding.sunFallTime;
        nightButton = binding.night;
        saturdayButton = binding.saturday;
        dateTextView = binding.dateMonth;
        todayButton = binding.today;
        sundayButton = binding.sunday;
        goodDirection = binding.goodDirection;
        mondayButton = binding.monday;
        badDirection = binding.badDirection;
        tuesdayButton = binding.tuesday;
        rahuTimeRange = binding.rahuTimeRange;
        thursdayButton = binding.thursday;
        wednesdayButton = binding.wednesday;
        actionMethods();
        loadData();
        getDate();
        return root;
    }

    private void getDate() {

        Calendar calendar = Calendar.getInstance();
        Locale locale = new Locale("si"); // create Sinhala locale
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMMM dd", locale); // create Sinhala date format
        String sinhalaDate = sdf.format(calendar.getTime());
        dateTextView.setText(sinhalaDate);

    }



    private void actionMethods() {
        todayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadToday();

            }
        });

        mondayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDay = 0;
                setData(selectedDay,true);
            }
        });

        tuesdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDay = 1;
                setData(selectedDay,true);
            }
        });

        wednesdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDay = 2;
                setData(selectedDay,true);
            }
        });

        thursdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDay = 3;
                setData(selectedDay,true);
            }
        });

        fridayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDay = 4;
                setData(selectedDay,true);
            }
        });

        saturdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDay = 5;
                setData(selectedDay,true);
            }
        });

        sundayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDay = 6;
                setData(selectedDay,true);
            }
        });

        nightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(selectedDay,false);
            }
        });

        dayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(selectedDay,true);
            }
        });
    }



    private void loadToday() {
        Log.i("loadToday", "loadToday: ");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        for (int i = 0; i < resultList.size(); i++) {
            Result r = resultList.get(i);
            if (dateStr.equals(r.getDate())) {
                setData(i, true);
                Log.i("loadToday", "set loadToday: ");
            }
        }
    }

    private void setData(int i, final Boolean isDay) {
        if (resultList != null) {
            Result result = resultList.get(i);
            Date date = new Date();
            @SuppressLint("SimpleDateFormat") DateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
            @SuppressLint("SimpleDateFormat") DateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
            String[] day_month_text = resultList.get(i).getDate().split("-");

            // dateTextView.setText(result.getDay() + " , " + day_month_text[2] + " " + monthFormat.format(date) + " " + yearFormat.format(date));
            try {
                sunriseTextView.setText(timeFormat.format(timeFormat.parse(result.getSunRaise())));
                sunsetTextView.setText(timeFormat.format(timeFormat.parse(result.getSunfall())));
                if (isDay) {
                    rahuTimeRange.setText(timeFormat.format(timeFormat.parse(result.getDivaSita())) + " - " + timeFormat.format(timeFormat.parse(result.getDivaDakva())));
                } else {
                    rahuTimeRange.setText(timeFormat.format(timeFormat.parse(result.getRathreeSita())) + " - " + timeFormat.format(timeFormat.parse(result.getRathreeDakva())));
                }
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String[] directions = getResources().getStringArray(R.array.directions);
            goodDirection.setText("හොඳ දිශාව - " + directions[result.getSubaDisava()]);
            badDirection.setText("අසුභ දිශාව - " + directions[result.getMaruDisava()]);

            dayButton.setBackgroundColor(isDay? Color.parseColor(SELECTED_COLOR):Color.parseColor(UNSELECTED_COLOR));
            nightButton.setBackgroundColor(isDay?Color.parseColor(UNSELECTED_COLOR):Color.parseColor(SELECTED_COLOR));

        }

    }


    private void loadData() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        RahuTime.ResultAsynTask task = new RahuTime.ResultAsynTask((Activity) thiscontext);
        task.execute(URL);
        Log.i("loadToday", "result making loadData : ");
    }

    @SuppressLint("StaticFieldLeak")
    public class ResultAsynTask extends AsyncTask<String, Void, List<Result>> {
        private Activity context;
        private ProgressDialog progressDialog = new ProgressDialog(thiscontext);;

        ResultAsynTask(Activity context) {
            this.context = context;
            progressDialog.setMessage(getString(R.string.loading));
        }

        @Override
        protected void onPreExecute() {
            Log.i("loadToday", "onPreExecute");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Result> resultList) {

            Log.i("loadToday", "onPostExecute");
            progressDialog.dismiss();
            if (resultList == null) {
                Toast.makeText(context, "Check internet connection!", Toast.LENGTH_LONG).show();
                Log.i("loadToday", "Check internet");

            } else {
                RahuTime.this.resultList = resultList;
                Log.i("loadToday", String.valueOf(resultList) );
                loadToday();
            }
        }

        @Override
        protected List<Result> doInBackground(String... urls) {
            List<Result> resultList = new Vector<>();
            String response;

            for (String url : urls) {
                try {
                    response = JSONParser.getResponseFromUrl(url);

                    JSONObject jsonRootObject = new JSONObject(response);
                    // Get the instance of JSONArray that contains
                    // JSONObjects
                    JSONArray jsonArray = jsonRootObject.optJSONArray("results");
                    // Iterate the jsonArray and print the info of
                    // JSONObjects
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Result result = new Result();
                        result.setDay(jsonObject.optString("day"));
                        result.setDate(jsonObject.optString("date"));
                        result.setSunRaise(jsonObject.optString("sun_raise_time"));
                        result.setSunfall(jsonObject.optString("sun_fall_time"));
                        result.setDivaSita(jsonObject.optString("day_rahu_start_time"));
                        result.setDivaDakva(jsonObject.optString("day_rahu_end_time"));
                        result.setRathreeSita(jsonObject.optString("night_rahu_start_time"));
                        result.setRathreeDakva(jsonObject.optString("night_rahu_end_time"));
                        result.setSubaDisava(jsonObject.optInt("subha_dishawa"));
                        result.setMaruDisava(jsonObject.optInt("maru_dishawa"));
                        resultList.add(result);
                    }


                    Log.i("ap121i",">>>>>>>>>>>>>>>>>>>>>>>>>> Data Size : <<<<<<<<<<<<<<<<<<<<<<<<<<<<" + resultList.size());
                    System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>> Data Size : <<<<<<<<<<<<<<<<<<<<<<<<<<<<" + resultList.size());
                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

            return resultList;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}