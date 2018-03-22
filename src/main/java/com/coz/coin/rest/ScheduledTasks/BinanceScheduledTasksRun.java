package com.coz.coin.rest.ScheduledTasks;

import com.coz.coin.rest.CouchController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BinanceScheduledTasksRun {

    @Autowired
    CouchController couchController;

    @Scheduled(fixedRate = 60000)
    public void saveUsdBtcPriceBinance (){
        BinanceScheduledTasks st = new BinanceScheduledTasks();
        st.saveUsdBtcPriceBinance(couchController);
    }

    @Scheduled(fixedRate = 60000)
    public void saveUsdEthPriceBinance (){
        BinanceScheduledTasks st = new BinanceScheduledTasks();
        st.saveUsdEthPriceBinance(couchController);
    }

}
