public class Hybrid implements Electric, InternalCombustion{
 
    @Override
    public void chargeBattery() {
        System.out.println("lowbat na to charge nyo na");
    }
 
    @Override
    public void fillGas() {
        System.out.println("mag hintay ka nag pa-pa gas ako");
       
    }
}
 