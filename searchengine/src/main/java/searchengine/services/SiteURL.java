package searchengine.services;

import lombok.RequiredArgsConstructor;
import searchengine.model.Page;
import searchengine.model.PageRepository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Pattern;

//@NoArgsConstructor
@RequiredArgsConstructor
public class SiteURL extends RecursiveAction {

    private Page page;
    private String mainSiteURL;
    private final PageRepository pageRepository;


    public SiteURL(String siteURL, PageRepository pageRepository) {
        this.pageRepository = pageRepository;
        this.mainSiteURL = siteURL;
        page = new Page();
        page.setPath("");
    }

    public SiteURL(String siteUrl, String path, PageRepository pageRepository){
        this.pageRepository = pageRepository;
        this.mainSiteURL = siteUrl;
        page = new Page();
        page.setPath(path);
    }


    @Override
    protected void compute() {
        try {
            Thread.sleep(150);
            ArrayList<SiteURL> pagesList = new ArrayList<>();
            String link = mainSiteURL + page.getPath();
            Document doc = Jsoup.connect(link).get();
            Elements websiteAddresses = doc.select("a");
            StringBuilder content = new StringBuilder(doc.select("*").text());//todo Get html to stringBuilder&

            page.setContent(content);
            page.setCode(0);

            for (Element url : websiteAddresses){
                String path = url.attr("href");
                if (Pattern.matches("^/", path) && !isAlreadyExist(path, pagesList)){
                    SiteURL newSite = new SiteURL(mainSiteURL, path, pageRepository);
                    newSite.fork();
                    pagesList.add(newSite);
                }
            }

            for (SiteURL task : pagesList){
                task.join();
            }
            pageRepository.save(page);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    private boolean isAlreadyExist(String path, ArrayList<SiteURL> pagesList){
        for (SiteURL site : pagesList){
            if(site.page.getPath().equals(path)){
                return true;
            }
        }
        return false;
    }

}
