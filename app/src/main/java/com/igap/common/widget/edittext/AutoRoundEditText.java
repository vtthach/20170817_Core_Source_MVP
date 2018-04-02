package com.igap.common.widget.edittext;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by vtthach on 7/22/2017.
 */

public class AutoRoundEditText extends android.support.v7.widget.AppCompatEditText {
    private AutoRoundCallback callback;

    DecimalFormat decimalFormatFloat = new DecimalFormat("#,###.##");

    public AutoRoundEditText(Context context) {
        super(context);
    }

    public AutoRoundEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoRoundEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        addTextChangedListener(autoRoundTextChangedListener);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(runnableAutoRoundInputValue);
        removeTextChangedListener(autoRoundTextChangedListener);
    }

    public final Runnable runnableAutoRoundInputValue = new Runnable() {
        @Override
        public void run() {
            if (isAttachedToWindow()) {
                removeTextChangedListener(autoRoundTextChangedListener);
                // Auto round
                String text = getText().toString();
                double value = getDoubleValue(text);
                // Round half up value
                String autoRoundValue = getRoundValue(value);
                // Store auto round value in StepManager
                notifyToCallback(autoRoundValue);
                // Auto add comma to display to user
                String valueWithFormatSeparator = autoFormatWithSeparator(autoRoundValue);
                setText(valueWithFormatSeparator);
                // Restore cursor position
                setSelection(getText().length());
                addTextChangedListener(autoRoundTextChangedListener);
            }
        }

        private String getRoundValue(double value) {
            BigDecimal bigDecimal = BigDecimal.valueOf(value);
            return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }

        private String autoFormatWithSeparator(String value) {
            String rs = value;
            if (value != null && !value.equals("")) {
                if (value.startsWith(".")) { //adds "0." when only "." is pressed on begining of writting
                    rs = "0." + value;
                } else if (value.startsWith("0") && !value.startsWith("0.") && value.length() > 2) {
                    rs = rs.substring(1); //Prevents "0" while starting but not "0."
                }
                rs = generateDecimalFormat(getDoubleValue(rs)); // To generat 5,54545.34
            }
            return rs;
        }

        private String generateDecimalFormat(double rs) {
            return decimalFormatFloat.format(rs);
        }

        private double getDoubleValue(String input) {
            int firstIndexFloat = input.indexOf('.');
            String temFinalPart = "";
            String secondPart = input;
            if (firstIndexFloat != -1 && firstIndexFloat != input.length() - 1) {
                temFinalPart = input.substring(0, firstIndexFloat + 1);
                temFinalPart = temFinalPart.replaceAll(",", "");
                secondPart = input.substring(firstIndexFloat);
            }
            secondPart = secondPart.replaceAll("[,.]", "");
            temFinalPart += secondPart;
            if (temFinalPart.isEmpty()) {
                return 0;
            }
            try {
                return Double.valueOf(temFinalPart);
            } catch (NumberFormatException ignore) {
                return 0;
            }
        }
    };

    private void notifyToCallback(String autoRoundValue) {
        if (callback != null) {
            callback.onFormatComplete(autoRoundValue);
        }
    }

    private TextWatcher autoRoundTextChangedListener = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            removeCallbacks(runnableAutoRoundInputValue);
            postDelayed(runnableAutoRoundInputValue, getTimeDelay(s.toString(), start, count));
        }

        private long getTimeDelay(String input, int start, int count) {
            return 2500;
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void forceRun() {
        runnableAutoRoundInputValue.run();
    }

    public interface AutoRoundCallback {
        void onFormatComplete(String value);
    }

    public void setCallback(AutoRoundCallback callback){
        this.callback = callback;
    }
}
