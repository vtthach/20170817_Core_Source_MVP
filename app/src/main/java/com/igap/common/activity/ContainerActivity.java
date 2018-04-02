package com.igap.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.igap.R;
import com.igap.common.fragment.BaseFragment;

import static com.igap.application.Constants.UNUSED_VALUE;

/**
 * Container activity with menu_action_empty frame layout container to host a single fragment
 */
public class ContainerActivity extends BaseActivity {

    private static final String EXTRA_FRAGMENT_CLASS_NAME = "fragment_class_name";
    private static final String EXTRA_FRAGMENT_BUNDLE_ARGS = "fragment_bundle_args";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate fragment only in state create new not in restart(by change orientation, low memory, etc...)
        if (savedInstanceState == null) {
            hostFragment(getDefaultFragmentToHost());
        }
    }

    private Fragment getDefaultFragmentToHost() {
        Fragment attachedFragment = getAttachHostFragment();
        if (attachedFragment != null) {
            return attachedFragment;
        } else {
            return initFragmentHostFromIntent();
        }
    }

    protected Fragment getAttachHostFragment() {
        // Stub method to override
        return null;
    }

    protected void hostFragment(Fragment fragment) {
        if (fragment != null && getSupportFragmentManager().findFragmentByTag(fragment.getClass().getName()) == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.activity_fragment_container, fragment, fragment.getClass().getName());
            ft.commit();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_host;
    }

    private Fragment initFragmentHostFromIntent() {
        String className = getIntent().getStringExtra(EXTRA_FRAGMENT_CLASS_NAME);
        Bundle bundle = getIntent().getBundleExtra(EXTRA_FRAGMENT_BUNDLE_ARGS);
        return instantiateFragment(className, bundle);
    }

    /**
     * Init fragment to host
     *
     * @param fragmentClassName class name
     * @param data              bundle data
     */
    public Fragment instantiateFragment(String fragmentClassName, Bundle data) {
        if (fragmentClassName != null) {
            return Fragment.instantiate(this, fragmentClassName, data);
        } else {
            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static final class IntentBuilder {
        private Fragment fragment;
        private Activity activity;
        private Bundle data;
        private Class<? extends BaseFragment> fragmentClass;
        private int requestCode;
        private int flag;
        private boolean isFinishPreviousActivity;
        private int toolbarMode;
        private String toolbarTitle;

        /**
         * Instantiates a new Intent builder.
         *
         * @param activity the activity
         */
        public IntentBuilder(Activity activity) { // NOSONAR
            this.activity = activity;
        }

        /**
         * Instantiates a new Intent builder.
         *
         * @param fragment the activity
         */
        public IntentBuilder(Fragment fragment) { // NOSONAR
            this.fragment = fragment;
            this.activity = fragment.getActivity();
        }

        /**
         * Sets fragment class.
         *
         * @param fragmentClass the fragment class
         * @return the fragment class
         */
        public IntentBuilder setFragmentClass(Class<? extends BaseFragment> fragmentClass) {
            this.fragmentClass = fragmentClass;
            return this;
        }

        /**
         * Sets data.
         *
         * @param data the data
         * @return the data
         */
        public IntentBuilder setData(Bundle data) {
            this.data = data;
            return this;
        }

        /**
         * Sets request code.
         *
         * @param requestCode the request code
         * @return the request code
         */
        public IntentBuilder setRequestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        /**
         * Sets flag.
         *
         * @param flag the flag
         * @return the flag
         */
        public IntentBuilder setFlag(int flag) {
            this.flag = flag;
            return this;
        }

        /**
         * Sets finish previous activity.
         *
         * @param finishPrevious the finish previous
         * @return the finish previous activity
         */
        public IntentBuilder setFinishPreviousActivity(boolean finishPrevious) {
            isFinishPreviousActivity = finishPrevious;
            return this;
        }

        /**
         * Start.
         */
        public void start() {
            Intent intent = new Intent(activity, ContainerActivity.class);
            if (flag != UNUSED_VALUE) {
                intent.addFlags(flag);
            }
            intent.putExtra(EXTRA_FRAGMENT_CLASS_NAME, fragmentClass.getName());
            if (data != null) {
                intent.putExtra(EXTRA_FRAGMENT_BUNDLE_ARGS, data);
            }
            if (requestCode != UNUSED_VALUE) {
                if (fragment != null) {
                    fragment.startActivityForResult(intent, requestCode);
                } else {
                    activity.startActivityForResult(intent, requestCode);
                }
            } else {
                activity.startActivity(intent);
            }
            if (isFinishPreviousActivity) {
                activity.finish();
            }
        }
    }
}
