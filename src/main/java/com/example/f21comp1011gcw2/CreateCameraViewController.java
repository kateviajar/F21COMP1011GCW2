package com.example.f21comp1011gcw2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CreateCameraViewController implements Initializable {

    @FXML
    private ComboBox<String> makeComboBox;

    @FXML
    private TextField modelTextField;

    @FXML
    private Slider priceSlider;

    @FXML
    private Label priceLabel;

    @FXML
    private Spinner<Integer> megaPixelsSpinner;

    @FXML
    private CheckBox mirrorlessCheckBox;

    @FXML
    private CheckBox digitalCheckBox;

    @FXML
    private Button submitButton;

    @FXML
    private Label msgLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> cameraBrands = Arrays.asList("Canon", "Nikon", "Sony");
        makeComboBox.getItems().addAll(cameraBrands); //add the brands list to ComboBox

        //set up Spinner range, and set it is editable, range of 5-75
        //The arguments are min, max, default, and step (increment)
        SpinnerValueFactory<Integer> mgSpinnerValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 75, 18, 5);
        megaPixelsSpinner.setValueFactory(mgSpinnerValues);
        megaPixelsSpinner.setEditable(true);

        //Prevent users to input string into Spinner field
        TextField spinnerTextField = megaPixelsSpinner.getEditor();
        spinnerTextField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            try {
                Integer.parseInt(newValue);
            }catch (Exception e){
                spinnerTextField.setText(oldValue);
                msgLabel.setText("You can only enter numbers!");
            }
        }));

        //Update the Price Slider, Max, Min, Default Value and Label
        priceSlider.setMin(300);
        priceSlider.setMax(3000);
        priceSlider.setValue(500);
        priceLabel.setText(String.format("$%.2f", priceSlider.getValue()));

        //making an inner class and referring to it
        //priceSlider.valueProperty().addListener(new PriceChangeListener2());

        //creating an anonymous inner class
        /*priceSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                priceLabel.setText(String.format("$%.2f", newValue));
            }
        });*/

        //using a Lambda expression
        priceSlider.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
            priceLabel.setText(String.format("$%.2f", newValue));
        }));

    }

    @FXML
    private void submitButton()
    {
        try
        {
            String make = this.makeComboBox.getSelectionModel().getSelectedItem();
            String model = this.modelTextField.getText();
            ArrayList<String> lenses = new ArrayList<>();
            lenses.addAll(Arrays.asList("70-200 F2.8", "15-50 F1.8", "100-400 F4.5"));
            int mp = this.megaPixelsSpinner.getValue();
            //double price = Double.parseDouble(this.priceLabel.getText()); // convert string to double
            double price = this.priceSlider.getValue();

            boolean digital = this.digitalCheckBox.isSelected();
            boolean mirrorless = this.mirrorlessCheckBox.isSelected();

            //instantiate the camera object
            Camera camera = new Camera(make, model, lenses, mp, price, digital, mirrorless);
            msgLabel.setText("Created camera: " + camera);
        }catch (Exception e)
        {
            this.msgLabel.setText(e.getMessage());
            //this.msgLabel.setText("Please fill up all the information.");
        }
    }

    //Create a change listener inside controller class
    public class PriceChangeListener2 implements ChangeListener {
        @Override
        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
            priceLabel.setText(String.format("$%.2f", newValue));
        }
    }
}
