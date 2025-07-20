package frc.Demacia.Sysid;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frc.Demacia.Sysid.LogReader.entryHirerchy;

public class SysidResult extends JPanel {
    public static final String[] K_NAMES = {"kS", "kV", "kA", "kG-Elevator", "kG-Arm", "kV2", "kSqrt"};
    public static int nK = K_NAMES.length;
    JCheckBox[] checkBoxes;
    JLabel[][] k;
    JButton applyButton, resetButton;
    JLabel[] velLabels = {new JLabel("Velocity Range"), new JLabel("     0%   -   30%"), new JLabel("  30%    -  70%"), new JLabel("     70% - 100%")};
    JLabel[] countLabels = {new JLabel("Number of data"), new JLabel("             0"), new JLabel("            0"), new JLabel("                   0")};
    JLabel[] avgErrorLabels = {new JLabel("Average Error"), new JLabel("             0"), new JLabel("            0"), new JLabel("                   0")};
    JLabel[] maxErrorLabels = {new JLabel("Max Error"), new JLabel("             0"), new JLabel("            0"), new JLabel("                   0")};
    Sysid app;

    public SysidResult(Sysid app) {
        super(new GridLayout(nK + 6, 4, 5, 5)); // Adjusted layout to fit new components
        this.app = app;
        for(JLabel l : velLabels) {
            l.setAlignmentX(1);
            add(l);
        }
        for(JLabel l : countLabels) {
            l.setAlignmentX(1);
            add(l);
        }
        for(JLabel l : avgErrorLabels) {
            l.setAlignmentX(1);
            add(l);
        }
        for(JLabel l : maxErrorLabels) {
            l.setAlignmentX(1);
            add(l);
        }
        checkBoxes = new JCheckBox[K_NAMES.length];
        k = new JLabel[nK][3];
        for(int i = 0; i < nK; i++) {
            for(int j = 0; j < 3; j++) {
                k[i][j] = new JLabel();
                k[i][j].setText("0");
            }
        }
        
        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = new JCheckBox(K_NAMES[i]);
            if(i < 3) {
                checkBoxes[i].setEnabled(false);
                checkBoxes[i].setSelected(true);
            } else {
                checkBoxes[i].setSelected(false);
            }
            add(checkBoxes[i]);
            for(int j = 0; j < 3; j++) {
                add(k[i][j]);
            }
        }

        // Add buttons
        applyButton = new JButton("Apply");
        resetButton = new JButton("Reset");
        add(applyButton);
        add(resetButton);

        // Add button action listeners
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entryHirerchy motor = app.getMotor();
                if(motor != null) {
                    MotorData data = motor.geMotorData();
                    double[][] res = (new SysidCalculate(data, checkBoxes[3].isSelected(), checkBoxes[4].isSelected(), checkBoxes[5].isSelected(), checkBoxes[6].isSelected())).result;
                    double maxV = data.maxVelocity();
                    velLabels[1].setText(String.format("0 - %4.2f",maxV * 0.3));
                    velLabels[2].setText(String.format("%4.2f - %4.2f",maxV * 0.3, maxV * 0.7));
                    velLabels[3].setText(String.format("%4.2f - %4.2f",maxV * 0.7, maxV));

                    for(int i = 0; i < 3; i++) {
                        countLabels[i+1].setText(Integer.toString((int)res[i][9]));
                        avgErrorLabels[i+1].setText(String.format("%4.2f%%", res[i][8]));
                        maxErrorLabels[i+1].setText(String.format("%4.2f%%", res[i][7]));
                        for(int j = 0; j < nK; j++) {
                            k[j][i].setText(String.format("%7.5f", res[i][j]));
                        }
                    }
                    
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle reset button click
                for (int i = 3; i < nK; i++) {
                    checkBoxes[i].setSelected(false); // Uncheck all checkboxes
                }
                System.out.println("Reset button clicked.");
            }
        });
    }
}
