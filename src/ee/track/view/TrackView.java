package ee.track.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import ee.track.helpers.DataIO;
import ee.track.program.TrackContext;

public class TrackView {
    private static Logger logger = Logger.getLogger(TrackView.class);
    private final JFrame frame = new JFrame();
    private final Map<String, Component> components = new HashMap<>();
    private final JInternalFrame internalFrame = new JInternalFrame("Information view");
    private final Action startStopAction = new StartStopAction();
    private final Action radioButtonAction = new RadioButtonAction();
    private final Action openListAction = new OpenListAction();
    private final Action openLogsAction = new OpenLogsAction();
    private final Action exitAction = new ExitAction();
    private final Action showInterFrameAction = new ShowInternalFrameAction();
    private final int x = 100;
    private final int y = 100;
    private final int width = 1000;
    private final int height = 350;
    private TrackContext context;

    public TrackView(TrackContext context) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TrackACar viewer");

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmOpenList = new JMenuItem();
        mntmOpenList.setAction(openListAction);
        mntmOpenList.setText("Open list to track");
        mnFile.add(mntmOpenList);

        JMenuItem mntmOpenLogs = new JMenuItem();
        mntmOpenLogs.setAction(openLogsAction);
        mntmOpenLogs.setText("Open logs");
        mnFile.add(mntmOpenLogs);

        JMenuItem mntmExit = new JMenuItem();
        mntmExit.setAction(exitAction);
        mntmExit.setText("Exit");
        mnFile.add(mntmExit);

        JMenu mnTrackACar = new JMenu("TrackACar");
        menuBar.add(mnTrackACar);

        JMenuItem mntmDescription = new JMenuItem();
        mntmDescription.setAction(showInterFrameAction);
        mntmDescription.setText("Description");
        mnTrackACar.add(mntmDescription);

        JMenuItem mntmAbout = new JMenuItem();
        mntmAbout.setAction(showInterFrameAction);
        mntmAbout.setText("About");
        mnTrackACar.add(mntmAbout);
        frame.getContentPane().setLayout(null);
        internalFrame.setClosable(true);
        internalFrame.setBounds(410, 11, 550, 240);
        frame.getContentPane().add(internalFrame);
        internalFrame.getContentPane().setLayout(null);

        JTextPane textPane = new JTextPane();
        textPane.setBackground(SystemColor.control);
        textPane.setBounds(10, 11, 514, 189);
        internalFrame.getContentPane().add(textPane);

        JPanel panelSettings = new JPanel();
        panelSettings.setBounds(0, 0, 380, 40);
        frame.getContentPane().add(panelSettings);
        panelSettings.setLayout(null);

        JLabel lblCheckFor = new JLabel("Check for:");
        lblCheckFor.setBounds(5, 5, 70, 20);
        panelSettings.add(lblCheckFor);
        lblCheckFor.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JRadioButton rdbtn10min = new JRadioButton("");
        rdbtn10min.setName("10");
        rdbtn10min.setAction(radioButtonAction);
        rdbtn10min.setBounds(80, 5, 20, 20);
        panelSettings.add(rdbtn10min);

        JRadioButton rdbtn15min = new JRadioButton("");
        rdbtn15min.setAction(radioButtonAction);
        rdbtn15min.setName("15");
        rdbtn15min.setSelected(true);
        rdbtn15min.setBounds(105, 5, 20, 20);
        panelSettings.add(rdbtn15min);

        JRadioButton rdbtn30min = new JRadioButton("");
        rdbtn30min.setAction(radioButtonAction);
        rdbtn30min.setName("30");
        rdbtn30min.setBounds(130, 5, 20, 20);
        panelSettings.add(rdbtn30min);

        JRadioButton rdbtn60min = new JRadioButton("");
        rdbtn60min.setAction(radioButtonAction);
        rdbtn60min.setName("60");
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

        JButton btnStart = new JButton();
        btnStart.setAction(startStopAction);
        btnStart.setText("Start");
        btnStart.setForeground(Color.BLACK);
        btnStart.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnStart.setBounds(180, 5, 90, 30);
        panelSettings.add(btnStart);

        JButton btnStop = new JButton();
        btnStop.setAction(startStopAction);
        btnStop.setText("Stop");
        btnStop.setEnabled(false);
        btnStop.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnStop.setBounds(275, 5, 90, 30);
        panelSettings.add(btnStop);

        JLabel lblStatus1 = new JLabel("");
        lblStatus1.setBounds(400, 10, 560, 15);
        frame.getContentPane().add(lblStatus1);

        JLabel lblStatus2 = new JLabel("");
        lblStatus2.setBounds(400, 30, 560, 15);
        frame.getContentPane().add(lblStatus2);

        JLabel lblLogs = new JLabel("Logs:");
        lblLogs.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblLogs.setBounds(5, 50, 46, 15);
        frame.getContentPane().add(lblLogs);

        JScrollPane scrollPaneLogs = new JScrollPane();
        scrollPaneLogs.setBounds(5, 65, width - 40, height - 150);
        frame.getContentPane().add(scrollPaneLogs);

        JTextArea textAreaLogs = new JTextArea();
        scrollPaneLogs.setViewportView(textAreaLogs);

        JLabel lblCopyright = new JLabel("Copyright @ Aleksei Mahhov, 2015");
        lblCopyright.setBounds(width - 300, height - 80, 260, 15);
        frame.getContentPane().add(lblCopyright);

        frame.getRootPane().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JScrollPane _scrollPaneLogs = (JScrollPane) getComponentByName("logPanel");
                JLabel _lblCopyright = (JLabel) getComponentByName("lblCopyright");
                if (_scrollPaneLogs != null) {
                    _scrollPaneLogs.setBounds(5, 65, frame.getBounds().width - 30, frame.getBounds().height - 150);
                    _lblCopyright.setBounds(frame.getBounds().width - 300, frame.getBounds().height - 80, 260, 15);
                }
            }
        });

        components.put(btnStart.getText(), btnStart);
        components.put(btnStop.getText(), btnStop);
        components.put("Status1", lblStatus1);
        components.put("Status2", lblStatus2);
        components.put(rdbtn10min.getName(), rdbtn10min);
        components.put(rdbtn15min.getName(), rdbtn15min);
        components.put(rdbtn30min.getName(), rdbtn30min);
        components.put(rdbtn60min.getName(), rdbtn60min);
        components.put("logPanel", scrollPaneLogs);
        components.put("lblCopyright", lblCopyright);

        frame.setBounds(x, y, width, height);
        this.context = context;
    }

    public void show(boolean show) {
        frame.setVisible(show);
    }

    /* Actions */

    private class StartStopAction extends AbstractAction {
        private static final long serialVersionUID = -9204629333569183907L;

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if ("Start".equals(button.getText())) {
                JButton btnStop = (JButton) getComponentByName("Stop");
                button.setEnabled(false);
                btnStop.setEnabled(true);
                updateStatus("Track started");
                context.start();
            } else {
                JButton btnStart = (JButton) getComponentByName("Start");
                btnStart.setEnabled(true);
                button.setEnabled(false);
                updateStatus("Track stoped");
                context.stop();
            }
        }
    }

    private class RadioButtonAction extends AbstractAction {
        private static final long serialVersionUID = -5536479956603719879L;

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton button = (JRadioButton) e.getSource();
            JRadioButton button10 = (JRadioButton) getComponentByName("10");
            JRadioButton button15 = (JRadioButton) getComponentByName("15");
            JRadioButton button30 = (JRadioButton) getComponentByName("30");
            JRadioButton button60 = (JRadioButton) getComponentByName("60");
            button10.setSelected(false);
            button15.setSelected(false);
            button30.setSelected(false);
            button60.setSelected(false);
            if ("10".equals(button.getName())) {
                button10.setSelected(true);
                context.setCheckInterval(10 * 60);
            } else if ("15".equals(button.getName())) {
                button15.setSelected(true);
                context.setCheckInterval(15 * 60);
            } else if ("30".equals(button.getName())) {
                button30.setSelected(true);
                context.setCheckInterval(30 * 60);
            } else if ("60".equals(button.getName())) {
                button60.setSelected(true);
                context.setCheckInterval(60 * 60);
            }
        }
    }

    private class OpenListAction extends AbstractAction {
        private static final long serialVersionUID = 2852229236396176144L;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isWindowsOS()) {
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec("C:/Windows/system32/notepad.exe " + System.getProperty("user.dir") + "/" + DataIO.LINKS_FILE_NAME);
                } catch (IOException e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        }
    }

    private class OpenLogsAction extends AbstractAction {
        private static final long serialVersionUID = -4087647098254232803L;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isWindowsOS()) {
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec("C:/Windows/system32/notepad.exe " + System.getProperty("user.dir") + "/trackacar.log");
                } catch (IOException e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        }
    }

    private class ExitAction extends AbstractAction {
        private static final long serialVersionUID = -6510209252826150673L;

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class ShowInternalFrameAction extends AbstractAction {
        private static final long serialVersionUID = 7156471072828176364L;

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            JTextPane textPane = (JTextPane) internalFrame.getContentPane().getComponent(0);
            if ("Description".equals(menuItem.getText())) {
                textPane.setText("Description of this program");
            } else if ("About".equals(menuItem.getText())) {
                textPane.setText("Track a car program.\nVersion 1.0");
            }
            internalFrame.setVisible(true);
        }
    }

    /* Helpers */

    private boolean isWindowsOS() {
        return StringUtils.containsIgnoreCase(System.getProperty("os.name"), "windows");
    }

    private Component getComponentByName(String name) {
        Component component = components.get(name);
        if (component != null) {
            return component;
        }
        throw new IllegalArgumentException("No component foudn for name=" + name);
    }

    private void updateStatus(String newStatus) {
        JLabel status1 = (JLabel) getComponentByName("Status1");
        JLabel status2 = (JLabel) getComponentByName("Status2");
        status1.setText(newStatus);
        status2.setText("" + new Date());
    }
}
