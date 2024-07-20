package com.extensionproject.app.gui.loggergui.events;

import com.extensionproject.app.gui.loggergui.LoggerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Resize implements MouseListener {
    private LoggerPanel mainpanel;
    private Dimension minSize;
    private JFrame windowAncestor;
    public Resize(LoggerPanel mainpanel){
        this.mainpanel = mainpanel;
        this.minSize = new Dimension(600, 400);
        windowAncestor = (JFrame) SwingUtilities.getWindowAncestor(this.mainpanel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        warn(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        warn(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        warn(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        warn(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        warn(e);
    }

    private void warn(MouseEvent e){

        JFrame windowAncestor = (JFrame) SwingUtilities.getWindowAncestor(this.mainpanel);
        if(this.mainpanel.getWidth() < this.minSize.getWidth()){
            this.mainpanel.setSize((int) this.minSize.getWidth(), this.mainpanel.getHeight());
            windowAncestor.setSize((int) this.minSize.getWidth(), windowAncestor.getHeight());

        }
        if(this.mainpanel.getHeight() < this.minSize.getHeight()){
            this.mainpanel.setSize(this.mainpanel.getWidth(), (int) this.minSize.getHeight());
            windowAncestor.setSize(windowAncestor.getWidth(), (int) this.minSize.getHeight());
        }

    }
}
