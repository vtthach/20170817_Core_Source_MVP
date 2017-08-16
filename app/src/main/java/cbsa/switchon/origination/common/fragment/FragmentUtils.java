package cbsa.switchon.origination.common.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class FragmentUtils {
    public static <T extends Fragment> T getFragmentByClassTag(Class<T> clazz, FragmentManager fragmentManager) {
        return (T) fragmentManager.findFragmentByTag(getDefaultFragmentTag(clazz));
    }

    public static <T extends Fragment> String getDefaultFragmentTag(Class<T> clazz) {
        return clazz.getName();
    }
}
