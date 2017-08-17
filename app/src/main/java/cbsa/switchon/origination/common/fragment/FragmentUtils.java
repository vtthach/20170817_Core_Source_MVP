package cbsa.switchon.origination.common.fragment;

import android.support.v4.app.Fragment;

public class FragmentUtils {
    private FragmentUtils() {
        // Private cons
    }

    public static <T extends Fragment> String getDefaultFragmentTag(Class<T> clazz) {
        return clazz.getName();
    }
}
