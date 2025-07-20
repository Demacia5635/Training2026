package frc.Demacia.Sysid;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frc.Demacia.Sysid.SysidCalculate.KTypes;
import frc.Demacia.Sysid.SysidCalculate.VelocityRange;

public class SysidResultPanel extends JPanel {
    public static final String[] K_NAMES = {"kS", "kV", "kA", "kG-Elevator", "kG-Arm", "kV2", "kSqrt"};
    public static int nK = K_NAMES.length;
    JCheckBox[] checkBoxes;
    JLabel[][] k;
    JButton applyButton, resetButton;
    JLabel[] velLabels = {new JLabel("Velocity Range"), new JLabel("     0%   -   30%"), new JLabel("  30%    -  70%"), new JLabel("     70% - 100%")};
    JLabel[] countLabels = {new JLabel("Number of records"), new JLabel("             0"), new JLabel("            0"), new JLabel("                   0")};
    JLabel[] avgErrorLabels = {new JLabel("Average Error %"), new JLabel("             0"), new JLabel("            0"), new JLabel("                   0")};
    JLabel[] maxErrorLabels = {new JLabel("Max Error %"), new JLabel("             0"), new JLabel("            0"), new JLabel("                   0")};
    Sysid app;

    public SysidResultPanel(Sysid app) {
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
        add(applyButton);

        // Add button action listeners
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogEentryHirerchy motor = app.getMotor();
                if(motor != null) {
                    MotorData motorData = motor.getMotorData();
                    SysidCalculate calculate = new SysidCalculate(motorData, checkBoxes[3].isSelected(), checkBoxes[4].isSelected(), checkBoxes[5].isSelected(), checkBoxes[6].isSelected());
                    velLabels[1].setText(String.format("0 - %4.2f",calculate.getRange(VelocityRange.SLOW)));
                    velLabels[2].setText(String.format("%4.2f - %4.2f",calculate.getRange(VelocityRange.SLOW),calculate.getRange(VelocityRange.MID)));
                    velLabels[3].setText(String.format("%4.2f - %4.2f",calculate.getRange(VelocityRange.MID),calculate.getRange(VelocityRange.HIGH)));

                    for(VelocityRange range: VelocityRange.values()) {
                        int i = range.ordinal() + 1;
                        countLabels[i].setText(Integer.toString(calculate.getCount(range)));
                        avgErrorLabels[i].setText(String.format("%4.2f%%", calculate.getAverageError(range)));
                        maxErrorLabels[i].setText(String.format("%4.2f%%", calculate.getMaxError(range)));
                        i--;
                        for(KTypes type : KTypes.values()) {
                            k[type.ordinal()][i].setText(String.format("%7.5f", calculate.getK(type, range)));
                        }
                    }
                    
                }
            }
        });
    }
}
