package searchengine.services;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import searchengine.config.SitesList;
import searchengine.model.PageRepository;
import searchengine.model.Site;
import searchengine.model.SiteRepository;
import searchengine.model.status.StatusType;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class PageIndexingService {


    private final SiteRepository siteRepository ;
    private final PageRepository pageRepository;
    private final SitesList mainSitesList ;


    public boolean pageIndexing(){
        siteRepository.deleteAll();
        ArrayList<String> allSitesURL = new ArrayList<>();
        if( mainSitesList.getSites() == null || mainSitesList.getSites().isEmpty()){
            return false;
        }

        mainSitesList.getSites().stream().forEach(site -> {
            allSitesURL.add(site.getUrl());

        });
        allSitesURL.stream().forEach(siteURL ->{
            Site site = new Site(getSiteName(siteURL), siteURL, StatusType.INDEXING, new Date(), "-");
            siteRepository.save(site);
            new ForkJoinPool().invoke(new SiteURL(siteURL, pageRepository));
            updateSite(site);

        });

        return true;
    }


    private String getSiteName(String siteURL){
        try {
            Thread.sleep(150);
            Document doc = Jsoup.connect(siteURL).get();
            return doc.select("title").text();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateSite(Site site){
        Site updatedSite = site;
        updatedSite.setStatus(StatusType.INDEXED);
        updatedSite.setStatusTime(new Date());
        siteRepository.delete(site);
        siteRepository.save(updatedSite);

    }

}
