package com.thonysupersonic.redditclient.view.utilities;


import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by anthony on 2/16/18.
 */

public class Functions {

    public static String convertUTCTime(long utctime){




        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        TimeZone tz = TimeZone.getDefault();
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        java.util.Date currenTimeZone = new java.util.Date((utctime *1000)); //date in current timezone


        long diff = currentDate.getTime() - currenTimeZone.getTime();
        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if(minutes > 59){
            return  hours + " hours ago" ;
        }else{
            return minutes + " minutes ago";
        }

    }
}
