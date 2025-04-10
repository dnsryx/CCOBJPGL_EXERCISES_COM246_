public class HybridPickup extends HybridVehicle{
 
    public HybridPickup(String carName) {
        super(carName);
    }
 
    public void disfuelling(){
        System.out.print("tong  " + carName + " ay ");
        fillGas();
    }
    public void disCharging(){
        System.out.print("tong " + carName + " ay ");
        chargeBattery();
    }
}
 