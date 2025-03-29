package searchengine.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchengine.config.SitesList;
import searchengine.model.Site;
import searchengine.model.status.StatusType;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

import static org.springframework.boot.web.servlet.server.Session.SessionTrackingMode.URL;

@Service
@RequiredArgsConstructor
public class PageIndexingService {

    @Autowired
    private SitesList mainSitesList = new SitesList();

    public boolean pageIndexing(){
         /*HashMap<String, String> sitesConfig = new HashMap<>();
        if( mainSitesList.getSites() == null || mainSitesList.getSites().isEmpty()){
            return;
        }
        mainSitesList.getSites().stream().forEach(site -> sitesConfig.put(site.getUrl(), site.getName()));

        for (String url : sitesConfig.keySet()) {
            String name = sitesConfig.get(url);
            new ForkJoinPool().invoke(new SiteURL(
                    new searchengine.model.Site(name , url, StatusType.INDEXING,new Date(), "null")));
        }*/


        ArrayList<Site> allSites = new ArrayList<>();
        if( mainSitesList.getSites() == null || mainSitesList.getSites().isEmpty()){
            return false;
        }
        mainSitesList.getSites().stream().forEach(site -> {
            allSites.add(new Site(site.getName(),
                    site.getUrl(), StatusType.INDEXING, new Date(), "null"));
            allSites.addAll(new ForkJoinPool().invoke(new SiteURL(
                    new Site(site.getName(),
                            site.getUrl(), StatusType.INDEXING, new Date(), "null"))));
        });

        return true;
    }
}
