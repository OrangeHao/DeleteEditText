package com.orange.deleteedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


/**
 * created by czh on 2018-01-15
 */

public class DeletelEditText extends AppCompatEditText {

    private int  ic_deleteResID; // 删除图标 资源ID
    private Drawable  ic_delete; // 删除图标


    public DeletelEditText(Context context) {
        super(context);
    }

    public DeletelEditText(Context context, AttributeSet attrs) {
        super(context,attrs);
        initView(context,attrs);
    }

    public DeletelEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    private void initView(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DeleteEditText);
        ic_deleteResID = typedArray.getResourceId(R.styleable.DeleteEditText_ic_delete,-1);
    }


//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        mHeight=heightSize;
//        Log.d("czh","height:"+mHeight);
//
//        if (ic_deleteResID!=-1){
//            ic_delete=getResources().getDrawable(ic_deleteResID);
//            ic_delete.setBounds(0, 0, 50, 50);
//        }
//    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("czh","wh:"+w+"  "+h);
        if (ic_deleteResID!=-1){
            ic_delete=getResources().getDrawable(ic_deleteResID);
            if (h>70) {
                h = 65;
            }
            ic_delete.setBounds(0, 0, h, h);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setDeleteIconVisible(hasFocus() && text.length() > 0);
    }


    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setDeleteIconVisible(focused && length() > 0);
    }

    private void setDeleteIconVisible(boolean deleteVisible) {
        if (ic_deleteResID!=-1){
            setCompoundDrawables(null, null, deleteVisible ?  ic_delete: null, null);
            invalidate();
        }
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (ic_deleteResID==-1){
            return super.onTouchEvent(event);
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Drawable drawable =  ic_delete;
                if (drawable != null && event.getX() <= (getWidth() - getPaddingRight())
                        && event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width())) {
                    setText("");
                }
                break;
        }

        return super.onTouchEvent(event);
    }

}
