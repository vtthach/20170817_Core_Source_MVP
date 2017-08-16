package cbsa.switchon.origination.common.widget.edittext;

import android.text.InputFilter;
import android.text.Spanned;


public class RegexCharacterFilter implements InputFilter {


    private final String regex;
    private final InvalidRegexCallback callback;

    public RegexCharacterFilter(String regex, InvalidRegexCallback callback) {
        this.regex = regex;
        this.callback = callback;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceStr = source.toString();
        if (isAllow(sourceStr)) {
            return null;
        } else {
            if (callback != null) {
                callback.onInputInvalid(sourceStr);
            }
            return ""; // Return empty if in case not allow
        }
    }

    private boolean isAllow(String sourceStr) {
        return sourceStr.replaceAll(regex, "").isEmpty();
    }

    public interface InvalidRegexCallback {
        void onInputInvalid(String inputWrong);
    }
}
