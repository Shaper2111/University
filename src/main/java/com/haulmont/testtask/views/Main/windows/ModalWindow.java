package com.haulmont.testtask.views.Main.windows;

import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

public class ModalWindow extends Window {


    public ModalWindow(String caption){
        super(caption);
        setWindow();
    }


    public ModalWindow(String caption, Component content){
        super(caption, content);
        setWindow();
    }

    private void setWindow(){
        setClosable(false);
        setResizable(false);
        setModal(true);
    }
}
