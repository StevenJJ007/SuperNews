package inc.shijj.supernews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import inc.shijj.supernews.App;
import inc.shijj.supernews.R;
import inc.shijj.supernews.beans.NewsBean;

/**
 * Created by shijj on 2016/11/29.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {
    private List<NewsBean.DataBean> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    /**
     * 设置点击回调
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public MyAdapter(List<NewsBean.DataBean> data) {
        this.list = data;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        //注册点击时间
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_description.setText(list.get(position).getDescription());
        //glide加载图片
        Glide.with(App.application).load(list.get(position).getPicSmall()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView tv_name;
        TextView tv_description;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_name = (TextView) itemView.findViewById(R.id.tv_title);
            tv_description = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    /**
     * item点击接口
     */
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}
