/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int capacity = 10000;
    Resume[] storage = new Resume[capacity];
    int size = 0;

    void clear() {
        storage = new Resume[capacity];
        size = 0;
    }

    /*
    * add new resume to the end of the array,
    * if resume already exist, write message about it.
    * check empty space for adding new resume.
    */
    void save(Resume r) {
        if(!isFull()) {
            String uuid = r.getUuid();
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

    void delete(String uuid) {
        int position = getIndex(uuid);
        if (isEmpty()) {
            return;
        }
        if((position == size -1)) {
            storage[position] = null;
            size--;
        } else if(position < 0) {
            System.out.println("Resume not found"); //will we use some notification if resume not found?
        } else {
            storage[position] = storage[size -1];
            storage[size -1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[size];
        System.arraycopy(storage,0,result,0, size);
        return result;
    }

    int size() {
        return size;
    }

    boolean isFull() {
        return (size == storage.length);
    }

    boolean isEmpty() {
        return (size == 0);
    }


    int getIndex(String uuid) {
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
