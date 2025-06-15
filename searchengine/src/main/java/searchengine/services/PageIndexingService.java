package searchengine.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import searchengine.config.SitesList;
import searchengine.model.Site;
import searchengine.model.SiteRepository;
import searchengine.model.status.StatusType;


import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;


@Service
@RequiredArgsConstructor
public class PageIndexingService {


    private final SiteRepository siteRepository ;
    private final SitesList mainSitesList ;


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
        //siteRepository.deleteAll();

        ArrayList<Site> allSites = new ArrayList<>();
        if( mainSitesList.getSites() == null || mainSitesList.getSites().isEmpty()){
            return false;
        }


        mainSitesList.getSites().stream().forEach(site -> {
            allSites.add(new Site(site.getName(),
                    site.getUrl(), StatusType.INDEXING, new Date(), "null"));

        });
        allSites.stream().forEach(site ->
                new ForkJoinPool().invoke(new SiteURL(site.getUrl(), siteRepository)));

        return true;
    }
}
