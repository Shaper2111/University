package com.haulmont.testtask.views.Group;

import com.vaadin.navigator.View;

interface IGroupView extends View {
    void addListener(IGroupViewListener listener);
}
