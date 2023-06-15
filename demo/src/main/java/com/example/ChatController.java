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

    private void loadGesprek(Gesprek uGesprek) {
        Label label = new Label(uGesprek.getTitel());
        actiefGesprek = uGesprek;
        Tab newTab = new Tab();

        newTab.setGraphic(label);
        newTab.setClosable(true);
        TextField textField = new TextField();
        newTab.setOnCloseRequest(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                closeGesprek(newTab);
            }
        });

        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickGesprek(event, uGesprek, textField, newTab, label);
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
                uGesprek.setTitel(textField.getText());
                newTab.setGraphic(label);            
              }
            }
        });

        VBox chat = new VBox();
        for(int i = 0; i < uGesprek.getVragen().size(); i++) {
            chat.getChildren().add(new Text(uGesprek.getVragen().get(i)));
            chat.getChildren().add(new Text(uGesprek.getAntwoorden().get(i)));
        }

        newTab.setContent(chat);
        activeChats.getTabs().add(newTab);
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
                closeGesprek(newTab);
            }
        });

        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickGesprek(event, nGesprek, textField, newTab, label);
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

    private void closeGesprek(Tab newTab) {
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

    private void clickGesprek(MouseEvent event, Gesprek nGesprek, TextField textField, Tab newTab, Label label) {
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

    @FXML
    private void initialize() {
        deleteAllButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteAllChats();
            }
        });

        for(Gesprek gesprek: Main.loggedInAccount.getGesprekken()) {
            loadGesprek(gesprek);
        }
    }


}









