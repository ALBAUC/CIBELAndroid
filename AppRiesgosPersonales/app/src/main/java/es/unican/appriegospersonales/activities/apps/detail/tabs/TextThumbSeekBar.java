package es.unican.appriegospersonales.activities.apps.detail.tabs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.SeekBar;

import androidx.core.content.ContextCompat;

import es.unican.appriesgospersonales.R;

public class TextThumbSeekBar extends androidx.appcompat.widget.AppCompatSeekBar {

    private int mThumbSize;
    private TextPaint mTextPaint;
    private Paint paint = new Paint();

    public TextThumbSeekBar(Context context) {
        this(context, null);
    }

    public TextThumbSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.seekBarStyle);
    }

    public TextThumbSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mThumbSize = getResources().getDimensionPixelSize(R.dimen.thumb_size);

        mTextPaint = new TextPaint();
        mTextPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.thumb_text_size));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        // Tramos de colores
        int width = getWidth();
        int height = getHeight();

        paint.setColor(ContextCompat.getColor(getContext(), R.color.ecoPoor));
        canvas.drawRect(0, 0, width / 4, height, paint);

        paint.setColor(ContextCompat.getColor(getContext(), R.color.ecoFair));
        canvas.drawRect(width / 4, 0, width / 4 * 2, height, paint);

        paint.setColor(ContextCompat.getColor(getContext(), R.color.ecoGood));
        canvas.drawRect(width / 4 * 2, 0, width / 4 * 3, height, paint);

        paint.setColor(ContextCompat.getColor(getContext(), R.color.ecoExcelent));
        canvas.drawRect(width / 4 * 3, 0, width, height, paint);

        super.onDraw(canvas);

        // Texto del thumb
        String progressText = String.valueOf(getProgress());
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(progressText, 0, progressText.length(), bounds);

        int leftPadding = getPaddingLeft() - getThumbOffset();
        int rightPadding = getPaddingRight() - getThumbOffset();
        int widthT = getWidth() - leftPadding - rightPadding;
        float progressRatio = (float) getProgress() / getMax();
        float thumbOffset = mThumbSize * (.5f - progressRatio);
        float thumbX = progressRatio * widthT + leftPadding + thumbOffset;
        float thumbY = getHeight() / 2f + bounds.height() / 2f;
        canvas.drawText(progressText, thumbX, thumbY, mTextPaint);
    }
}
