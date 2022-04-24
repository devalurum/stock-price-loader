package org.devalurum.stockprice.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class History {
    private ArrayList<String> columns;
    private ArrayList<ArrayList<?>> data;
}
