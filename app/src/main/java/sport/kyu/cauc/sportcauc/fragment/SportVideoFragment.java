package sport.kyu.cauc.sportcauc.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victor.loading.newton.NewtonCradleLoading;

import sport.kyu.cauc.sportcauc.R;


public class SportVideoFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sportvideo_fragment, container, false);
        NewtonCradleLoading newtonCradleLoading= (NewtonCradleLoading) v.findViewById(R.id.newton_cradle_loading);
        newtonCradleLoading.start();
        return v;
    }

}
