package com.presentation.mvc.views.agreement;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Arrays;

import com.model.Pair;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.presentation.mvc.views.ViewVBox;
import com.presentation.mvc.views.generalgui.NiceHBox;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
//karl 
//this class is made to show the agreement in the view and allow editing of certain fields
public class OpenAgreementView extends ViewVBox{
    private HBox buttonBox;
    public OpenAgreementView(Pane employeeView, Pane customerView, Pane vehicleView, AgreementModel model, boolean open) {
        ChangeListener ch;
        getStyleClass().add("rightContainer");
        DecimalFormat df = new DecimalFormat("#.00"); 
        TextField rki = new TextField(model.getRki() != null ? model.getRki().toString() : "");
        rki.setEditable(false);
        model.RKiProperty().addListener( ch = (observable, old, newval) -> rki.setText(newval != null ? newval.toString() : ""));
        
        addListener(new Pair<>(model.RKiProperty(), ch));

        TextField startAgreement = new TextField(model.getStartAgreement().toString());
        startAgreement.setEditable(false);
        
        TextField rate = new TextField(df.format(model.getDaysRate()) + "%");
        rate.setEditable(false);
        model.daysRateProperty().addListener(ch = (observable, old, newval) -> rate.setText(df.format(newval) + "%"));
        addListener(new Pair<>(model.daysRateProperty(), ch));

        TextField totalRate = new TextField(df.format(model.getTotalRate()) + "%");
        totalRate.setEditable(false);
        model.totalRateProperty().addListener(ch = (observable, old, newval) -> totalRate.setText(df.format(newval) + "%"));
        addListener(new Pair<>(model.totalRateProperty(), ch));

        TextField totalAmount = new TextField(df.format(model.getEndPrice()) + ".Kr");
        totalAmount.setEditable(false);
        model.endPriceProperty().addListener(ch = (observable, old, newval) -> totalAmount.setText(df.format(newval) + ".Kr"));
        addListener(new Pair<>(model.endPriceProperty(), ch));
        ChangeListener<LocalDate> chdate;
        DatePicker startDate = new DatePicker(model.getStart() != null ? model.getStart().toLocalDate() : new Date(0).toLocalDate());
        startDate.valueProperty().addListener(chdate = (observable, old, newval) -> model.setStart(newval != null ? Date.valueOf(newval) : new Date(System.currentTimeMillis())));
        addListener(new Pair<>(startDate.valueProperty(), chdate));
        ChangeListener<Date> chd;
        model.startProperty().addListener(chd = (observable, old, newval) -> startDate.setValue(newval != null ? newval.toLocalDate() : new Date(0).toLocalDate()));
        addListener(new Pair<>(model.startProperty(), chd));

        startDate.setEditable(open);
        startDate.setDisable(!open);

        DatePicker endDate = new DatePicker(model.getEnd() != null ? model.getStartAgreement().toLocalDate() : new Date(0).toLocalDate());
        model.endProperty().addListener(chd = (observable, old, newval) -> endDate.setValue(newval.toLocalDate()));
        addListener(new Pair<>(model.endProperty(), chd));

        endDate.setEditable(false);
        endDate.setDisable(true);

        TextField StartValue = new TextField(String.valueOf(model.getStartValue()));
        Bindings.bindBidirectional(StartValue.textProperty(), model.startValueProperty(), new NumberStringConverter());
        StartValue.setEditable(open);

        TextField FixedTerms = new TextField(String.valueOf(model.getFixedTerms()));
        Bindings.bindBidirectional(FixedTerms.textProperty(), model.fixedTermsProperty(), new NumberStringConverter());
        FixedTerms.setEditable(open);
        
        addProperties(Arrays.asList(model.startValueProperty(), model.fixedTermsProperty()));
        //adds new labels and textfields to the view
        getChildren().add(
            new HBox(
                customerView,
                employeeView,
                vehicleView,
                new VBox(
                    new NiceHBox("rightContainer", new Insets(5), new Label("Tilbud givet den: "), startAgreement),
                    new NiceHBox("rightContainer", new Insets(5), new Label("Dagens rente: "), rate),
                    new NiceHBox("rightContainer", new Insets(5), new Label("Rki:"), rki),
                    new NiceHBox("rightContainer", new Insets(5), new Label("Total rente: "), totalRate),
                    new NiceHBox("rightContainer", new Insets(5), new Label("Total Pris: "), totalAmount),
                    new NiceHBox("rightContainer", new Insets(5), new Label("Start indskud:"), StartValue),
                    new NiceHBox("rightContainer", new Insets(5), new Label("Terminer:"), FixedTerms),
                    new NiceHBox("rightContainer", new Insets(5), new Label("i kraft dato: "), startDate),
                    new NiceHBox("rightContainer", new Insets(5), new Label("slut dato: "), endDate)
                ) 
            )
        );

    }
    @Override
    public void addButtons(Button... buttons) {
        if(buttonBox == null) {
            getChildren().add(buttonBox = new NiceHBox("buttonBar", buttons));
        }
        else
            buttonBox.getChildren().addAll(buttons);
    }
    

}
