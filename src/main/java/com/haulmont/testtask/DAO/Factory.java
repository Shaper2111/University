/*
 * %W% %E% Firstname Lastname
 *
 * Copyright (c) 2016.
 *
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.haulmont.testtask.DAO;

class Factory implements IFactory {

    @Override
    public IStudentDao getStudentDao() {
        return null;
    }

    @Override
    public IGroupDao getGroupDao() {
        return null;
    }
}
