package com.ose4g.gadsproject;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;


public class LearnersFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<Learner> learners;
    private final int ID = 1;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_learners, container, false);
        learners = new ArrayList<>();
        mRecyclerView = mView.findViewById(R.id.recycler_view);

        try {
            URL url = ApiHelper.buildUrl(ID);
            new HoursQueryTask().execute(url);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        mView.findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                URL url = ApiHelper.buildUrl(ID);
                new HoursQueryTask().execute(url);
            }
        });


        return mView;
    }

    public class HoursQueryTask extends AsyncTask<URL,Void,String>
    {

        @Override
        protected void onPreExecute() {
            ((ProgressBar) mView.findViewById(R.id.progressBar)).setVisibility(View.VISIBLE);
            ((TextView) mView.findViewById(R.id.error_message)).setVisibility(View.INVISIBLE);
            ((RecyclerView) mView.findViewById(R.id.recycler_view)).setVisibility(View.INVISIBLE);
            mView.findViewById(R.id.refresh).setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onPostExecute(String json) {
            if(json==null)
            {
                ((ProgressBar) mView.findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
                ((TextView) mView.findViewById(R.id.error_message)).setVisibility(View.VISIBLE);
                mView.findViewById(R.id.refresh).setVisibility(View.VISIBLE);
                ((RecyclerView) mView.findViewById(R.id.recycler_view)).setVisibility(View.INVISIBLE);
            }
            else
            {
                learners = ApiHelper.getLearners(json,ID);
                mRecyclerView.setAdapter(new LearnersListAdaptor(mView.getContext(),learners,ID));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
                ((ProgressBar) mView.findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
                ((TextView) mView.findViewById(R.id.error_message)).setVisibility(View.INVISIBLE);
                ((RecyclerView) mView.findViewById(R.id.recycler_view)).setVisibility(View.VISIBLE);
                mView.findViewById(R.id.refresh).setVisibility(View.INVISIBLE);
            }



        }


        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;
            try
            {
                result = ApiHelper.getJSON(searchURL,ID);
            }
            catch (Exception e)
            {
                Log.d("error",e.getMessage());
            }

            return result;
        }
    }
}