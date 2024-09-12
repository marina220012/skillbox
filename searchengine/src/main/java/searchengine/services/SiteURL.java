package searchengine.services;

import lombok.NoArgsConstructor;
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

@NoArgsConstructor
public class SiteURL extends RecursiveTask<ArrayList<Site>> {

    private Site site;

    public SiteURL(Site site) {
        this.site = site;
    }
    SiteRepository siteRepository;

    @Override
    protected ArrayList<Site> compute() {
        try {
            Thread.sleep(150);
            ArrayList<SiteURL> sitesList = new ArrayList<>();
            Document doc = Jsoup.connect(site.getUrl()).get(); //подключение к сайту
            String siteName = doc.select("tittle").toString();
            Elements websiteAddresses = doc.select("a");

            for (Element url : websiteAddresses){
                String link = url.attr("href");
                if (isValid(link) && !isAlreadyExist(link, sitesList)){
                    site = new Site(siteName, link, StatusType.INDEXING, new Date(), "null");
                    siteRepository.save(site);//todo to improve
                    SiteURL task = new SiteURL();
                    task.fork();
                    sitesList.add(task);
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
