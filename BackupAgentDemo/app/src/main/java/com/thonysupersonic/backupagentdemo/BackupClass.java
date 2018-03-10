package com.thonysupersonic.backupagentdemo;

import android.app.backup.BackupAgentHelper;
import android.app.backup.SharedPreferencesBackupHelper;

/**
 * Created by anthony on 2/24/18.
 */

public class BackupClass extends BackupAgentHelper {
    static final  String PRES_TEST = "testprefs";
    static final String MY_PREFS_BACKUP_KEY = "data";

    @Override
    public void onCreate() {
        SharedPreferencesBackupHelper helper = new SharedPreferencesBackupHelper(this, PRES_TEST);
        addHelper(MY_PREFS_BACKUP_KEY, helper);
    }
}
