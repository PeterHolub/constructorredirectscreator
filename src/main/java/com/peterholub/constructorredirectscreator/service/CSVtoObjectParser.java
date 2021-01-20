package com.peterholub.constructorredirectscreator.service;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import com.peterholub.constructorredirectscreator.model.Redirect;
import com.peterholub.constructorredirectscreator.model.RedirectRuleMatch;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
public class CSVtoObjectParser {
    private static final int PATTERN_INDEX = 0;
    private static final int URL_INDEX = 1;

    public List<Redirect> parseCSVtoObjects(String fileName) {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        List<Redirect> redirects = new ArrayList<>();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(fileName))
                .withCSVParser(csvParser)
                .build()) {
            List<String[]> lines = reader.readAll();
            lines.forEach(line -> createRedirectsCollection(redirects, line)
            );
        } catch (IOException | CsvException e) {
            log.error(e);
        }

        return redirects;
    }

    private void createRedirectsCollection(List<Redirect> redirects, String[] line) {
        RedirectRuleMatch redirectRuleMatch = new RedirectRuleMatch();
        redirectRuleMatch.setPattern(line[PATTERN_INDEX]);
        Redirect redirect = new Redirect();
        redirect.setUrl(line[URL_INDEX]);
        redirect.setMatches(Arrays.asList(redirectRuleMatch));
        redirects.add(redirect);
    }


}
