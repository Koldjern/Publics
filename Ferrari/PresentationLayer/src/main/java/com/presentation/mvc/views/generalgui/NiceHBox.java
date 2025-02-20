package com.presentation.mvc.views.generalgui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
//anders
//just adds some nice to haves
public class NiceHBox extends HBox {
    private Insets inset;
    public NiceHBox(String css, Insets insets, Node... nodes) {
        this(css, nodes);
        this.inset = insets;
        for(Node n : nodes)
            setMargin(n, insets);
    }
    public NiceHBox(String css, Node... nodes) {
        super(nodes);
        getStyleClass().add(css);
    }
    public NiceHBox(Insets insets, Node... nodes) {
        super(nodes);
        this.inset = insets;
        for(Node n : nodes)
            setMargin(n, insets);
    }
}
