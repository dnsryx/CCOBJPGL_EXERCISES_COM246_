public class HdmiToVgaAdapter implements VgaConnector {
    private HdmiConnector hdmiConnector;
    public HdmiToVgaAdapter(HdmiConnector hdmiConnector) {
        this.hdmiConnector = hdmiConnector;
    }
    
    @Override
    public void connectUsingVgaConnector() {
        System.out.println("Adapter converts HDMI to VGA...");
        hdmiConnector.connectUsingHdmi();
    }
 }