package com.powercode.test.max.moviebook.ui.databinding;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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

    @BindingAdapter("fadeVisible")
    public static void setFadeVisible(final View view, boolean visible) {
        if (view.getTag() == null) {
            view.setTag(true);
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        } else {
            view.animate().cancel();


            if (visible) {
                view.setVisibility(View.VISIBLE);
                view.setAlpha(0);
                view.animate().alpha(1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setAlpha(1);
                    }
                });
            } else {
                view.animate().alpha(0).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setAlpha(1);
                        view.setVisibility(View.GONE);
                    }
                });
            }
        }
    }


    public interface OnClickListener {
        void onClick();
    }
}
