package sport.kyu.cauc.sportcauc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sport.kyu.cauc.sportcauc.R;
import sport.kyu.cauc.sportcauc.bean.SportItem;


/**
 * 瀑布流适配器
 * Created by kyu on 2015/7/9 at 20:16.
 */
public class SportItemAdapter extends
        RecyclerView.Adapter<SportItemAdapter.MyViewHolder> {

    private List<SportItem> mDatas;
    private LayoutInflater mInflater;
    private Context context;
    private List<Integer> mHeights;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public SportItemAdapter(Context context, List<SportItem> datas) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;

//        mHeights = new ArrayList<Integer>();
//        if (mDatas != null && !mDatas.isEmpty()) {//数据不为空
//            for (int i = 0; i < mDatas.size(); i++) {
//                mHeights.add((int) (100 + Math.random() * 300));
//            }
//        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.view_sportitem, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

     //   ViewGroup.LayoutParams lp = holder.sportItemImg.getLayoutParams();
    //    lp.height = mHeights.get(position);
        SportItem sportItem = mDatas.get(position);

        holder.sportItemPresent.setText(sportItem.getPresent());
     //   holder.sportItemImg.setLayoutParams(lp);
        //   holder.taskDate.setText("2014/5/6");
       // Log.i("item", String.valueOf(sportItem.getImgLocal()));
        Picasso.with(context)
                .load(sportItem.getImgUrl())
                .into(holder.sportItemImg);

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
//                    removeData(pos);
                    return false;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    public void addData(int position) {
        //  mDatas.add(position, "Insert One");
        mHeights.add((int) (100 + Math.random() * 300));
        notifyItemInserted(position);

    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView sportItemImg;
        TextView sportItemPresent;
        public MyViewHolder(View view) {
            super(view);
            sportItemImg= (ImageView) view.findViewById(R.id.sportitem_img);
            sportItemPresent= (TextView) view.findViewById(R.id.sportitem_present);
        }
    }
}
