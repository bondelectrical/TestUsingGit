package nlt.bondarenko.newsapp.screens.articleList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.util.newsApi.models.Article;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleListViewHolder> {

    private final LayoutInflater inflater;
    private List<Article> articleList;
    private Context context;
    private OnClickListenerArticleList onClickListenerArticleList;

    public ArticleListAdapter(Context context, OnClickListenerArticleList onClickListenerArticleList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.onClickListenerArticleList = onClickListenerArticleList;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleListViewHolder holder, int position) {
        Article articleItem = articleList.get(position);
        ImageView imageViewNews = holder.imageViewNews;
        Glide.with(imageViewNews.getContext()).load(articleItem.getUrlToImage()).into(imageViewNews);
        holder.textViewTitleNews.setText(articleItem.getTitle());
        holder.textViewDescriptionNews.setText(articleItem.getDescription());
        holder.textViewNameSource.setText(articleItem.getName());
        holder.imageViewButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenerArticleList.onClickListenerArticleShare(articleItem);

            }
        });

        holder.imageViewButtonMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (articleItem != null) {
                    onClickListenerArticleList.onClickItemArticle(articleItem);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenerArticleList.onClickListenerWebView(articleItem.getUrl());
            }
        });


    }

    public interface OnClickListenerArticleList {

        void onClickItemArticle(Article news);

        void onClickListenerArticleShare(Article news);

        void onClickListenerWebView(String url);

    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ArticleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = inflater.inflate(R.layout.item_article_list, parent, false);
        return new ArticleListViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (articleList != null) {
            return articleList.size();
        }
        return 0;
    }

    public class ArticleListViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewNews;
        TextView textViewTitleNews;
        TextView textViewDescriptionNews;
        TextView textViewNameSource;
        ImageView imageViewButtonShare;
        ImageView imageViewButtonMark;


        public ArticleListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewNews = itemView.findViewById(R.id.image_view_news);
            textViewTitleNews = itemView.findViewById(R.id.text_view_news);
            textViewDescriptionNews = itemView.findViewById(R.id.text_view_description_news);
            textViewNameSource = itemView.findViewById(R.id.text_view_source);
            imageViewButtonShare = itemView.findViewById(R.id.image_button_share);
            imageViewButtonMark = itemView.findViewById(R.id.image_button_bookmark);

        }
    }

}
