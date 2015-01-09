package ee.track.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class TrackView extends JFrame {
    private static final long serialVersionUID = 1982053004822801315L;
    private final JInternalFrame internalFrame = new JInternalFrame("Information view");

    public TrackView() throws PropertyVetoException {
        setTitle("TrackACar viewer");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmOpenList = new JMenuItem("Open list to track");
        mnFile.add(mntmOpenList);

        JMenuItem mntmExit = new JMenuItem("Open logs");
        mnFile.add(mntmExit);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Exit");
        mnFile.add(mntmNewMenuItem_2);

        JMenu mnTrackACar = new JMenu("TrackACar");
        menuBar.add(mnTrackACar);

        JMenuItem mntmDescription = new JMenuItem("Description");
        mnTrackACar.add(mntmDescription);

        JMenuItem mntmAbout = new JMenuItem("About");
        mnTrackACar.add(mntmAbout);
        getContentPane().setLayout(null);
        internalFrame.setClosable(true);
        internalFrame.setBounds(410, 11, 550, 240);
        getContentPane().add(internalFrame);
        internalFrame.getContentPane().setLayout(null);

        JTextPane textPane = new JTextPane();
        textPane.setBackground(SystemColor.control);
        textPane.setBounds(10, 11, 514, 189);
        internalFrame.getContentPane().add(textPane);

        JPanel panelSettings = new JPanel();
        panelSettings.setBounds(0, 0, 380, 40);
        getContentPane().add(panelSettings);
        panelSettings.setLayout(null);

        JLabel lblCheckFor = new JLabel("Check for:");
        lblCheckFor.setBounds(5, 5, 70, 20);
        panelSettings.add(lblCheckFor);
        lblCheckFor.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JRadioButton rdbtn10min = new JRadioButton("");
        rdbtn10min.setBounds(80, 5, 20, 20);
        panelSettings.add(rdbtn10min);

        JRadioButton rdbtn15min = new JRadioButton("");
        rdbtn15min.setSelected(true);
        rdbtn15min.setBounds(105, 5, 20, 20);
        panelSettings.add(rdbtn15min);

        JRadioButton rdbtn30min = new JRadioButton("");
        rdbtn30min.setBounds(130, 5, 20, 20);
        panelSettings.add(rdbtn30min);

        JRadioButton rdbtn60min = new JRadioButton("");
        rdbtn60min.setBounds(155, 5, 20, 20);
        panelSettings.add(rdbtn60min);

        JLabel lblInMinutes = new JLabel("(in minutes)");
        lblInMinutes.setBounds(8, 25, 70, 15);
        panelSettings.add(lblInMinutes);
        lblInMinutes.setFont(new Font("Tahoma", Font.PLAIN, 10));

        JLabel lbl10 = new JLabel("10");
        lbl10.setBounds(85, 25, 15, 15);
        panelSettings.add(lbl10);
        lbl10.setFont(new Font("Tahoma", Font.PLAIN, 10));

        JLabel lbl15 = new JLabel("15");
        lbl15.setBounds(110, 25, 15, 15);
        panelSettings.add(lbl15);
        lbl15.setFont(new Font("Tahoma", Font.PLAIN, 10));

        JLabel lbl30 = new JLabel("30");
        lbl30.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lbl30.setBounds(135, 25, 15, 15);
        panelSettings.add(lbl30);

        JLabel lbl60 = new JLabel("60");
        lbl60.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lbl60.setBounds(160, 25, 15, 15);
        panelSettings.add(lbl60);

        JButton btnStart = new JButton("Start");
        btnStart.setForeground(Color.BLACK);
        btnStart.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnStart.setBounds(180, 5, 90, 30);
        panelSettings.add(btnStart);

        JButton btnStop = new JButton("Stop");
        btnStop.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnStop.setEnabled(false);
        btnStop.setBounds(275, 5, 90, 30);
        panelSettings.add(btnStop);

        JLabel lblStatus1 = new JLabel("");
        lblStatus1.setBounds(400, 10, 560, 15);
        getContentPane().add(lblStatus1);

        JLabel lblStatus2 = new JLabel("");
        lblStatus2.setBounds(400, 30, 560, 15);
        getContentPane().add(lblStatus2);

        JLabel lblLogs = new JLabel("Logs:");
        lblLogs.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblLogs.setBounds(5, 50, 46, 15);
        getContentPane().add(lblLogs);

        JScrollPane scrollPaneLogs = new JScrollPane();
        scrollPaneLogs.setBounds(5, 65, 960, 200);
        getContentPane().add(scrollPaneLogs);

        JTextArea textAreaLogs = new JTextArea();
        scrollPaneLogs.setViewportView(textAreaLogs);

        JLabel lblCopyright = new JLabel("Copyright @ Aleksei Mahhov, 2015");
        lblCopyright.setBounds(700, 270, 260, 15);
        getContentPane().add(lblCopyright);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TrackView view = new TrackView();
                    view.setBounds(100, 100, 1000, 350);
                    view.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
