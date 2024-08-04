package searchengine.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import searchengine.config.Site;
import searchengine.config.SitesList;
import searchengine.model.status.StatusType;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

import static org.springframework.boot.web.servlet.server.Session.SessionTrackingMode.URL;

@Service
@RequiredArgsConstructor
public class PageIndexingService {

    SitesList mainSitesList;

    public void pageIndexing(){
        HashMap<String, String> sitesConfig = new HashMap<>();
        if(mainSitesList.getSites().isEmpty()){
            return;
        }
        mainSitesList.getSites().stream().forEach(site -> sitesConfig.put(site.getUrl(), site.getName()));

        for (String url : sitesConfig.keySet()) {
            String name = sitesConfig.get(url);
            new ForkJoinPool().invoke(new SiteURL(
                    new searchengine.model.Site(name , url, StatusType.INDEXING,new Date(), "null")));
        }

    }
}
