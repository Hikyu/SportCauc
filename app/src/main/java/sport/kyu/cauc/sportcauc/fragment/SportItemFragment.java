package sport.kyu.cauc.sportcauc.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sport.kyu.cauc.sportcauc.R;
import sport.kyu.cauc.sportcauc.activity.SportActivity;
import sport.kyu.cauc.sportcauc.adapter.SportItemAdapter;
import sport.kyu.cauc.sportcauc.bean.SportItem;

/**
 * Created by kyu on 2015/9/22 at 16:49.
 */
public class SportItemFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<SportItem> mDatas;
    private SportItemAdapter mSportItemAdapter;
    private FloatingActionButton mFloatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void init() {
        mDatas=new ArrayList<>();
        SportItem sportItem=new SportItem();
        sportItem.setImgUrl("file:///android_asset/run.jpg");
        sportItem.setPresent("短跑");
        mDatas.add(sportItem);

        SportItem sportItem1=new SportItem();
        sportItem1.setImgUrl("file:///android_asset/dangang.jpg");
        sportItem1.setPresent("单杠");
        mDatas.add(sportItem1);

        SportItem sportItem2=new SportItem();
        sportItem2.setImgUrl("file:///android_asset/tiaoyuan.jpg");
        sportItem2.setPresent("跳远");
        mDatas.add(sportItem2);

        SportItem sportItem3=new SportItem();
        sportItem3.setImgUrl("file:///android_asset/yangwoqizuo.jpg");
        sportItem3.setPresent("仰卧起坐");
        mDatas.add(sportItem3);

        SportItem sportItem4=new SportItem();
        sportItem4.setImgUrl("file:///android_asset/zuqiu.jpg");
        sportItem4.setPresent("足球");
        mDatas.add(sportItem4);

        SportItem sportItem5=new SportItem();
        sportItem5.setImgUrl("file:///android_asset/qianqiu.jpg");
        sportItem5.setPresent("铅球");
        mDatas.add(sportItem5);

        SportItem sportItem6=new SportItem();
        sportItem6.setImgUrl("file:///android_asset/lanqiu.jpg");
        sportItem6.setPresent("篮球");
        mDatas.add(sportItem6);

        SportItem sportItem7=new SportItem();
        sportItem7.setImgUrl("file:///android_asset/youyong.jpg");
        sportItem7.setPresent("游泳");
        mDatas.add(sportItem7);

//        for(int i=0;i!=100;++i){
//            SportItem sportItem=new SportItem();
//            sportItem.setImgUrl("http://d.hiphotos.baidu.com/image/pic/item/8d5494eef01f3a29262d67269d25bc315d607c74.jpg");
//            sportItem.setPresent("第"+i);
//            mDatas.add(sportItem);
//        }
        mSportItemAdapter = new SportItemAdapter(getActivity(), mDatas);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mSportItemAdapter);
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mSportItemAdapter.setOnItemClickLitener(new SportItemAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 20, 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        getActivity().startActivity(new Intent(getActivity(), SportActivity.class));
                    }
                });
                animator.start();

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.sportitem_fragment, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.id_recyclerview);
        mFloatingActionButton= (FloatingActionButton) v.findViewById(R.id.refresh_btn);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });
        init();
        return  v;
    }


}
