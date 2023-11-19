package com.engeto.rssreader.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@RestController
public class ReaderController {

    //Úvodní stránka a kategorie
    @GetMapping("/")
    public String printHome() {
        return "<h1>Miniaturní RSS čtečka</h1><br>" +
                "Kategorie:<br>" +
                "<a href =\"/scifi\">/scifi</a><br>" +
                "<a href =\"/historic\">/historic</a><br>" +
                "<a href =\"/romantic\">/romantic</a>";
    }
    @GetMapping("/scifi")
    public ResponseEntity<String> printScifi() {
        return getFileContent("scifi.txt");
    }

    @GetMapping("/romantic")
    public ResponseEntity<String> printRomantic() {
        return getFileContent("romantic.txt");
    }

    @GetMapping("/historic")
    public ResponseEntity<String> printHistoric() {
        return getFileContent("historic.txt");
    }

    private ResponseEntity<String> getFileContent(String fileName) {
        try {
            // Nalezení souboru ve složce files
            File filePath = new File("files/" + fileName);

            // Čtení obsahu souboru pomocí Scanneru
            try (Scanner scanner = new Scanner(filePath)) {
                StringBuilder content = new StringBuilder();
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("<br>");
                }
                return ResponseEntity.ok().body(content.toString());
            }
        } catch (IOException e) {
            // Ošetření výjimky, pokud soubor neexistuje
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Soubor nebyl nalezen");
        }
    }
}

