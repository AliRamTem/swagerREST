package ru.vtb.rama.swagerrest.model.base;

import java.io.Serializable;

public interface IDeleteEntity<T extends Serializable> extends IEntity<T> {

    boolean isDeleted();

    void setDeleted(boolean deleted);
}

