package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.*;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n" +
            "Hoedje van, hoedje van\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "Heb je dan geen hoedje meer\n" +
            "Maak er één van bordpapier\n" +
            "Eén, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van, hoedje van\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "En als het hoedje dan niet past\n" +
            "Zetten we 't in de glazenkas\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier";

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction(ActionEvent event) {
        //Using an HashSet because it does not add the duplicate values.
        HashSet<String> hashSet = new HashSet<>();

        //Gets the user input.
        String userInput = taInput.getText();

        //Counts the total number of words
        int i = 0;

        //For each string in the input, check if it isn't an empty string and put in in the HashSet.
        //Increment integer i, because another word was processed.
        for (String s : userInput.split("[,\\s.\\n+]"))
        {
            if (!s.equals(""))
            {
                hashSet.add(s);
                i++;
            }
        }

        //Sets the text of the output field to desired values.
        taOutput.setText("Aantal verschillende woorden: " + Integer.toString(hashSet.size()) + ", Totaal aantal woorden: " + i);
    }


    @FXML
    private void sorteerAction(ActionEvent event) {
        //Using a TreeSet because this makes it possible to sort all strings.
        //Using collections.reverseorder so the TreeSet is sorting in a reverse alphabetic order.
        Set<String> treeSet =
                new TreeSet<String>(Collections.reverseOrder());

        //Gets the user input.
        String userInput = taInput.getText();

        //For each string in the input, check if it isn't an empty string and put in in the TreeSet.
        for (String s : userInput.split("[,\\s.\\n+]"))
        {
            if (!s.equals(""))
            {
                treeSet.add(s);
            }
        }

        //Creating the final output string, and displaying it in the output box.
        String finalOutput = "";
        for (String s : treeSet)
        {
            finalOutput += (s + "\n");
        }
        taOutput.setText(finalOutput);
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        //Using an HashMap because this has a Key/Value, so you can put every word the user put in as a key and the value is an integer that
        //gets incremented if the same word is added again.
        Map<String, Integer> hashMap = new HashMap<String, Integer>();

        //Gets the user input.
        String userInput = taInput.getText();

        //Every word is used as a key to check if it already exists in the HashMap,
        //if it does not yet exist we can add it to the HashMap with a value of 1.
        //If it DOES exist we increment the existing value.
        for (String s : userInput.split("[,\\s.\\n+]"))
        {
            if (!s.equals(""))
            {
                Integer frequency = hashMap.get(s);

                if (frequency != null)
                {
                    hashMap.put(s, frequency + 1);
                }
                else
                {
                    hashMap.put(s, 1);
                }
            }
        }

        //Shows the frequencies in the output box.
        taOutput.setText(hashMap.toString());
    }

    @FXML
    private void concordatieAction(ActionEvent event) {

        // Using a TreeMap because this has a Key/Value, so every is only added once
        // and you only have to edit the value
        // Also the treeMap is automatically sorted on ascending keys
        Map treeMap = new TreeMap();

        String userInput = taInput.getText();
        Integer lineNumber = 0;

        // Loop trough every line and keep track of the line number
        for (String s : userInput.split("[\\n]"))
        {
            lineNumber++;

            // When the line is not empty check every word excluding spaces
            // check if the word is already added to the treeMap if it is add the line number to the value
            // if it isn't add a new object to the treeMap
            if (!s.equals("")) {
                for (String word : s.split("[,\\s.\\n+]")) {

                    if (!word.equals("")) {

                        Object value = treeMap.get(word);

                        if (value != null)
                        {
                            // Check if the value already contains the line number and only add it when it doesn't
                            if (!value.toString().contains(lineNumber.toString()))
                            {
                                treeMap.put(word, value.toString() + " " + lineNumber);
                            }
                        }
                        else
                        {
                            treeMap.put(word, lineNumber);
                        }
                    }
                }
            }
        }

        taOutput.setText(treeMap.toString());
    }

}
