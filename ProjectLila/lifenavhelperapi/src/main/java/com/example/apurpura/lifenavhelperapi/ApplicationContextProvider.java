package com.example.apurpura.lifenavhelperapi;

/**
 * Created by apurpura on 5/30/2017.
 */

import android.app.Application;
import android.content.Context;

public class ApplicationContextProvider extends Application  {

    private static Context mContext;

  /*  public static Context getContext() {
        if(mContext != null)
            return mContext;
        else{
            Intent intent = new Intent(new Context(), SigningOnActivity.class);
            this.startActivity(intent);

        }
    }*/

    public static void setContext(Context mContexty) {
        mContext = mContexty;
    }

}

