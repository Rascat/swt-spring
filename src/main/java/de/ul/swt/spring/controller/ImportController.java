package de.ul.swt.spring.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.ul.swt.spring.service.ImportService;

@RestController
public class ImportController {

    @Autowired
    private ImportService importService;

    @RequestMapping("/import")
    public int importPersons(
            @RequestParam(value = "file", defaultValue = "names_10000.json") String fileName) {

        try {
            importService.importPersons(fileName);

            return 0;
        } catch (final IOException e) {
            return 1;
        } catch (final ParseException e) {
            return 2;
        }
    }

}
