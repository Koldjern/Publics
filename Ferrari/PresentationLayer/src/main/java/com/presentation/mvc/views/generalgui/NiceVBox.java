package com.presentation.mvc.views.generalgui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
//anders
//just adds some nice to haves
public class NiceVBox extends VBox{
    private Insets inset;
    public NiceVBox(String css, Insets insets, Node... nodes) {
        this(css, nodes);
        this.inset = insets;
        for(Node n : nodes)
            setMargin(n, insets);
    }
    public NiceVBox(String css, Node... nodes) {
        super(nodes);
        getStyleClass().add(css);
    }
    public NiceVBox(Insets insets, Node... nodes) {
        super(nodes);
        this.inset = insets;
        for(Node n : nodes)
            setMargin(n, insets);
    }
}
