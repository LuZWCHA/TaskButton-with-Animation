package com.lu.mytaskbutton;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Region;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;

import java.lang.ref.WeakReference;

/**
 * Created by 陆正威 on 2017/2/23.
 */

public class TaskButton extends View {
    
     public enum State{
        NORMAL,
        TASKSTART,
        DOINGTASK,
        TASKFINISH
    }

    private TaskButtonListener mListener;
    private TaskButtonEventListener mEventListener;
    
    private Matrix mMapMatrix = null;
    private boolean isTouch = false;

    private Region buttonReg;
    private Region globalRegion;

    private final int MAX_TEXT_SIZE = 127;
    private final int MIN_TEXT_SIZE = 1;
    private final int DEFLUT_TIMEOUT = 5;

    public final static int FORCE_STOP_ID = 1;
    public final static int WAIT_STOP_ID = 2;
    public final static int NORMAL_STOP_ID = 0;

    private State CurState = State.NORMAL;
    private int timeOut = DEFLUT_TIMEOUT;
    private final int DEFULT_TEXT_SIZE = 32;
    private int defaultDuration = 100;
    private Path buttonPath;
    private int mWeith;
    private int mHeight;
    private float r;
    private String Text = "";
    private int TextSize = DEFULT_TEXT_SIZE;
    private int maxTextSize = MAX_TEXT_SIZE;
    private int minTextSize = MIN_TEXT_SIZE;
    private final int DEFULT_TEXT_COLOR = Color.WHITE;
    private int TextColor = DEFULT_TEXT_COLOR;
    private final  int DEFULT_BUTTONCOLOR  = Color.GRAY;
    private final int DEFFULT_BUTTON_PRESS_COLOR = Color.DKGRAY;
    private int ButtonPressColor = DEFFULT_BUTTON_PRESS_COLOR;
    private int ButtonNormalColor = DEFULT_BUTTONCOLOR;
    private int buttonColor = DEFULT_BUTTONCOLOR;
    
    private ValueAnimator StartAnm;
    private ValueAnimator DoAnm;
    private ValueAnimator FinishAnm;

    private ValueAnimator.AnimatorUpdateListener AnmUpdListener;
    private ValueAnimator.AnimatorListener AnmListener;

    private Handler AnmHandler;
    private float AnmValue = 1f;

    private Paint buttonPaint;
    private TextPaint textPaint;

    private float textStartX;
    private float textStartY;
    private boolean textChanged = false;

    public final static float DEFULT_EMOLLESCENCE = 1;
    public final static float DEFULT_EXTENSION = 1;
    private float emollescence = DEFULT_EMOLLESCENCE;//动画中小球的硬度
    private float extension = DEFULT_EXTENSION;//小球被拉伸的程度
    private float PointsConfig[] = {0,0,0,0,0,0,0};
    private Point point0;
    private Point point1;
    private Point point2;
    private Point point3;

    public void setEmollescence(float emollescence) {
        this.emollescence = emollescence;
    }

    public void setExtension(float extension) {
        this.extension = extension;
    }

    public void setText(String text)
    {
        if(textPaint.measureText(text) != textPaint.measureText(Text))
            textChanged = true;
        Text = text;

        invalidate();
    }
    
    public void setSpeed(float rate) throws IllegalAccessException {
        int duration;
        if(rate>0 && rate<10)
            duration = (int) (defaultDuration * rate);
        else
            throw new IllegalArgumentException();

        if(DoAnm.isRunning() || StartAnm.isRunning() || FinishAnm.isRunning())
            throw new IllegalAccessException("Anm is running");

        DoAnm.setDuration(duration*10);
        StartAnm.setDuration(duration*2);
        FinishAnm.setDuration(duration*2);
        invalidate();
    }
    
    public void setMaxTextSize(int size) throws Exception {
        if(maxTextSize >= MIN_TEXT_SIZE && maxTextSize <= MAX_TEXT_SIZE && maxTextSize >= minTextSize)
            this.maxTextSize = size;
        else
            throw new Exception("size error");
    }

    public void setMinTextSize(int minTextSize) throws Exception {
        if(minTextSize >= MIN_TEXT_SIZE && minTextSize <= MAX_TEXT_SIZE && maxTextSize >= minTextSize)
            this.minTextSize = minTextSize;
        else
            throw new Exception("size error");
    }

    public void setTextSize(int textSize) throws Exception {
        if(minTextSize >= MIN_TEXT_SIZE && minTextSize <= MAX_TEXT_SIZE && maxTextSize >= minTextSize) {
            TextSize = textSize;

            invalidate();
        }
        else
            throw new Exception("size error");
    }

    public void setTextColor(int color)
    {
        TextColor = color;
        invalidate();
    }

    public void setButtonColor(int color)
    {
        buttonColor = color;
        ButtonNormalColor = color;
        invalidate();
    }

    public void setButtonPressColor(int color)
    {
        ButtonPressColor = color;
        invalidate();
    }

    public TaskButton.State getCurState()
    {
        return CurState;
    }

    public boolean startTaskAnimation()
    {
        if(CurState == State.NORMAL)
        {
            AnmHandler.sendEmptyMessage(0);
            return  true;
        }
        else
        {
            return  false;
        }
    }

    public boolean forcestopTaskAnimation()
    {
        return CurState == State.DOINGTASK && AnmHandler.sendEmptyMessage(FORCE_STOP_ID);
    }

    public boolean waitTaskAnimation()
    {
        return CurState == State.DOINGTASK && AnmHandler.sendEmptyMessage(WAIT_STOP_ID);
    }

    public TaskButton(Context context) {
        this(context,null);
    }

    public TaskButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public TaskButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        buttonPath= new Path();
        buttonReg = new Region();
        mMapMatrix = new Matrix();

        point0 = new Point();
        point1 = new Point();
        point2 = new Point();
        point3 = new Point();

        PaintInit();
        HandlerInit();
        AnmListenerInit();
        AnmInit();

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,R.styleable.TaskButton,defStyleAttr,defStyleAttr);

        try {
            maxTextSize =  Math.abs(typedArray.getDimensionPixelSize(R.styleable.TaskButton_text_maxsize, px2sp(context,MAX_TEXT_SIZE)));
            minTextSize = Math.abs(typedArray.getDimensionPixelSize(R.styleable.TaskButton_text_minsize, px2sp(context,MIN_TEXT_SIZE)));
            TextSize = Math.abs(typedArray.getDimensionPixelSize(R.styleable.TaskButton_button_textsize,DEFULT_TEXT_SIZE));
            defaultDuration = Math.abs(typedArray.getInteger(R.styleable.TaskButton_animation_speed, 100));
            TextColor = typedArray.getColor(R.styleable.TaskButton_button_text_color, DEFULT_TEXT_COLOR);
            ButtonPressColor = typedArray.getColor(R.styleable.TaskButton_button_press_color, DEFFULT_BUTTON_PRESS_COLOR);
            ButtonNormalColor = typedArray.getColor(R.styleable.TaskButton_button_color, DEFULT_BUTTONCOLOR);
            Text = typedArray.getString(R.styleable.TaskButton_button_text);
            timeOut = Math.abs(typedArray.getInteger(R.styleable.TaskButton_animation_timeout,DEFLUT_TIMEOUT));
        }finally {
            adjustTextSize();
            typedArray.recycle();
        }
    }

    private int px2sp(Context context, int pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    private void PathandRegionInit()
    {
        globalRegion = new Region(-mWeith,-mHeight,mWeith,mHeight);
        buttonPath.addRoundRect(0,0,mWeith,mHeight,r,r, Path.Direction.CW);
        buttonReg.setPath(buttonPath,globalRegion);
    }

    private void PaintInit()
    {
        buttonPaint = new Paint();
        textPaint = new TextPaint();

        buttonPaint.setAntiAlias(true);
        textPaint.setAntiAlias(true);

        textPaint.setColor(TextColor);
        buttonPaint.setColor(buttonColor);

        textPaint.setTextSize(TextSize);
        buttonPaint.setStyle(Paint.Style.FILL);

    }

    private void AnmListenerInit()
    {
        AnmUpdListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnmValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        };

        AnmListener = new Animator.AnimatorListener() {


            @Override
            public void onAnimationStart(Animator animation) {
                //do nothing
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(AnmHandler != null)
                    AnmHandler.sendEmptyMessage(NORMAL_STOP_ID);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //do nothing
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //do nothing
            }

        };
    }

    private void HandlerInit()
    {
        AnmHandler = new mybuttontask(this);
    }

    private void AnmInit()
    {
        StartAnm = ValueAnimator.ofFloat(1, 0).setDuration(defaultDuration*2);
        DoAnm = ValueAnimator.ofFloat(0, 1 ,0).setDuration(defaultDuration*10);
        DoAnm.setInterpolator(new ExtensionInterpolator2());
        FinishAnm = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration*2);

        StartAnm.addUpdateListener(AnmUpdListener);
        DoAnm.addUpdateListener(AnmUpdListener);
        FinishAnm.addUpdateListener(AnmUpdListener);

        StartAnm.addListener(AnmListener);
        DoAnm.addListener(AnmListener);
        FinishAnm.addListener(AnmListener);
    }

    private void adjustTextSize(){

        int avaiWidth = (int) (getWidth() - getPaddingLeft() - getPaddingRight()- 2*r);
        if (avaiWidth <= 0) {
            return;
        }
        TextPaint textPaintClone = new TextPaint(textPaint);
        textPaintClone.setTextSize(TextSize);//textPaintClone.measureText(text) > avaiWidth && trySize > minTextSize

        float g =  avaiWidth / textPaintClone.measureText(Text) ;
        if(g < 1)
            TextSize *= g;

        invalidate();
    }

    public float[] setConfigforAnm(float extension , float emollescence )
    {
        if(extension>1 || extension <0)
            extension = DEFULT_EXTENSION;
        if(emollescence>1 || emollescence<0)
            emollescence = DEFULT_EMOLLESCENCE;

        final int EMOLLESCENCE_BASE = 3;
        final int EXTENSION_BASE = 1;
        final float GRANULARUTY = 0.88f;
        final float RateStart = 0.552f;
        final float TEST_RATE = 1;
        final float R = r;

        float VxtoVy = (2 * emollescence - 1) * EMOLLESCENCE_BASE ;
        float YtoX = (2 * extension - 1) * EXTENSION_BASE;

        float Vx = ((2 * emollescence - 1) * mWeith / 2 - r)*0.9f;//0.9：百分比运动时最大横向占比
        float Y = r * GRANULARUTY;

        float config[] = new float[7];
        config[0] = TEST_RATE;
        config[1] = VxtoVy;
        config[2] = YtoX;
        config[3] = RateStart;
        config[4] = R ;
        config[5] = Vx;
        config[6] = Y;
        return config;
    }

    private void setpoint(float g/*旋转角度,未使用*/,float time ,float[] config){
        //g=0;
        //float TEST_RATE = config[0];
        float Y = config[6];
        float X = Y/config[2];
        float Vx = config[5];
        float Vy = Vx/config[1];
        float RateStart = config[3];
        float R = config[4];

        point0.set((int)(R + time * Vx),0);
        point1.set(point0.x, (int) (RateStart*R + Vy*time));
        point2.set((int)(R * RateStart + time * X),(int)(R - Y * time));
        point3.set(0,  point2.y);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWeith = MeasureSpec.getSize(widthMeasureSpec);

        r = (float) (mHeight * 0.5);

       // Outline outline = new Outline();
        //outline.setRoundRect(0,0,mWeith,mHeight);
        PointsConfig = setConfigforAnm(1,1);
    }

    @Override
    public ViewOutlineProvider getOutlineProvider() {
        return super.getOutlineProvider();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMapMatrix.reset();

        mHeight = h;
        mWeith = w;

        r = (float) (mHeight*0.5);

        PathandRegionInit();

        textStartX =(w-textPaint.measureText(Text)) * 0.5f;
        textStartY = (r-(textPaint.getFontMetrics().bottom+textPaint.getFontMetrics().top)*0.5f);

        adjustTextSize();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if (mMapMatrix.isIdentity()) {
//            canvas.getMatrix().invert(mMapMatrix);
//        }
        if(isTouch)
            buttonColor = ButtonPressColor;
        else
            buttonColor = ButtonNormalColor;

        buttonPaint.setColor(buttonColor);
        textPaint.setColor(TextColor);

        if(CurState != State.DOINGTASK)
            drawButton(canvas,AnmValue);
        else
            drawLoadingAnm(canvas,AnmValue);
    }

    private void drawLoadingAnm(Canvas canvas,float anmValue)
    {
        canvas.save();
        canvas.translate(mWeith*0.5f,r);
        buttonPath.reset();
        setpoint(0,anmValue,PointsConfig);
        buttonPath.moveTo(point0.x,point0.y);
        buttonPath.cubicTo(point1.x,point1.y,point2.x,point2.y,point3.x,point3.y);
        buttonPath.cubicTo(-point2.x,point2.y,-point1.x,point1.y,-point0.x,point0.y);
        buttonPath.cubicTo(-point1.x,-point1.y,-point2.x,-point2.y,-point3.x,-point3.y);
        buttonPath.cubicTo(point2.x,-point2.y,point1.x,-point1.y,point0.x,-point0.y);
        buttonPath.close();
        canvas.drawPath(buttonPath,buttonPaint);
        canvas.restore();
        //invalidate();
    }

    private void drawButton(Canvas canvas,float factor)
    {
        buttonPath.reset();
        buttonPath.addRoundRect((mWeith-mHeight)*(1-factor)/2,0,(mWeith/2+(mWeith-mHeight)*factor/2)+r,mHeight,r,r, Path.Direction.CW);
        //buttonPaint.setShadowLayer(5,5,5,Color.BLACK);
        canvas.drawPath(buttonPath,buttonPaint);
        if(CurState == State.NORMAL)
        {
            drawText(canvas);
        }
    }

    private void drawText(Canvas canvas)
    {
        if(textPaint.getTextSize() != TextSize || textChanged ) {
            textPaint.setTextSize(TextSize);
            textStartX =(mWeith-textPaint.measureText(Text))*0.5f;
            textStartY = (r-(textPaint.getFontMetrics().bottom+textPaint.getFontMetrics().top)*0.5f);
            textChanged = false;
        }
        canvas.drawText(Text,textStartX,textStartY,textPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float[] pts = new float[2];
        pts[0] = event.getX();
        pts[1] = event.getY();
        //mMapMatrix.mapPoints(pts);

        int x = (int) pts[0];
        int y = (int) pts[1];

        //Log.d("onTouchEvent",x + "," +y );

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if(buttonReg.contains(x,y) && CurState == State.NORMAL){
                    isTouch = true;
                }else
                {
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(!buttonReg.contains(x,y)) {
                    isTouch = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                // 如果手指按下区域和抬起区域相同且不为空，则判断点击事件
                if (isTouch && buttonReg.contains(x,y)) {
                    CurState = State.NORMAL;
                    AnmHandler.sendEmptyMessage(0);
                    
                    if(null != mListener) {
                        mListener.OnClicked(this);
                    }
                    
                }
                isTouch = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                isTouch = false;
                break;
        }
        invalidate();
        return true;
    }


    public void setTaskButtonOnClickListener(TaskButtonListener taskButtonListener)
    {
        if(mListener != taskButtonListener)
            mListener = taskButtonListener;
    }
    
    public void setTaskButtonEventListener(TaskButtonEventListener taskButtonEventListener)
    {
        if(mEventListener != taskButtonEventListener)
            mEventListener = taskButtonEventListener;
    }

    private static class mybuttontask extends Handler {

        private WeakReference<TaskButton> mTaskButtonReference;
        private boolean forceStop;
        private boolean waitStop;
        private boolean hasstop;

        private mybuttontask(TaskButton taskButton)
        {
            this.mTaskButtonReference = new WeakReference<>(taskButton);
            forceStop = waitStop = hasstop = false;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            TaskButton mTaskButton = mTaskButtonReference.get();
            if (mTaskButton != null) {
                if (msg.what == FORCE_STOP_ID) {
                    forceStop = true;
                    waitStop = false;
                    hasstop = false;
                } else if (msg.what == WAIT_STOP_ID) {
                    waitStop = true;
                    hasstop = true;
                }
                try {
                    switch (mTaskButton.CurState)//各个动画结束时
                    {
                        case NORMAL:
                            mTaskButton.taskNormal2Start();
                            break;
                        case TASKSTART:
                            mTaskButton.taskStart2Doing();
                            break;
                        case DOINGTASK:
                            if (forceStop)//如果外界要求停止动画
                            {
                                mTaskButton.taskForceStop();
                                forceStop = false;
                            } else if (waitStop) {
                                mTaskButton.taskStopAfterAnmStop();
                                waitStop = false;
                            } else {
                                //动画播放完毕，循环播放
                                mTaskButton.taskDoing2Finish(hasstop);
                                if (hasstop)
                                    hasstop = false;
                            }
                            break;
                        case TASKFINISH:
                            mTaskButton.CurState = State.NORMAL;
                            break;
                        default:
                            break;
                    }
                    mTaskButton.invalidate();
                }catch (NullPointerException e)
                {
                    this.removeCallbacksAndMessages(null);
                }
            }
        }
    }

    private void taskStart2Doing()
    {
        DoAnm.setRepeatCount(timeOut - 1);
        DoAnm.setRepeatMode(ValueAnimator.REVERSE);
        DoAnm.start();
        CurState = State.DOINGTASK;
        if (mEventListener != null)
            mEventListener.OnTaskStart(this);
    }

    private void taskNormal2Start()
    {
        StartAnm.start();
        CurState = State.TASKSTART;
    }

    private void taskForceStop()
    {
        DoAnm.pause();
        FinishAnm.start();
        CurState = State.TASKFINISH;
        if (mEventListener != null)
            mEventListener.OnTaskEnd(this, FORCE_STOP_ID);
    }

    private void taskStopAfterAnmStop()
    {
        DoAnm.setRepeatCount(0);
        if (mEventListener != null)
            mEventListener.OnTaskEnd(this, WAIT_STOP_ID);
    }

    private void taskDoing2Finish(boolean hasstop)
    {
        FinishAnm.start();
        CurState = State.TASKFINISH;
        if (mEventListener != null && !hasstop)
            mEventListener.OnTaskEnd(TaskButton.this, NORMAL_STOP_ID);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnmHandler.removeCallbacksAndMessages(null);
    }

    private interface TaskButtonListener{
        void OnClicked(View view);
    }
    public interface TaskButtonEventListener{
        void OnTaskStart(View view);
        void OnTaskEnd(View view,int type);
    }
}
