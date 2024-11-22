package com.example.testfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

public class HelloApplication extends Application {

    ArrayList<Person> personList = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        BorderPane mainLayout = new BorderPane();

        Label banner = new Label("Entry Form");
        banner.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #2E8B57;" +
                " -fx-font-family: 'Arial';");
        banner.setAlignment(Pos.CENTER);
        banner.setMaxWidth(Double.MAX_VALUE);
        banner.setPadding(new Insets(20));

        mainLayout.setTop(banner);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(40));
        grid.setHgap(20);
        grid.setVgap(25);
        grid.setStyle("-fx-background-color: #F8F8F8; -fx-border-color: #D3D3D3; -fx-border-width: 2px;");

        Label name = new Label("Name:");
        name.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        TextField nameText = new TextField();
        nameText.setPromptText("Enter your name");
        nameText.setStyle("-fx-font-size: 14px; -fx-border-color: #D3D3D3;");

        Label fatherName = new Label("Father Name:");
        fatherName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        TextField fatherNameText = new TextField();
        fatherNameText.setPromptText("Enter father's name");
        fatherNameText.setStyle("-fx-font-size: 14px; -fx-border-color: #D3D3D3;");

        Label cnic = new Label("CNIC:");
        cnic.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        TextField cnicText = new TextField();
        cnicText.setPromptText("Enter CNIC");
        cnicText.setStyle("-fx-font-size: 14px; -fx-border-color: #D3D3D3;");

        Label dob = new Label("Date of Birth:");
        dob.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        DatePicker dobPicker = new DatePicker();
        dobPicker.setPromptText("dd/MM/yyyy");
        dobPicker.setStyle("-fx-font-size: 14px;");

        Label genderLabel = new Label("Gender:");
        genderLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);

        Label cityLabel = new Label("City:");
        cityLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.getItems().addAll("Lahore", "Islamabad", "Multan");
        cityComboBox.setPromptText("Select city");
        cityComboBox.setStyle("-fx-font-size: 14px; -fx-border-color: #D3D3D3;");

        Label imageLabel = new Label("Image:");
        imageLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Button imageButton = new Button("Choose Image");
        imageButton.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; " +
                "-fx-font-size: 14px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #2E8B57; -fx-text-fill: white; " +
                "-fx-font-size: 14px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        grid.add(name, 0, 0);
        grid.add(nameText, 1, 0);

        grid.add(fatherName, 0, 1);
        grid.add(fatherNameText, 1, 1);

        grid.add(cnic, 0, 2);
        grid.add(cnicText, 1, 2);

        grid.add(dob, 0, 3);
        grid.add(dobPicker, 1, 3);

        grid.add(genderLabel, 0, 4);
        grid.add(male, 1, 4);
        grid.add(female, 1, 5);

        grid.add(cityLabel, 0, 6);
        grid.add(cityComboBox, 1, 6);

        grid.add(imageLabel, 0, 7);
        grid.add(imageButton, 1, 7);

        grid.add(saveButton, 1, 8);

        VBox rightSection = new VBox(10);
        rightSection.setPadding(new Insets(40));

        ImageView imageView = new ImageView();
        imageView.setFitWidth(250);
        imageView.setFitHeight(280);
        imageView.setStyle("-fx-border-color: #D3D3D3; -fx-border-width: 2px;");

        imageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);
            }
        });

        saveButton.setOnAction(e -> {
            String selectedGender = male.isSelected() ? "Male" : female.isSelected() ? "Female" : null;

            Person person = new Person(
                    nameText.getText(),
                    fatherNameText.getText(),
                    cnicText.getText(),
                    dobPicker.getValue() != null ? dobPicker.getValue().toString() : "",
                    selectedGender,
                    cityComboBox.getValue()
            );

            personList.add(person);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Person Details");
            alert.setHeaderText("Details Saved Successfully");
            alert.setContentText(
                    "Name: " + person.getName() + "\n" +
                            "Father's Name: " + person.getFatherName() + "\n" +
                            "CNIC: " + person.getCnic() + "\n" +
                            "Date of Birth: " + person.getDateOfBirth() + "\n" +
                            "Gender: " + person.getGender() + "\n" +
                            "City: " + person.getCity()
            );

            alert.showAndWait();

            nameText.clear();
            fatherNameText.clear();
            cnicText.clear();
            dobPicker.setValue(null);
            genderGroup.selectToggle(null);
            cityComboBox.setValue(null);
            imageView.setImage(null);

            personList.forEach(System.out::println);
        });

        rightSection.getChildren().add(imageView);

        mainLayout.setLeft(grid);
        mainLayout.setRight(rightSection);

        Scene scene = new Scene(mainLayout, 900, 600);
        stage.setTitle("Form Layout Example");
        stage.setScene(scene);
        stage.show();
    }
}
