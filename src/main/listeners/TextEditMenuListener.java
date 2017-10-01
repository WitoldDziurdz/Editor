package main.listeners;



import main.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * Created by vitia on 20.09.2017.
 */
public class TextEditMenuListener implements MenuListener {
    private View view;
    public TextEditMenuListener(View view){
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        JMenu jMenu = (JMenu) e.getSource();
        Component[] items = jMenu.getMenuComponents();
        for(Component component : items){
            if(view.isHtmlTabSelected()){
                component.setEnabled(true);
            }else {
                component.setEnabled(false);
            }
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
