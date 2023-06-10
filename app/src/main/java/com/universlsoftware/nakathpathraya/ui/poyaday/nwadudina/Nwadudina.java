package com.universlsoftware.nakathpathraya.ui.poyaday.nwadudina;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.universlsoftware.nakathpathraya.R;
import com.universlsoftware.nakathpathraya.databinding.FragmentNwadudinaBinding;

import org.json.JSONException;

import java.io.IOException;


public class Nwadudina extends Fragment {

    private FragmentNwadudinaBinding binding;


    RelativeLayout all_events;
    PopupWindow popupWindow;
    int national, local, bank, silent, christian, observance, orthodox, season, daylight;
    String theme;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NiwadudinaViewModel niwadudinaViewModel;
        niwadudinaViewModel = new ViewModelProvider(this).get(NiwadudinaViewModel.class);

        binding = FragmentNwadudinaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//            final TextView textView = binding.textGallery;

        all_events = binding.allEvents;

        national = getResources().getIdentifier("ic_national", "drawable", getActivity().getPackageName());
        local = getResources().getIdentifier("ic_local", "drawable", getActivity().getPackageName());
        bank = getResources().getIdentifier("ic_bank", "drawable", getActivity().getPackageName());
        silent = getResources().getIdentifier("ic_silent", "drawable",getActivity().getPackageName());
        christian = getResources().getIdentifier("ic_christian", "drawable",getActivity().getPackageName());
        observance = getResources().getIdentifier("ic_observance", "drawable", getActivity().getPackageName());
        orthodox = getResources().getIdentifier("ic_orthodox", "drawable", getActivity().getPackageName());
        season = getResources().getIdentifier("ic_season", "drawable", getActivity().getPackageName());
        daylight = getResources().getIdentifier("ic_time_server", "drawable", getActivity().getPackageName());




        LinearLayout jan =  binding.jan;
        LinearLayout feb =  binding.feb;
        LinearLayout mar =  binding.mar;
        LinearLayout apr =  binding.apr;
        LinearLayout may =  binding.may;
        LinearLayout jun =  binding.jun;
        LinearLayout jul =  binding.jul;
        LinearLayout aug =  binding.aug;
        LinearLayout sep =  binding.sep;
        LinearLayout oct =  binding.oct;
        LinearLayout nov =  binding.nov;
        LinearLayout dec =  binding.dec;

        EventLoader2022 eventLoader2022 = new EventLoader2022(getActivity().getBaseContext());
        try {
            eventLoader2022.setEvents();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Event[] holidaysJan = eventLoader2022.getAllByMonth("jan");
        Event[] holidaysFeb = eventLoader2022.getAllByMonth("feb");
        Event[] holidaysMar = eventLoader2022.getAllByMonth("mar");
        Event[] holidaysApr = eventLoader2022.getAllByMonth("apr");
        Event[] holidaysMay = eventLoader2022.getAllByMonth("may");
        Event[] holidaysJun = eventLoader2022.getAllByMonth("jun");
//        Event[] holidaysJul = eventLoader.getAllByMonth("jul"); NO ANY EVENTS OR HOLIDAYS IN JULY
        Event[] holidaysAug = eventLoader2022.getAllByMonth("aug");
        Event[] holidaysSep = eventLoader2022.getAllByMonth("sep");
        Event[] holidaysOct = eventLoader2022.getAllByMonth("oct");
        Event[] holidaysNov = eventLoader2022.getAllByMonth("nov");
        Event[] holidaysDec = eventLoader2022.getAllByMonth("dec");

        addHolidaysToView(jan, holidaysJan);
        addHolidaysToView(feb, holidaysFeb);
        addHolidaysToView(mar, holidaysMar);
        addHolidaysToView(apr, holidaysApr);
        addHolidaysToView(may, holidaysMay);
        addHolidaysToView(jun, holidaysJun);
//        addHolidaysToView(jul, holidaysJul); NO ANY EVENTS OR HOLIDAYS IN JULY
        addHolidaysToView(aug, holidaysAug);
        addHolidaysToView(sep, holidaysSep);
        addHolidaysToView(oct, holidaysOct);
        addHolidaysToView(nov, holidaysNov);
        addHolidaysToView(dec, holidaysDec);



//            poyaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void addHolidaysToView(LinearLayout linearLayout, Event[] holidays) {

        if (holidays != null) {

            int len = holidays.length;
            for (int i = 0; i < len; i++) {

                final LayoutInflater layoutInflater = LayoutInflater.from(getActivity().getBaseContext());
                View relativeLayout = layoutInflater.inflate(R.layout.holiday_view, null);

                TextView textView = relativeLayout.findViewById(R.id.textViewHoliday);
                ImageView imageView = relativeLayout.findViewById(R.id.imageViewType);

                String name = holidays[i].getName();
                int day = holidays[i].getDay();
                String type = holidays[i].getType();

                textView.setText(name + " - " + day);
                imageView.setImageResource(getType(type));

                linearLayout.addView(relativeLayout);

                System.out.println(i + type);
            }

            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    public int getType(String type) {
        switch (type) {
            case "National holiday":
                return national;
            case "Local holiday":
                return local;
            case "Bank holiday":
                return bank;
            case "Silent day":
                return silent;
            case "Christian":
                return christian;
            case "Observance":
                return observance;
            case "Season":
                return season;
            case "Clock change/Daylight Saving Time":
                return daylight;
            default:
                return 0;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}