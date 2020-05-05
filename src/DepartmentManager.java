public class DepartmentManager {
    private Department[] departments;
    private int size;

    public DepartmentManager(){
        departments = new Department[16];
        size = 0;
    }

    public DepartmentManager(int size){
        departments = new Department[size];
        this.size = 0;
    }

    public DepartmentManager(Department[] departments) {
        this.departments = departments;
        this.size = departments.length;
    }

    public boolean add(Department dep){
        if(hasSpace()){
            lockAdd(dep);
            return true;
        }else{
            extend();
            return false;
        }
    }

    public boolean insert(Department dep,int index){
        if(hasSpace()){
            for(int i = departments.length-1; i > index; i++){
                Department C = departments[i];
                departments[i] = departments[i-1];
                departments[i-1] = C;
            }
            departments[index] = dep;
            size++;
            return true;
        }else{
            return false;
        }
    }

    public Department get(int index){
        return departments[index];
    }

    public Department get(String name){
        for(int i = 0; i<departments.length;i++){
            if(departments[i].getName().equals(name)){
                return departments[i];
            }
        }
        return null;
    }

    public Department set(Department dep, int index){
        Department buf = departments[index];
        departments[index] = dep;
        if(dep != null && departments[index] == null) size++;
        if(dep == null && departments[index] != null) size--;
        return buf;
    }

    public Department delete(int index){
        Department buf = departments[index];
        departments[index] = null;
        trim();
        return departments[index];
    }

    public Department delete(String name){
        for(int i = 0; i<departments.length;i++){
            if(departments[i].getName().equals(name)){
                Department buf = departments[i];
                departments[i] = null;
                trim();
                return buf;
            }
        }
        return null;
    }

    private void extend(){
        Department[] deps = new Department[departments.length*2];
        for(int i = 0; i<departments.length;i++){
            deps[i] = departments[i];
        }
        departments = deps;
    }

    private void lockAdd(Department dep){
        for(int i = 0; i<departments.length;i++){
            if(departments[i]==null){
                departments[i]=dep;
                return;
            }
        }
        size++;
    }

    private boolean hasSpace(){
        if(size == departments.length) return false;
        return true;
    }

    private void trim(){
        for(int i = 0; i<departments.length;i++){
            for(int j = 0; j<departments.length-1;j++){
                if(departments[j]==null && departments[j+1]!=null){
                    Department C = departments[j];
                    departments[j] = departments[j+1];
                    departments[j+1] = C;
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Department[] getDepartments(){
        trim();
        Department[] deps = new Department[size];
        for(int i = 0; i<size;i++){
            deps[i] = departments[i];
        }
        return deps;
    }
}

