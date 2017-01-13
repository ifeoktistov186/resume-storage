/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int lastEl = 0;

    void clear() {
        for (int i = 0; i<= lastEl; i++) {
            System.arraycopy(storage,0,new Resume[lastEl],0,lastEl);
        }
        lastEl = 0;
    }

    void save(Resume r) {
        storage[lastEl] = r;
        lastEl++;
    }

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
        for(int i = 0; i < lastEl; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                System.arraycopy(storage,i+1,storage,i, lastEl -i);
                lastEl--;
            }
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
}
