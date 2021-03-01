package lab.one.app;

import lab.one.app.component.AlgoButton;
import lab.one.util.AlgoName;
import lab.one.util.Task;

import java.awt.*;
import java.awt.event.ActionListener;

public class App {

    private static final int FRAME_SIZE = 500;

    private Frame frame;
    private Component activePanel;

    private void setActivePanel(Component component) {
        if (activePanel != null) {
            frame.remove(activePanel);
        }
        frame.add(component);
        activePanel = component;
    }

    private Panel algoPanel(AlgoName algoName) {
        frame.setTitle(algoName.toString());
        Panel panel = new Panel();
        Task task = new Task(algoName);
        double x = task.runAlgorithm();
        double f = task.f(x);

        System.out.println("djdjdj");

        Label xLabel = new Label("x = " + x);
        xLabel.setVisible(true);
        Label fLabel = new Label("f(x) = " + f);
        fLabel.setVisible(true);

        panel.add(xLabel);
        panel.add(fLabel);
        return panel;
    }

    private final ActionListener dichotomyAL = e -> setActivePanel(algoPanel(AlgoName.DICHOTOMY));

    private final ActionListener fibAL = e -> setActivePanel(algoPanel(AlgoName.FIBONACCI));

    private final ActionListener goldenRatioAL = e -> setActivePanel(algoPanel(AlgoName.GOLDEN_RATIO));

    private final ActionListener parabolicAL = e -> setActivePanel(algoPanel(AlgoName.PARABOLIC));

    private Panel greetingPanel() {
        Panel panel = new Panel();

        Button dichotomyButton = new AlgoButton("Dichotomy", dichotomyAL);
        Button fibButton = new AlgoButton("Fibonacci", fibAL);
        Button goldenRatioButton = new AlgoButton("Golden Ratio", goldenRatioAL);
        Button parabolicButton = new AlgoButton("Parabolic", parabolicAL);

        panel.add(dichotomyButton);
        panel.add(fibButton);
        panel.add(parabolicButton);
        panel.add(goldenRatioButton);

        return panel;
    }

    private void showGreetingView() {
        frame.setTitle("Optimization methods, lab №1");
        Panel greetingPanel = greetingPanel();
        setActivePanel(greetingPanel);
    }

    private void initFrame() {
        frame = new Frame();
        frame.setVisible(true);
        frame.setSize(FRAME_SIZE, FRAME_SIZE);
    }

    public void run() {
        initFrame();
        showGreetingView();
    }


    public static void main(String [] args) {
        new App().run();
    }
}
