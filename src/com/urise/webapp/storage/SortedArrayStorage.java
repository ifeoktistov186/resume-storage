package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }
}