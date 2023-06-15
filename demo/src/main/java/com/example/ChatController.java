package com.example;

import java.io.IOException;
import java.util.ConcurrentModificationException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ChatController {
    private Gesprek actiefGesprek;

    public void setSelectedLanguage(String selectedLanguage) {
        Settings.setSelectedLanguage(selectedLanguage);
    }

    @FXML
    TabPane activeChats;
    @FXML
    TextField vragenBox;
    @FXML
    VBox geschiedenis;


    @FXML Button SettingsButton;




    @FXML
    private void openSettings() throws IOException {
        Main.changeScene("Settings.fxml");
    }



    @FXML
    private void newGesprek() {
        Label label = new Label("Nieuw gesprek");
        Tab newTab = new Tab();
        Gesprek nGesprek = new Gesprek(Main.loggedInAccount, label.getText());
        actiefGesprek = nGesprek;

        newTab.setGraphic(label);
        newTab.setClosable(true);
        TextField textField = new TextField();

        newTab.setOnCloseRequest(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                HBox hGeschiedenis = new HBox();
                hGeschiedenis.getChildren().add(newTab.getGraphic());
                hGeschiedenis.getChildren().get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2) {
                            if (!activeChats.getTabs().contains(newTab)) {
                                activeChats.getTabs().add(newTab);
                            }
                        }
                    }
                });

                geschiedenis.getChildren().add(hGeschiedenis);
            }
        });

        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 1) {
                    actiefGesprek = nGesprek;
                }
                if(event.getClickCount() == 2) {
                    textField.setText(label.getText());
                    newTab.setGraphic(textField);
                    textField.selectAll();
                    textField.requestFocus();
                }
            }
        });

        textField.setOnAction(new EventHandler<ActionEvent>() {  
            @Override  
            public void handle(ActionEvent event) {  
              label.setText(textField.getText());  
              newTab.setGraphic(label);  
            }  
          });
          
          
          textField.focusedProperty().addListener(new ChangeListener<Boolean>() {  
            @Override  
            public void changed(ObservableValue<? extends Boolean> observable,  
                Boolean oldValue, Boolean newValue) {  
              if (! newValue) {  
                label.setText(textField.getText());  
                nGesprek.setTitel(textField.getText());
                newTab.setGraphic(label);            
              }
            }
        });

        newTab.setContent(new VBox());
        activeChats.getTabs().add(newTab);
    }

    @FXML
    Button deleteAllButton;
    @FXML
    private void deleteAllChats() {
        activeChats.getTabs().clear();
        geschiedenis.getChildren().clear();
        Main.loggedInAccount.deleteAllGesprekken();
    }


    @FXML
    private void stelVraag() {
        vragenBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    if (activeChats.getSelectionModel().getSelectedItem() == null) {
                        newGesprek();
                    }

                    Tab current = activeChats.getSelectionModel().getSelectedItem();
                    VBox content = (VBox) current.getContent();
                    actiefGesprek.addVraag(vragenBox.getText());
                    content.getChildren().add(new Text(actiefGesprek.getRecenteVraag()));
                    String antwoord = actiefGesprek.maakAntwoord(vragenBox.getText());
                    content.getChildren().add(new Text(actiefGesprek.getRecenteAntwoord()));
                    vragenBox.clear();
                }
            }
        });
    }


    private String generateResponse(String question) {
        String language = Settings.getSelectedLanguage();
        String response = "";

        // Add logic to generate the response based on the question and selected language
        // Here's an example that returns a hardcoded response based on the language
        switch (language) {
            case "English":
                response = "English response.";
                break;
            case "Dutch":
                response = "Nederlandse reactie.";
                break;
            case "German":
                response = "Deutsche Antwort.";
                break;
            default:
                response = "Unsupported language.";
                break;
        }

        return response;
    }
    @FXML
    private void initialize() {
        deleteAllButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteAllChats();
            }
        });
    }


}









