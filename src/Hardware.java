public class Hardware implements Equipment{
    private long number;
    private String name;
    private EquipmentTypes type;

    public static final String HARDWARE = "Hardware";

    public Hardware(long number){
        this(number,HARDWARE);
    }

    public Hardware(long number,String name){
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
