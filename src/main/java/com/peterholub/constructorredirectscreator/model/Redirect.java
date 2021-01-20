package com.peterholub.constructorredirectscreator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Redirect {
    private String url;
    private List<RedirectRuleMatch> matches = new ArrayList<>();

}
