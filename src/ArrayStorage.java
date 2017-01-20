/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int capacity = 10000;
    Resume[] storage = new Resume[capacity];
    int lastEl = 0;

    //override not null cells of array
    void clear() {
        System.arraycopy(storage,0,new Resume[lastEl],0,lastEl);
        lastEl = 0;
    }

    //it's not good, because used more ram
    void clearNewArray() {
        storage = new Resume[capacity];
        lastEl = 0;
    }

    /*
    * add new resume to the end of the array,
    * if resume already exist, write message about it.
    * check empty space for adding new resume.
    */
    void save(Resume r) {
        if(!isFull()) {
            String uuid = r.getUuid();
            if(findPosition(r.getUuid()) == -1) {
                storage[lastEl] = r;
                lastEl++;
            } else {
                System.out.println("Resume already in storage");
            }
        } else {
            System.out.println("Storage is full, can't save resume");
        }

    }

    /*
    * finding resume by uuid,
    * return null-resume if not found.
    */
    Resume get(String uuid) {
        Resume result = new Resume();
        for (Resume resume : storage) {
            if (resume != null) {
                if (resume.getUuid().equals(uuid)) {
                    result = resume;
                    break;
                }
            } else {
                break;
            }
        }
        return result;
    }

    /*
    * delete resume from storage by shifted array's tail to the left at one cell.
    */
    void delete(String uuid) {
        if (isEmpty()) {
            return;
        }
        int position = findPosition(uuid);
        if((position == lastEl-1)) {
            storage[position] = null;
            lastEl--;
        } else if(position < 0) {
            System.out.println("Resume not found"); //will we use some notification if resume not found?
        } else {
            System.arraycopy(storage, position+1, storage, position, lastEl-position-1);
            lastEl--;
        }
    }

    void deleteThroughReplace(String uuid) {
        int position = findPosition(uuid);
        if (isEmpty()) {
            return;
        }
        if((position == lastEl-1)) {
            storage[position] = null;
            lastEl--;
        } else if(position < 0) {
            System.out.println("Resume not found"); //will we use some notification if resume not found?
        } else {
            storage[position] = storage[lastEl-1];
            storage[lastEl-1] = null;
            lastEl--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[lastEl];
        System.arraycopy(storage,0,result,0, lastEl);
        return result;
    }

    int size() {
        return lastEl;
    }

    boolean isFull() {
        return (lastEl == storage.length);
    }

    boolean isEmpty() {
        return (lastEl == 0);
    }


    int findPosition(String uuid) {
        int position = -1; // if resume not found return -1
        if (!isEmpty()) {
            for(int i=0; i<lastEl; i++) {
                if(storage[i].getUuid().equals(uuid)) {
                    position = i;
                    break;
                }
            }
        }
        return position;
    }
}
