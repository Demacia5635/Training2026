package frc.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.vision.VisionPipeline;
import edu.wpi.first.vision.VisionThread;

public class MyVisionPipeline implements VisionPipeline {
    @Override
    public void process(Mat input) {
        // כאן יבוא האלגוריתם שלכם
        Mat output = new Mat();
        
        // דוגמה: המרה ל-HSV
        Imgproc.cvtColor(input, output, Imgproc.COLOR_BGR2HSV);
        
        // עיבוד נוסף...
        boolean targetFound = false;
        // שליחת תוצאות ל-NetworkTables
        NetworkTableInstance.getDefault()
            .getTable("vision")
            .getEntry("target_found")
            .setBoolean(targetFound);
    }

    public static void main(String[] args) {
        UsbCamera camera = CameraServer.startAutomaticCapture();
        camera.setExposureAuto();
        camera.setFPS(30);
        camera.setResolution(320,200);
        VisionThread visionThread = new VisionThread(camera, new MyVisionPipeline(), pipeline -> { });
        visionThread.setDaemon(true);
        visionThread.start();
    }
}
