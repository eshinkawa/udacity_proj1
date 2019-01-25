package project1.udacity.eduardoshinkawa.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

     public static Boolean checkConnectivity(Context context) {

         ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

         NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
         boolean isConnected = activeNetwork != null &&
                 activeNetwork.isConnectedOrConnecting();
         return isConnected;
     }

}
