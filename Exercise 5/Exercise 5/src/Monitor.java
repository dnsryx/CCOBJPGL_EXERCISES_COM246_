public class Monitor {
    public void connect(VgaConnector connector) {
        System.out.println("Needed Connector: VGA Connector");
        
        connector.connectUsingVgaConnector();
       
        System.out.println("Monitor is using a VGA Connector.");
    }
 }