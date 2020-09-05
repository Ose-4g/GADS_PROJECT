package com.ose4g.gadsproject;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiHelper
{
    private static final String BASE_API_URL =" https://gadsapi.herokuapp.com/api";
    private static final String IQ_SCORE_QUERY = "skilliq";
    private static final String HOURS = "hours";
    private static final String KEY = "/";

    private ApiHelper(){}

    public static URL buildUrl(int mode)
    {

        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon().appendPath((mode==1?HOURS:IQ_SCORE_QUERY))
                .build();

        try
        {
            url = new URL(uri.toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return url;
    }

    public static String getJSON(URL url,int mode) throws IOException
    {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

         try
         {
             InputStream stream = connection.getInputStream();
             Scanner scanner = new Scanner(stream);
             scanner.useDelimiter("\\A");
             boolean hasData = scanner.hasNext();
             if (hasData)
                 return scanner.next();

             return null;
         }
         catch (Exception e)
         {
             Log.d("error",e.toString());
             return null;
         }
         finally {
             connection.disconnect();
         }
    }

    public static ArrayList<Learner> getLearners(String json,int mode)
    {
        String name,country,badgeURL;
        int hours,score;
        ArrayList<Learner> learners = new ArrayList<>();
        try
        {
            hours=-1;
            score=-1;
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonLearner = jsonArray.getJSONObject(i);
                name = jsonLearner.getString("name");
                country = jsonLearner.getString("country");
                badgeURL = jsonLearner.getString("badgeUrl");

                if (mode==1)
                    hours=jsonLearner.getInt("hours");
                else
                    score = jsonLearner.getInt("score");

                learners.add(new Learner(name,score,hours,country,badgeURL));
            }

            return learners;
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
