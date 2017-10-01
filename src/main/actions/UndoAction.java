package main.actions;

import main.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by vitia on 20.09.2017.
 */
public class UndoAction extends AbstractAction {
    private View view;

    public UndoAction(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.undo();
    }
}
