package com.haulmont.testtask.views.Main.windows;

import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * Basic modal window, that holds design, settings and buttons.
 *
 * @version 1.0.0 14.10.2016
 * @author Leonid Gubarkov
 */
public abstract class ModalWindow extends Window {

    protected final ModalWindowDesign design = new ModalWindowDesign();

    protected abstract void OkButtonClick(Button.ClickEvent event);

    private void CancelButtonClick(Button.ClickEvent event){
        this.close();
    }

    protected ModalWindow(String caption){
        super(caption);
    }

    protected void setWindow(){
        setClosable(false);
        setResizable(false);
        setModal(true);

        design.okButton.addClickListener(this::OkButtonClick);
        design.cancelButton.addClickListener(this::CancelButtonClick);

        setContent(design);
        UI.getCurrent().addWindow(this);
    }
}
