package frc.team3863.robot.subsystems;


import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SerialPort;

public class Vision extends Subsystem  {

    private SerialPort jevois;
    private Notifier stream;
    private boolean tx, isJevoisConnected;
    private String txMessage, rxMessage;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Vision(int baud, SerialPort.Port port){
           isJevoisConnected = false;
            stream = new Notifier(() -> {
                try{
                    if(!isJevoisConnected){
                        jevois = new SerialPort(baud, port);
                        isJevoisConnected = true;
                    }
                    //do nothing if is already connected
                }
                catch(Exception e){
                    System.out.println("ERROR: JeVois is not connected!");
                    e.printStackTrace();
                    try{
                    Thread.sleep(5000);
                    }
                    catch(Exception f){
                        f.printStackTrace();
                    }
                }
                
                if(isJevoisConnected){
                    if(tx){
                        jevois.writeString(txMessage);
                    }
                    else if(jevois.getBytesReceived() > 0){
                        rxMessage = jevois.readString();
                    }
                }
            });
            stream.startPeriodic(0.005);
    }

    public void sendTxMessage(String message){
        txMessage = message + "\n";
        tx = true;
    }

    public String getRxMessage(){
        return rxMessage;
    }

    public void initDefaultCommand() {
        // TODO: Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }
}

