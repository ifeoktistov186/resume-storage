package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{


    /*
    * add new resume to the end of the array,
    * if resume already exist, write message about it.
    * check empty space for adding new resume.
    */
    public void save(Resume r) {
        if(!isFull()) {
            if(getIndex(r.getUuid()) == -1) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Resume already in storage");
            }
        } else {
            System.out.println("Storage is full, can't save resume");
        }

    }

    /*
    * replace existed resume.
    * if resume not found write message "Resume not found".
    */
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if(index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Resume not found");
        }
    }

    /*
    * finding resume by uuid,
    * return null-resume if not found.
    */


    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index == -1) {
            System.out.println("Resume not found");
        } else if((index == size -1)) {
            storage[index] = null;
            size--;
        } else {
            storage[index] = storage[size -1];
            storage[size -1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    boolean isFull() {
        return (size == storage.length);
    }

    boolean isEmpty() {
        return (size == 0);
    }

    protected int getIndex(String uuid) {
        int position = -1; // if resume not found return -1
        if (!isEmpty()) {
            for(int i = 0; i< size; i++) {
                if(storage[i].getUuid().equals(uuid)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }
}
