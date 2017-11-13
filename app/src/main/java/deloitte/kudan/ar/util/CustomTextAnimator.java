package deloitte.kudan.ar.util;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

/**
 * Created by myunuskabir on 11/13/2017.
 */

public class CustomTextAnimator extends Animation{
    TextView blobText;
    public String[] text = new String[] { "" };
    public int position = 0;
    Animation fadeiInAnimationObject;
    Animation textDisplayAnimationObject;
    Animation delayBetweenAnimations;
    Animation fadeOutAnimationObject;
    int fadeEffectDuration;
    int delayDuration;
    int displayFor;

    public CustomTextAnimator(TextView textV, String[] textList)
    {
        this(textV,700,1000,2000, textList);
    }
    public CustomTextAnimator(TextView textView, int fadeEffectDuration, int delayDuration, int displayLength, String[] textList)
    {
        blobText = textView;
        text = textList;
        this.fadeEffectDuration = fadeEffectDuration;
        this.delayDuration = delayDuration;
        this.displayFor = displayLength;
        InitializeAnimation();
    }

    private void InitializeAnimation()
    {
        fadeiInAnimationObject = new AlphaAnimation(0f, 1f);
        fadeiInAnimationObject.setDuration(fadeEffectDuration);
        textDisplayAnimationObject = new AlphaAnimation(1f, 1f);
        textDisplayAnimationObject.setDuration(displayFor);
        delayBetweenAnimations = new AlphaAnimation(0f, 0f);
        delayBetweenAnimations.setDuration(delayDuration);
        fadeOutAnimationObject = new AlphaAnimation(1f, 0f);
        fadeOutAnimationObject.setDuration(fadeEffectDuration);

        fadeiInAnimationObject.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //position++;
                if(position>=text.length)
                {
                    position = 0;
                }
                blobText.setText(text[position]);
                position++;
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                blobText.startAnimation(textDisplayAnimationObject);
            }
        });
        textDisplayAnimationObject.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                if(position>=text.length)
                {
                    return;
                }
                blobText.startAnimation(fadeOutAnimationObject);
            }
        });
        fadeOutAnimationObject.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                blobText.startAnimation(delayBetweenAnimations);
            }
        });
        delayBetweenAnimations.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                blobText.startAnimation(fadeiInAnimationObject);
            }
        });
    }

    public void startAnimation()
    {
        blobText.startAnimation(fadeiInAnimationObject);
        //blobText.startAnimation(fadeOutAnimationObject);
    }
}
