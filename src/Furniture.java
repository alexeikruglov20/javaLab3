public class Furniture implements Equipment{
    private long number;
    private String name;
    private EquipmentTypes type;

    public static final String FURNITURE = "Furniture";

    public Furniture(long number){
        this(number,FURNITURE);
    }

    public Furniture(long number,String name){
        setNumber(number);
        setName(name);
    }

    @Override
    public long getNumber() {
        return number;
    }

    @Override
    public void setNumber(long number) {
        this.number = number;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public EquipmentTypes getType() {
        return type;
    }

    public void setType(EquipmentTypes type) {
        this.type = type;
    }
}
