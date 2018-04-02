package com.igap.common.widget.materialdialog;

public class ButtonTittleCallback extends StubPositiveNegativeButtonClick {
    public String posTitle, negTittle;

    public ButtonTittleCallback(String posTitle, String negTittle) {
        this.posTitle = posTitle;
        this.negTittle = negTittle;
    }

    public ButtonTittleCallback(String posTitle, String negTittle, boolean posAutoHide, boolean negAutoHide) {
        super(posAutoHide, negAutoHide);
        this.posTitle = posTitle;
        this.negTittle = negTittle;
    }


}
