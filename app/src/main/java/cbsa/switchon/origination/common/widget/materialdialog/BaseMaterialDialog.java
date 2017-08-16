package cbsa.switchon.origination.common.widget.materialdialog;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.ButterKnife;

public class BaseMaterialDialog implements MaterialDialog.SingleButtonCallback {

    private final MaterialDialog mDialog;
    protected Activity mActivity;
    protected StubPositiveNegativeButtonClick positiveNegativeButtonClick;

    public BaseMaterialDialog(Activity activity, int layoutID, boolean isFullScreen) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity);
        if (layoutID != 0) {
            builder.customView(layoutID, false);
        }
        builder.onPositive(this);
        builder.onNegative(this);
        builder.onNeutral(this);
        mDialog = builder.build();
        mDialog.setCanceledOnTouchOutside(false);
        if (isFullScreen) {
            mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            mDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT);
            mDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mDialog.getWindow().setGravity(Gravity.CENTER);
        }
        mActivity = activity;
        View customView = mDialog.getCustomView();
        ButterKnife.bind(this, customView);
    }

    public BaseMaterialDialog(Activity activity) {
        this(activity, 0, false);
    }


    public MaterialDialog getDialog() {
        return mDialog;
    }


    public void showDialog() {
        if (!mDialog.isShowing() && activityAvailable()) {
            mDialog.show();
        }
    }

    private boolean activityAvailable() {
        return mActivity != null && !mActivity.isFinishing();
    }

    public void hideDialog() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public boolean isShow() {
        return mDialog.isShowing();
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        if (positiveNegativeButtonClick != null) {
            switch (which) {
                case POSITIVE:
                case NEUTRAL:
                    positiveNegativeButtonClick.onPositiveButtonClicked(dialog);
                    break;
                case NEGATIVE:
                    positiveNegativeButtonClick.onNegativeButtonClicked(dialog);
                    break;
            }
        }
    }

    public void setButtonClickListener(ButtonTittleCallback buttonClick) {
        if (buttonClick.posTitle != null) {
            mDialog.setActionButton(DialogAction.POSITIVE, buttonClick.posTitle);
        }
        if (buttonClick.negTittle != null) {
            mDialog.setActionButton(DialogAction.NEGATIVE, buttonClick.negTittle);
        }
    }

    public void setMessage(String msg) {
        mDialog.setContent(msg);
    }

    public void setTittle(String title) {
        title = title != null ? title : "";
        mDialog.setTitle(title);
    }
}
