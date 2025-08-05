package frc.Demacia.Sysid;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;



public class Sysid implements Consumer<File> {
    JFrame frame = new JFrame("Sysid");
    FileChooserPanel fileChooser = new FileChooserPanel(this);
    JList<LogEentryHirerchy> motorList = new JList<>();
    JList<LogDataEntry> motorListNew = new JList<>();
    SysidResultPanel result = new SysidResultPanel(this);
    JTextArea msgArea = new JTextArea();
    JScrollPane msgPane = new JScrollPane(msgArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    LogReader log;

    static boolean newLog = true;

    private static Sysid sysid = null;

    
    
    public Sysid() {
        sysid = this;
        frame.setSize(1024,800);
        frame.setMinimumSize(new Dimension(1024,800));
        frame.setLocation(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var pane = frame.getContentPane();
        pane.setLayout(new GridBagLayout());
        motorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        motorList.setMinimumSize(new Dimension(300,400));
        motorList.setBorder(BorderFactory.createEtchedBorder());
        pane.add(fileChooser, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 0), 5, 5));
        if(newLog) {
            pane.add(motorListNew, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 0), 5, 5));
        } else {
            pane.add(motorList, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 0), 5, 5));
        }
        pane.add(msgPane, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 0), 5, 5));
        pane.add(result, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 0), 5, 5));
        frame.pack();
    }

    private void show() {
        frame.setVisible(true);
    }

    public static void msg(String msg) {
        if(sysid != null) {
            sysid.msgArea.append(msg + "\n");
        }
    }

    @Override
    public void accept(File file) {
        System.out.println(" file set to " + file);
        try {
            log = new LogReader(file.getAbsolutePath());
            if(newLog) {
                for(LogDataEntry m : MotorDataNew.motors) {
                    m.getMotorData();
                }
                motorListNew.setListData(MotorDataNew.motors);
            } else {
                var motors = log.motors();
                motorList.setListData(motors);
            }
            msg("file " + file.getName() + " loaded");
        } catch (IOException e) {
            msg(" IO error - for file " + file + " error=" + e);
            fileChooser.field.setText("");
        }

    }

    public MotorData getMotor() {
        if(newLog) {
            var s = motorListNew.getSelectedValue();
            if(s != null) {
                return s.motorData;
            }
            return null;
        } else {
            var s = motorList.getSelectedValue();
            if(s != null) {
                return s.motorData;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        new Sysid();
        Sysid app = new Sysid();
        app.show();        
    }
}
