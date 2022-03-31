package org.dvigal.lesson.spring_1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class MoexExchangeStockPriceLoader implements StockPriceLoader {
    private static final String url = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities.json?date={date}&start={start}";
    private final RestTemplate restTemplate;

    @Override
    public Stream<StockPriceModel> load(final LocalDate from) {

        var currentDate = LocalDate.now();

        return Stream.iterate(from,
                localDate -> localDate.isBefore(currentDate) || localDate.isEqual(currentDate),
                localDate -> localDate.plusDays(1)
        ).map(date -> restTemplate.getForObject(url, Root.class, date.toString(), 1))
                .peek(System.out::println)
        .filter(data -> data != null && data.getHistory() != null && data.getHistory().getData() != null)
                .flatMap(data -> data.getHistory().getData().stream())
                .flatMap(this::map);
    }

    private Stream<StockPriceModel> map(ArrayList<Object> list) {
        return list.stream().map(item -> new StockPriceModel().setValue(item));
    }
    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
    public class BOARDID{
        public String type;
        public int bytes;
        public int max_size;
    }

    public class SECID{
        public String type;
        public int bytes;
        public int max_size;
    }

    public class TRADEDATE{
        public String type;
        public int bytes;
        public int max_size;
    }

    public class SHORTNAME{
        public String type;
        public int bytes;
        public int max_size;
    }

    public class NAME{
        public String type;
        public int bytes;
        public int max_size;
    }

    public class CLOSE{
        public String type;
    }

    public class OPEN{
        public String type;
    }

    public class HIGH{
        public String type;
    }

    public class LOW{
        public String type;
    }

    public class VALUE{
        public String type;
    }

    public class DURATION{
        public String type;
    }

    public class YIELD{
        public String type;
    }

    public class DECIMALS{
        public String type;
    }

    public class CAPITALIZATION{
        public String type;
    }

    public class CURRENCYID{
        public String type;
        public int bytes;
        public int max_size;
    }

    public class DIVISOR{
        public String type;
    }

    public class TRADINGSESSION{
        public String type;
        public int bytes;
        public int max_size;
    }

    public class VOLUME{
        public String type;
    }

    public class Metadata{
        @JsonProperty("BOARDID")
        public BOARDID bOARDID;
        @JsonProperty("SECID")
        public SECID sECID;
        @JsonProperty("TRADEDATE")
        public TRADEDATE tRADEDATE;
        @JsonProperty("SHORTNAME")
        public SHORTNAME sHORTNAME;
        @JsonProperty("NAME")
        public NAME nAME;
        @JsonProperty("CLOSE")
        public CLOSE cLOSE;
        @JsonProperty("OPEN")
        public OPEN oPEN;
        @JsonProperty("HIGH")
        public HIGH hIGH;
        @JsonProperty("LOW")
        public LOW lOW;
        @JsonProperty("VALUE")
        public VALUE vALUE;
        @JsonProperty("DURATION")
        public DURATION dURATION;
        @JsonProperty("YIELD")
        public YIELD yIELD;
        @JsonProperty("DECIMALS")
        public DECIMALS dECIMALS;
        @JsonProperty("CAPITALIZATION")
        public CAPITALIZATION cAPITALIZATION;
        @JsonProperty("CURRENCYID")
        public CURRENCYID cURRENCYID;
        @JsonProperty("DIVISOR")
        public DIVISOR dIVISOR;
        @JsonProperty("TRADINGSESSION")
        public TRADINGSESSION tRADINGSESSION;
        @JsonProperty("VOLUME")
        public VOLUME vOLUME;
        @JsonProperty("INDEX")
        public INDEX iNDEX;
        @JsonProperty("TOTAL")
        public TOTAL tOTAL;
        @JsonProperty("PAGESIZE")
        public PAGESIZE pAGESIZE;
    }

    @Data
    public class History{
        public Metadata metadata;
        public ArrayList<String> columns;
        public ArrayList<ArrayList<Object>> data;
    }

    public class INDEX{
        public String type;
    }

    public class TOTAL{
        public String type;
    }

    public class PAGESIZE{
        public String type;
    }

    @Data
    public class HistoryCursor{
        public Metadata metadata;
        public ArrayList<String> columns;
        public ArrayList<ArrayList<Integer>> data;
    }

    @Data
    public class Root{
        public History history;
        @JsonProperty("history.cursor")
        public HistoryCursor historyCursor;
    }


}
