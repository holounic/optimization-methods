package lab.one.app.component;

import java.awt.*;
import java.awt.event.ActionListener;

public class AlgoButton extends Button {

    public AlgoButton(String algoName, ActionListener listener) {
        setLabel(algoName);
        setVisible(true);
        addActionListener(listener);
    }
}
