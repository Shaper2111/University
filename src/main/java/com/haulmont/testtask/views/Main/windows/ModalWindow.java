package com.haulmont.testtask.views.Main.windows;

import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public abstract class ModalWindow extends Window {

    protected final ModalWindowDesign design = new ModalWindowDesign();

    protected abstract void OkButtonClick();

    private void CancelButtonClick(){
        this.close();
    }

    protected ModalWindow(String caption){
        super(caption);
    }

    protected void setWindow(){
        setClosable(false);
        setResizable(false);
        setModal(true);

        design.okButton.addClickListener((Button.ClickListener) (event) -> OkButtonClick());
        design.cancelButton.addClickListener((Button.ClickListener) (event) -> CancelButtonClick());

        setContent(design);
        UI.getCurrent().addWindow(this);
    }
}
