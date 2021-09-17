package com.example.f21comp1011gcw2;

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
    private TextField priceTextField;

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

        //set up Spinner range, and set it is editable
        SpinnerValueFactory<Integer> mgSpinnerValues = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 1);
        megaPixelsSpinner.setValueFactory(mgSpinnerValues);
        megaPixelsSpinner.setEditable(true);
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
            double price = Double.parseDouble(this.priceTextField.getText()); // convert string to double
            boolean digital = this.digitalCheckBox.isSelected();
            boolean mirrorless = this.mirrorlessCheckBox.isSelected();

            //instantiate the camera object
            Camera camera = new Camera(make, model, lenses, mp, price, digital, mirrorless);
            msgLabel.setText("Created camera: " + camera);
        }catch (Exception e)
        {
            //this.msgLabel.setText(e.getMessage());
            this.msgLabel.setText("Please fill up all the information.");
        }
    }
}
