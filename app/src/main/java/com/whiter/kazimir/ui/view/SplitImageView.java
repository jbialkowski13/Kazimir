package com.whiter.kazimir.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.whiter.kazimir.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by whiter
 */
public class SplitImageView extends LinearLayout {

    @InjectView(R.id.left_image)
    ImageView leftImage;

    @InjectView(R.id.right_image)
    ImageView rightImage;
    private Drawable leftDrawable;
    private Drawable rightDrawable;

    public SplitImageView(Context context) {
        this(context, null);
    }

    public SplitImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplitImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateSelf(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SplitImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateSelf(context, attrs, defStyleAttr, defStyleRes);
    }


    private void inflateSelf(Context context, AttributeSet attributeSet, int defStyleAttr, int defStylRes) {
        LayoutInflater.from(context).inflate(R.layout.split_image_view, this);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.SplitImageView, defStyleAttr, defStylRes);
        leftDrawable = typedArray.getDrawable(R.styleable.SplitImageView_whiter_leftImage);
        rightDrawable = typedArray.getDrawable(R.styleable.SplitImageView_whiter_rightImage);
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
        getLeftImage().setImageDrawable(leftDrawable);
        getRightImage().setImageDrawable(rightDrawable);
    }


    public ImageView getLeftImage() {
        return leftImage;
    }

    public ImageView getRightImage() {
        return rightImage;
    }
}
