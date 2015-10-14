package sport.kyu.cauc.sportcauc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sport.kyu.cauc.sportcauc.constant.Constant;
import sport.kyu.cauc.sportcauc.fragment.RollPicFragment;


public class DemoPagerAdapter extends FragmentPagerAdapter {

    private int pagerCount = Constant.ROLL_PIC_COUNT;//轮播图片数目

    public DemoPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    static String urls[] = new String[Constant.ROLL_PIC_COUNT];

    static

    {
        urls[0] = "http://picm.bbzhi.com/tiyubizhi/lanqiuxiliebizhi/design_sport_200624_m.jpg";
        urls[1] = "http://imgsrc.baidu.com/baike/pic/item/6609c93d70cf3bc7e43db93dd500baa1cd112a25.jpg";
        urls[2] = "http://pica.nipic.com/2008-04-21/2008421153143820_2.jpg";
    }

    @Override
    public Fragment getItem(int i) {
        return RollPicFragment.newInstance(urls[i]);
    }

    @Override
    public int getCount() {
        return pagerCount;
    }
}