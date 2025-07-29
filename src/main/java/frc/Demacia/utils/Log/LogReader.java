package frc.Demacia.utils.Log;

import java.io.IOException;

import edu.wpi.first.util.datalog.DataLogReader;
import edu.wpi.first.util.datalog.DataLogRecord;

public class LogReader {

    private DataLogReader reader;
    private String file;

    public LogReader(String file) throws IOException {
        this.file = file;
        try {
            reader = new DataLogReader(this.file);
        } catch (IOException e) {
            System.err.println(" Can not open Log File " + file + " error:" + e.getMessage());
            throw e;
        }
        reader.forEach(this::process);
    }

    private void process(DataLogRecord record) {
        if(record.isStart()) {
            var data = record.getStartData();
            System.out.println("start record - " + data.entry + " " + data.name + " " + data.metadata + " " + data.type);
        } else if(!record.isControl() && !record.isSetMetadata()) {
//            System.out.println(" process data for entry " + record.getEntry());
        }
    }


    public static void main(String[] args) {
        String file = "D:\\Projects\\wpilibLogAnalyzer\\FRC_20250113_153218.2.wpilog";
        try {
            new LogReader(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
