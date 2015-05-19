package com.yctc.gameservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MusicPlayService extends Service{

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
