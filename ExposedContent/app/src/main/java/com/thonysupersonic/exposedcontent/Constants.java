package com.thonysupersonic.exposedcontent;

import android.net.Uri;

/**
 * Created by anthony on 2/24/18.
 */

public class Constants {
    private Constants(){

    }

    public static final Uri URL = Uri.parse("content://" + DataProvider.AUTHORITY + "/t1");

    public static final String CONTENT_TYPE = "cnd.android.cursor.dir/vnd.android.contentproviderlab.t1";
    public static final String ID = "_id";
    public static final String TEXT = "MESSAGE";
    public static final String TEXT_DATA = "Hello Content Providers!";
}
