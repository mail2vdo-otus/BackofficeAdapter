package org.vorobiev.backofficeAdapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

@RestController
public class ProcessDealInfo {

    @GetMapping("/version")
    public ResponseEntity<String> getVersion() throws InterruptedException {
        return new ResponseEntity<String>("{\"version\": \""+"0.01"+"''\"}", HttpStatus.OK);
    }


    @RequestMapping(path = "/dealInfo",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public List<Deal> getDeals(@RequestBody Client client) {

        List<Deal> l = new ArrayList<Deal>();
        if (client.getSurName().toLowerCase().contains("вас")) {
            return l;
        }

        int index = (int) Math.floor(Math.random() * 10);
        Date dt = new Date();
        //client.setBirthDate(Date.from(dt.toInstant().minus(Period.ofYears(10+index))));
        client.setBirthDate(Date.from(ZonedDateTime.now().minusYears(30+index).toInstant()));
        String period = "123746840048565638294494948274648393033494949426244648303586766";

        index = (int) Math.floor(Math.random() * (period.length()-10));

        client.setPassport(period.substring(index,index+10));


        Deal d  = new Deal();

        index = (int) Math.floor(Math.random() * 10);


        d.setAmount(12000+index*400.5);
        d.setRate((float) (0.1+0.05*index/10));
        d.setClient(client);
        d.setFundISO("810");

        index = (int) Math.floor(Math.random() * (period.length()-5));

        d.setCode(period.substring(index,index+5));

        index = (int) Math.floor(Math.random() * (3));
        d.setOpenDate(Date.from(ZonedDateTime.now().minusYears(index).minusMonths(2).toInstant()));

        d.setEndDate(Date.from(ZonedDateTime.now().plusYears(index).plusMonths(2).toInstant()));
        l.add(d);
        return l;


    }
}
