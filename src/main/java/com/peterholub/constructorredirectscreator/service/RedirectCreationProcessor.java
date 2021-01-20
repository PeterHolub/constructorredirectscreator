package com.peterholub.constructorredirectscreator.service;

import com.peterholub.constructorredirectscreator.client.ConstructorClient;
import com.peterholub.constructorredirectscreator.model.Redirect;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RedirectCreationProcessor {

    private final CSVtoObjectParser csVtoObjectParser;

    private final ConstructorClient constructorClient;

    public void processRedirectsCreation(String fileName) {
        List<Redirect> redirects = csVtoObjectParser.parseCSVtoObjects(fileName);
        redirects.forEach(constructorClient::createRedirect);

    }
}
