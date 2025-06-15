package searchengine.services;

import org.springframework.transaction.annotation.Transactional;
import searchengine.model.Site;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import searchengine.model.SiteRepository;
import searchengine.model.status.StatusType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.RecursiveTask;

//@NoArgsConstructor
//@RequiredArgsConstructor
public class SiteURL extends RecursiveTask<ArrayList<Site>> {

    private Site site;

    public SiteURL(String siteURL, SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
        site = new Site();
        site.setUrl(siteURL);
    }

    private final SiteRepository siteRepository;



    @Override
    protected ArrayList<Site> compute() {
        try {
            Thread.sleep(150);
            ArrayList<SiteURL> sitesList = new ArrayList<>();
            Document doc = Jsoup.connect(site.getUrl()).get(); //подключение к сайту
            String siteName = doc.select("tittle").toString();
            Elements websiteAddresses = doc.select("a");
            site.setName(siteName);
            site.setStatus(StatusType.INDEXING);
            site.setStatusTime(new Date());
            site.setLastError("-");
            siteRepository.save(site);

            for (Element url : websiteAddresses){
                String link = url.attr("href");
                if (isValid(link) && !isAlreadyExist(link, sitesList)){
                    SiteURL newSite = new SiteURL(link, siteRepository);
                    //siteRepository.save(site);//todo to improve
                    //SiteURL task = new SiteURL();
                    newSite.fork();
                    sitesList.add(newSite);
                    site.addChild(new Site());
                    site.setStatus(StatusType.INDEXED);

                }
            }

            for (SiteURL task : sitesList){
                site.addChildren(task.join());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return site.getChildren();
    }
    private boolean isValid(String link) {
        return link.contains(site.getUrl()) && !link.contains(".pdf") && !link.equals(site.getUrl());
    }
    private boolean isAlreadyExist(String link, ArrayList<SiteURL> taskList){
        for (SiteURL child: taskList){
            if(child.site.getName().equals(link)){
                return true;
            }
        }
        return false;
    }

}
