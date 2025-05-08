public class App {
    public static void main(String[] args) throws Exception {
        HdmiConnector newConnector = new HdmiConnector();
        
        VgaConnector adapter = new HdmiToVgaAdapter(newConnector);
       
       
        Monitor monitor = new Monitor();
        monitor.connect(adapter);
}
}