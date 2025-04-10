public class HybridSedan extends HybridVehicle{
 
    public HybridSedan(String carName) {
        super(carName);
    }
    public void disCharging(){
        System.out.print("tong " + carName + " ay ");
        chargeBattery();
    }
 
    public void disfuelling(){
        System.out.print("tong " + carName + " ay ");
        fillGas();
    }
}