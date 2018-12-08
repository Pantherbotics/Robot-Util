package frc.team3863.robot.subsystems;


import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SerialPort;

public class Vision extends Subsystem {

    private SerialPort jevois;
    private Notifier stream;
    private boolean tx;
    private String txMessage, rxMessage;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Vision(int baud, SerialPort.Port port){
        jevois = new SerialPort(baud, port);
        stream = new Notifier(() -> {
            if(tx){
                jevois.writeString(txMessage);
            }
            else if(jevois.getBytesReceived() > 0){
                rxMessage = jevois.readString();
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

