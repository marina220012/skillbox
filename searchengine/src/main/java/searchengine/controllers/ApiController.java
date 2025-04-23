package searchengine.controllers;

import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import searchengine.dto.statistics.StatisticsResponse;
import searchengine.model.Site;
import searchengine.model.status.StatusType;
import searchengine.services.PageIndexingService;
import searchengine.services.StatisticsService;
import searchengine.services.StatisticsServiceImpl;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final StatisticsService statisticsService;
    private final PageIndexingService pageIndexingService;

    public ApiController(StatisticsService statisticsService, PageIndexingService pageIndexingService) {
        this.statisticsService = statisticsService;
        this.pageIndexingService = pageIndexingService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

    @GetMapping("/startIndexing")
    public ResponseEntity<?> startIndexing(){
        new Site("SiteName", "www.siteURL.", StatusType.INDEXED, new Date(), "0");
        return new ResponseEntity<>(HttpStatus.OK);
//        if(pageIndexingService.pageIndexing()){
//            return new ResponseEntity<>( HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
//                "Error"),
//                HttpStatus.NOT_FOUND);

        //new PageIndexingService().pageIndexing();
//        HashMap<String, Boolean> response = new HashMap<>();
//        String startSession = RequestContextHolder.currentRequestAttributes().getSessionId();
//        Site site = new Site();
//        site.setName(name);

    }
}
