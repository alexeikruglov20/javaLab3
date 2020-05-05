import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class EquipmentManager extends DepartmentManager {

    public EquipmentManager(){
        super();
    }

    public EquipmentManager(int size){
        super(size);
    }

    public  EquipmentManager(Department[] departments){
        super(departments);
    }

    public Department getDepartmentWithEquipment(long number){
        for(Department dep:getDepartments()){
            if(dep.get(number)!=null) return dep;
        }
        return null;
    }

    public Equipment set(Equipment equipment,long number){
        for(Department dep:getDepartments()){
            if(dep.get(number)!=null) {
                dep.add(equipment);
                return dep.remove(number);
            }
        }
        return null;
    }

    public Equipment remove(long number){
        for(Department dep:getDepartments()){
            Equipment buf = dep.remove(number);
            if(buf!=null) return buf;
        }
        return null;
    }

    public Equipment[] getTypedEquipments(EquipmentTypes type){
        ArrayList<Equipment> buf = new ArrayList<>();
        for(Department dep:getDepartments()){
            Equipment[] tmp = dep.getTypedEquipments(type);
            if(tmp!=null){
                buf.addAll(Arrays.asList(tmp));
            }
        }
        return (Equipment[]) buf.toArray();
    }
}
