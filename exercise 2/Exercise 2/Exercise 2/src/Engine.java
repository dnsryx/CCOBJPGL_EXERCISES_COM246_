interface  Engine {
    
    protected boolean gas;
    protected boolean electric;

    public boolean  Getgas(){
        return this.gas;
    }
    public boolean Getelectric(){
        return this.electric;
    }

    interface electric extends Engine {

        boolean electric = true;
        int battery = 10;

    }
    interface InternalCombustion{

    
        boolean gas = true;
        int cylinders = 10;

    
    }
    interface  hybrid extends Electric,InternalCombustion{
    }
    public class car
}
