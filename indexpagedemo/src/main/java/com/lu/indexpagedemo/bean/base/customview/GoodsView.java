package com.lu.indexpagedemo.bean.base.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lu.indexpagedemo.R;


/**
 * Created by 陆正威 on 2017/4/14.
 */

public class GoodsView extends LinearLayout implements View.OnClickListener {

    private  boolean isMeasure = false;
    private final static int DEFULT_MAX = 1000;
    private final static int DEFULT_MIN = 0;
    private final static int DEFULT_RESID = R.color.colorAccent;
    private final static int DEFULT_COLOR = Color.BLACK;
    private final static int DEFULT_TEXTSIZE = 32;
    private final static int DEFULT_TEXTTYPE = Typeface.BOLD;
    private final static int DEFULT_NUM = 1;

    private int Max = DEFULT_MAX;
    private int Min = DEFULT_MIN;
    private int TextColor = DEFULT_COLOR;
    private int TextSize = DEFULT_TEXTSIZE;
    private int TextType = DEFULT_TEXTTYPE;
    private int DefultNum = DEFULT_NUM;

    Context mContext;
    private LinearLayout mGoodsLayout;
    private ImageView mDecreaseBtn;
    private ImageView mIncreaseBtn;
    private LayoutParams mEditLayoutParams;
    private LayoutParams mButtonLayoutParams;
    private TextView mEditText;
    private int ResId1 = 0;
    private int ResId2 = 0;
    private OnChangeListener changeListener;
    private OnOutOfRangeListener rangeListener;
    private int width = 0;
    private int height = 0;

    public GoodsView(Context context, @IdRes int id,@IdRes int id2){
        this(context);
        ResId1 = id;
        ResId2 = id2;
    }

    public GoodsView(Context context) {
        this(context,null);
    }

    public GoodsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GoodsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,R.styleable.GoodsView,defStyleAttr,defStyleAttr);
        try {
            ResId1 = typedArray.getResourceId(R.styleable.GoodsView_increaseRes, DEFULT_RESID);
            ResId2 = typedArray.getResourceId(R.styleable.GoodsView_decreaseRes,DEFULT_RESID);
            TextColor = typedArray.getColor(R.styleable.GoodsView_lu_textcolor,DEFULT_COLOR);
            TextSize = typedArray.getDimensionPixelSize(R.styleable.GoodsView_lu_textsize,DEFULT_TEXTSIZE);
            Max = typedArray.getInteger(R.styleable.GoodsView_lu_maxnum,DEFULT_MAX);
            Min = typedArray.getInteger(R.styleable.GoodsView_lu_minnum,DEFULT_MIN);
        }catch (Exception e){

        }finally {
            typedArray.recycle();
        }
        mContext = context;
        Init();
    }


    private void Init(){
        setOrientation(VERTICAL);

        mGoodsLayout = new LinearLayout(mContext);
        mGoodsLayout.setOrientation(HORIZONTAL);

        addView(mGoodsLayout, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mEditText = new TextView(mContext);
        mEditText.setTypeface(Typeface.defaultFromStyle(TextType));
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX,TextSize);
        mEditText.setGravity(Gravity.CENTER);
        //mEditText.setBackground(null);
        mEditLayoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
        mButtonLayoutParams = new LayoutParams(0,0);
        mButtonLayoutParams.gravity =  Gravity.CENTER_VERTICAL;

        mDecreaseBtn = new ImageView(mContext);
        mIncreaseBtn = new ImageView(mContext);
        mDecreaseBtn.setImageResource(ResId2);
        mIncreaseBtn.setImageResource(ResId1);

        mIncreaseBtn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mDecreaseBtn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        mDecreaseBtn.setTag("sub");
        mIncreaseBtn.setTag("add");

        mDecreaseBtn.setOnClickListener(this);
        mIncreaseBtn.setOnClickListener(this);

        mEditText.setText(String.valueOf(DefultNum));
        mEditText.setTextColor(TextColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        mButtonLayoutParams.width = height;
        mButtonLayoutParams.height = height;

        int textwidth = width - height - height;
        if(textwidth > 0) {
            mEditLayoutParams.setMarginStart(textwidth>>4);
            mEditLayoutParams.setMarginEnd(textwidth>>4);
        }

        if(!isMeasure) {
            mGoodsLayout.addView(mDecreaseBtn, mButtonLayoutParams);
            mGoodsLayout.addView(mEditText, mEditLayoutParams);
            mGoodsLayout.addView(mIncreaseBtn, mButtonLayoutParams);
            isMeasure = true;
        }else{
            mEditText.setLayoutParams(mEditLayoutParams);
            mIncreaseBtn.setLayoutParams(mButtonLayoutParams);
            mDecreaseBtn.setLayoutParams(mButtonLayoutParams);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onClick(View v) {

        if(changeListener!=null) changeListener.onClick(v);

        if(v.getTag().equals("sub")) {
            String string = mEditText.getText().toString();
            if(string.isEmpty()) string = "0";
            int i = Integer.parseInt(string);
            if(i <= Min) {
                if(rangeListener != null)
                    rangeListener.onOutOfRange(this,true);
                return;
            }

            String afterstring = String.valueOf(--i);
            mEditText.setText(afterstring);

            if(string.length() != afterstring.length())
                mEditText.requestLayout();

            if(changeListener != null)
                changeListener.onChangeListener(this,false);
        }
        else{

            String string = mEditText.getText().toString();
            if(string.isEmpty()) string = "0";
            int i = Integer.parseInt(string);
            if(i >= Max) {
                if(rangeListener != null)
                    rangeListener.onOutOfRange(this,false);
                return;
            }

            mEditText.setText(String.valueOf(++i));

            if(changeListener != null)
                changeListener.onChangeListener(this,true);

        }
    }


    public void setOnChangeListener(OnChangeListener listener) {
        this.changeListener = listener;
    }

    public void setOnOutOfRangeListener(OnOutOfRangeListener listener){
        this.rangeListener = listener;
    }

    public interface OnOutOfRangeListener{
        void onOutOfRange(View view,boolean isTooSmall);
    }

    public interface OnChangeListener extends OnClickListener{
        void onChangeListener(View view,boolean isIncrease);
    }

    public int getNum(){
        return Integer.parseInt(mEditText.getText().toString());
    }

    public void setNum(int num){
        mEditText.setText(String.valueOf(num));
        mEditText.requestLayout();
    }

    public void setMin(int min){
        new Bulider(mContext).setMin(min);
    }

    public void setMax(int max){
        new Bulider(mContext).setMax(max);
    }

    public class Bulider{

        public Bulider(Context context){
            mContext = context;
        }

        public Bulider setTextColor(int color){
            TextColor = color;
            return this;
        }

        public Bulider setMax(int max){
            if(max < DEFULT_MAX && max >Min)
                Max = max;
            return this;
        }

        public Bulider setMin(int min){
            if(min > DEFULT_MIN && min <Max)
                Min = min;
            return this;
        }

        public Bulider setImage(@IdRes int id,@IdRes int id2){
            ResId1 = id;ResId2 = id2;
            return this;
        }

        public Bulider setTextSize(int size){
            if(size > 0)
                TextSize = size;
            return this;
        }

        public Bulider setTypeFace(int type){
            if(type<4 && type >-1)
                TextType = type;
            return this;
        }

        public Bulider setDefultNum(int num){
            if(num > Min && num <Max)
                DefultNum = num;
            return this;
        }

        public GoodsView bulid(){
            return new GoodsView(mContext);
        }
    }
}
