package es.unican.appriegospersonales.activities.apps.detail.tabs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import es.unican.appriesgospersonales.R;

public class CustomSeekBar extends androidx.appcompat.widget.AppCompatSeekBar {
    private Paint paint = new Paint();

    public CustomSeekBar(@NonNull Context context) {
        this(context, null);
    }

    public CustomSeekBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.R.attr.seekBarStyle);
    }

    public CustomSeekBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int progress = (int) (width * getProgress() / getMax());
        int borde = 14;

        paint.setColor(ContextCompat.getColor(getContext(), R.color.black));
        canvas.drawRect(0, borde, progress, height - borde, paint);

        paint.setColor(Color.argb(40, 150, 150, 150));
        canvas.drawRect(progress, borde, width, height - borde, paint);

        super.onDraw(canvas);
    }
}
