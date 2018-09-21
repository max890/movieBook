package com.powercode.test.max.moviebook.ui.databinding;

import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.powercode.test.max.moviebook.ui.utils.CircleTransform;
import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @BindingAdapter("onClick")
    public static void setOnClickListener(final View view, @Nullable final OnClickListener listener) {
        if (listener != null) {
            view.setOnClickListener(v -> listener.onClick());
        } else {
            view.setOnClickListener(null);
        }
    }

    @BindingAdapter("onClick")
    public static void setOnClickListener(final View view, @Nullable Runnable listener) {
        if (listener != null) {
            view.setOnClickListener(v -> listener.run());
        } else {
            view.setOnClickListener(null);
        }
    }

    @BindingAdapter(value = "bindCircleImage")
    public static void setCircleImageUri(final ImageView view, @Nullable String url) {
        if (TextUtils.isEmpty(url)) {
            Picasso.with(view.getContext())
                    .load(android.R.drawable.ic_menu_report_image)
                    .transform(new CircleTransform())
                    .into(view);
        } else {
            Picasso.with(view.getContext())
                    .load(url)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .transform(new CircleTransform())
                    .into(view);
        }
    }

    @BindingAdapter(value = "bindImage")
    public static void setImageUri(final ImageView view, @Nullable String url) {
        if (TextUtils.isEmpty(url)) {
            Picasso.with(view.getContext())
                    .load(android.R.drawable.ic_menu_report_image)
                    .into(view);
        } else {
            Picasso.with(view.getContext())
                    .load(url)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .into(view);
        }
    }


    public interface OnClickListener {
        void onClick();
    }
}
