package com.universlsoftware.nakathpathraya.ui.poyaday.nwadudina;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.universlsoftware.nakathpathraya.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class EventLoader2022 {
    private Context context;
    private ArrayList<Event> holidays;
    private ArrayList<Event> events;

    public EventLoader2022(Context context) {
        this.context = context;
        this.holidays = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public void setEvents() throws IOException, JSONException {

        // get JSONObject from 2022 JSON file
        InputStream is = context.getAssets().open("holidays2023.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String json = new String(buffer, "UTF-8");
        JSONObject obj = new JSONObject(json);

        JSONArray eventList = obj.getJSONArray("events");
        for (int i = 0; i < eventList.length(); i++) {
            JSONObject event = eventList.getJSONObject(i);

            String name = event.getString("name");
            String type = event.getString("type");
            String to = event.getString("to");
            String except = event.getString("except");
            String month = event.getString("month");
            String day = event.getString("day");
            boolean isHoliday = event.getBoolean("holiday");

            if (event.getBoolean("holiday")) {
                Event ev = new Event(name, type, to, except, month, day, isHoliday);
                this.holidays.add(ev);
            } else {
                Event ev = new Event(name, type, to, except, month, day, isHoliday);
                this.events.add(ev);
            }
        }
    }

    public Event[] getAll() {
        Event[] all = new Event[events.size() + holidays.size()];
        for (int i = 0; i < events.size(); i++) {
            all[i] = events.get(i);
        }

        for (int i = 0; i < holidays.size(); i++) {
            all[events.size() + i] = holidays.get(i);
        }

        return all;
    }

    public Event[] getHolidays() {
        Event[] holidayArr = new Event[holidays.size()];
        for (int i = 0; i < holidays.size(); i++) {
            holidayArr[i] = holidays.get(i);
        }

        return holidayArr;
    }

    public Event[] getEvents() {
        Event[] eventArr = new Event[events.size()];
        for (int i = 0; i < events.size(); i++) {
            eventArr[i] = events.get(i);
        }

        return eventArr;
    }

    //This function is used to get events by month
    public Event[] getEventsByMonth(String month) {
        Event[] eventList = null;
        month = month.toLowerCase();

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getMonth().equals(month)) {
                Event[] temp;
                if (eventList == null) {
                    temp = new Event[1];
                    temp[0] = events.get(i);
                } else {
                    temp = new Event[eventList.length + 1];

                    for (int j = 0; j < eventList.length; j++) {
                        temp[j] = eventList[j];
                    }
                    temp[eventList.length] = events.get(i);
                }
                eventList = temp;
            }
        }

        return eventList;
    }

    //This event is used to get holidays by month
    public Event[] getHolidaysByMonth(String month) {
        Event[] holidayList = null;
        month = month.toLowerCase();

        for (int i = 0; i < holidays.size(); i++) {
            if (holidays.get(i).getMonth().equals(month)) {
                Event[] temp;
                if (holidayList == null) {
                    temp = new Event[1];
                    temp[0] = holidays.get(i);
                } else {
                    temp = new Event[holidayList.length + 1];

                    for (int j = 0; j < holidayList.length; j++) {
                        temp[j] = holidayList[j];
                    }
                    temp[holidayList.length] = holidays.get(i);
                }
                holidayList = temp;
            }
        }

        return holidayList;
    }

    //This event is used to get every all events by month
    public Event[] getAllByMonth(String month) {
        Event[] allEvents = null;
        month = month.toLowerCase();

        Event[] tempHolidays = getHolidaysByMonth(month);
        Event[] tempEvents = getEventsByMonth(month);

        if (tempEvents != null && tempHolidays != null) {

            allEvents = new Event[tempHolidays.length + tempEvents.length];

            for (int i = 0; i < tempHolidays.length; i++) {
                allEvents[i] = tempHolidays[i];
            }

            for (int i = 0; i < tempEvents.length; i++) {
                allEvents[tempHolidays.length + i] = tempEvents[i];
            }
        } else {

            allEvents = tempEvents == null ? tempHolidays : tempEvents;
        }

        if (allEvents != null) {

            Arrays.sort(allEvents, new Comparator<Event>() {
                @Override
                public int compare(Event o1, Event o2) {
                    return o1.getDay() - o2.getDay();
                }
            });
        }

        return allEvents;
    }

    //This function is used to set the holidays for 2022 calendar
    public void setHolidays(LinearLayout linearLayout, String month, int year) {
        linearLayout.removeAllViews();
        month = month.toLowerCase();

        for(int i = 0; i < holidays.size(); i++) {

            if (holidays.get(i).getMonth().equals(month) && year == 2023) {

                int day = holidays.get(i).getDay();
                String name = holidays.get(i).getName();

                String text = String.format("%d - %s", day, name);

                if (!holidays.get(i).getExcept().equals("none")) {
                    text += "\n     Außer " + holidays.get(i).getExcept();
                }

                if (!holidays.get(i).getTo().equals("all")) {
                    text += "\n     Nur für " + holidays.get(i).getTo();
                }

                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View holidayLine = layoutInflater.inflate(R.layout.holiday_view_main, null);

                TextView textView = holidayLine.findViewById(R.id.textView);
                ImageView imageView = holidayLine.findViewById(R.id.imageView);

                textView.setText(text);

                switch (holidays.get(i).getType()) {
                    case "National holiday":
                        imageView.setImageResource(R.drawable.national_holiday);
                        break;
                    case "Local holiday":
                        imageView.setImageResource(R.drawable.local_holiday);
                        break;
                    case "Bank holiday":
                        imageView.setImageResource(R.drawable.bank_holiday);
                        break;
                    case "Silent day":
                        imageView.setImageResource(R.drawable.silent_day);
                        break;
                }


                linearLayout.addView(holidayLine);
            }
        }
    }

    //This method is used to get Holidays by month and day
    public Event[] getHolidaysByMonthAndDay(String month, String day) {
        month = month.toLowerCase();
        Event[] holidaysList = null;

        for (int i = 0; i < holidays.size(); i++) {
            if (String.valueOf(holidays.get(i).getDay()).equals(day) && month.equals(holidays.get(i).getMonth())) {
                Event[] temp;
                if (holidaysList == null) {
                    temp = new Event[1];
                    temp[0] = holidays.get(i);
                } else {
                    temp = new Event[holidaysList.length + 1];

                    for (int j = 0; j < holidaysList.length; j++) {
                        temp[j] = holidaysList[j];
                    }

                    temp[holidaysList.length] = holidays.get(i);
                }

                holidaysList = temp;
            }
        }

        return  holidaysList;
    }
}
