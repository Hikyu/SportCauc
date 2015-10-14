package sport.kyu.cauc.sportcauc.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import sport.kyu.cauc.sportcauc.R;


public class RollPicFragment extends Fragment {

    private static final String ARG_URL = "url";
    private ImageView pic;
    private String picUrl;

    public static RollPicFragment newInstance(String picUrl) {
        RollPicFragment fragment = new RollPicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, picUrl);
        fragment.setArguments(args);
        return fragment;
    }

    public RollPicFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            picUrl = getArguments().getString(ARG_URL);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rollpic_fragment, container, false);
        pic = (ImageView) v.findViewById(R.id.roll_pic);

        Picasso.with(getActivity())
                .load(picUrl)
                .error(R.drawable.ic_picker_imagefail)
                .into(pic);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(getActivity(), WebViewActivity.class);
//                intent.putExtra("www",www);
//                startActivity(intent);
            }
        });
        return v;
    }

}
