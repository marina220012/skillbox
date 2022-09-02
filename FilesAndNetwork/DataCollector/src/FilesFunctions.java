import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class FilesFunctions {
    private List<Station> stationsInfoFromFiles;
    private String htmlFile;

    public String getHtmlFile() {
        return htmlFile;
    }

    public void setHtmlFile(String htmlFile) {
        this.htmlFile = parseFile(htmlFile);
    }
    public FilesFunctions(){
            stationsInfoFromFiles = new ArrayList<>();
            htmlFile = "";
     }
    public FilesFunctions(String path) {
        this.htmlFile = parseFile(path);
    }

    public List<Station> getStationsInfoFromFiles() {
        return stationsInfoFromFiles;
    }

    public void setStationsInfoFromFiles(List<Station> stationsInfoFromFiles) {
        this.stationsInfoFromFiles = stationsInfoFromFiles;
    }

    public void findAllLines(){
        Document doc = Jsoup.parse(htmlFile);
        Elements lines = doc.select("span.js-metro-line.t-metrostation-list-header.t-icon-metroln");
        lines.forEach(line -> System.out.println(line.text() + " "
                + line.attr("data-line") /*line.toString().substring(line.toString().indexOf("data-line=\""),
                line.toString().lastIndexOf("\"")).substring(11)*/));

    }

    public void findAllStations(){
        Document doc = Jsoup.parse(htmlFile);
        Elements stations = doc.select("span.name");
        stations.forEach(station -> System.out.println(station.text() + " "
                + station.parent().parent().toString().substring(
                        station.parent().parent().toString().indexOf("data-line=\""),
                station.parent().parent().toString().lastIndexOf("\" style")).substring(11)));
    }

    public String parseFile(String path){
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line + "\n"));
        } catch (Exception e) {
            e.getStackTrace();
        }
        return builder.toString();
    }

    public void collectInformationAboutStationsFromFiles(File folder){
        File[] files = folder.listFiles();

        if ( files == null){ // если папка пустая
            return ;
        }

        for (File file: files ) {
            switch (getFileExtension(file)){
                case "json":
                {
                    ifFileJson(file.getAbsolutePath());
                    break;
                }
                case "csv":{
                    ifFileCsv(file);
                    break;
                }
                case "": {
                    collectInformationAboutStationsFromFiles(file);
                    break;
                }

            }
        }
        checkingSameInformation();
    }

    private void ifFileJson(String path){
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonData = (JSONArray) parser.parse(parseFile(path));
            parseJsonFile(jsonData);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void ifFileCsv(File file){
        try {
            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            String[] nameTables = lines.get(0).split(",");
            boolean isDate = false;

            if (nameTables[1].equals("Дата открытия")){
                isDate = true;
            }
            lines.remove(0);

            for (String line : lines){
                String[] fragments = line.split(",");
                if (fragments.length != 2){
                    continue;
                }

                String stationName = fragments[0];
                String stationDepth = "-";
                String stationDate = "-";

                if (isDate) {
                    stationDate = fragments[1];
                } else{
                    stationDepth = fragments[1];
                }

                addStationToList(stationName, stationDepth, stationDate, isDate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void parseJsonFile(JSONArray namePlusInfoArray) {
        namePlusInfoArray.forEach(item ->
        {
            JSONObject itemObject = (JSONObject) item;
            String stationName = (String) itemObject.get("name");
            String depth = "-";
            String date = "-";
            boolean finalIsDate = false;

            if(itemObject.get("date") != null){
                date = (String) itemObject.get("date");
                finalIsDate = true;
            }else if(itemObject.get("depth") != null) {
                depth = itemObject.get("depth").toString();
            }

            addStationToList(stationName, depth, date, finalIsDate);
        });

    }

    private void addStationToList(String stationName, String stationDepth, String stationDate, boolean finalIsDate){
        if (stationName == null){
            return;
        }

        boolean isStationIsExist = false;
        if (!stationsInfoFromFiles.isEmpty())
        {

        /*for (Station station : stationsInfoFromFiles) { // TODO: different result with stream
            if (station.getName().equals(stationName)) {
                isStationIsExist = true;
            }

        }*/
            isStationIsExist = stationsInfoFromFiles.stream().anyMatch(station ->
                   station.getName().equals(stationName));
            stationsInfoFromFiles.forEach(station -> {
                if(station.getName().equals(stationName) && finalIsDate){

                    if( Objects.equals(station.getDate(), "-")){
                        station.setDate("");
                    } else if( station.getDate() != null){
                        station.setDate(station.getDate() +" / ");
                    }
                    station.setDate(station.getDate() + stationDate);

                } else if (station.getName().equals(stationName) && !finalIsDate) {
                    if(Objects.equals(station.getDepth(), "-")){
                        station.setDepth("");
                    }else if( station.getDepth() != null){
                        station.setDepth(station.getDepth() +", ");
                    }
                    station.setDepth(station.getDepth() + stationDepth);
                }
            });
        }

        if (!isStationIsExist){
            Station station = new Station(stationName, stationDepth, stationDate);
            stationsInfoFromFiles.add(station);
        }
    }

    private void checkingSameInformation(){ //TODO: maybe allocate a separate method
        stationsInfoFromFiles.forEach(station -> {
            if(station.getDate().contains("/")){

                String[] dates = station.getDate().split(" / ");
                station.setDate("");

                List<String> datesList = Arrays.asList(dates);
                datesList = datesList.stream().distinct().collect(Collectors.toList());
                datesList.forEach(date->station.setDate(station.getDate().concat(date).concat(" / ")));

                station.setDate(station.getDate().substring( 0 , station.getDate().lastIndexOf(" / ")));
            }

            if(station.getDepth().contains(",")){

                String[] depths = station.getDepth().split(", ");
                station.setDepth("");

                List<String> depthsList = Arrays.asList(depths);
                depthsList = depthsList.stream().distinct().collect(Collectors.toList());
                depthsList.forEach(Depth->station.setDepth(station.getDepth().concat(Depth).concat(", ")));

                station.setDepth(station.getDepth().substring( 0 , station.getDepth().lastIndexOf(", ")));
            }
        });
    }
    private  String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        return "";
    }

    public  void makingNewJsonFile(String htmlFile){
        try {
            //--------------------------
            Document doc = Jsoup.parse(this.htmlFile);
            JSONObject object = new JSONObject();
            JSONObject linesArray = new JSONObject();

            Elements lines = doc.select("div.js-metro-stations.t-metrostation-list-table");

            lines.forEach(line-> {
                String numberLine = line.attr("data-line");
                System.out.println(numberLine);
                Elements stationsName = line.getElementsByClass("name");
                JSONArray arrStations = new JSONArray();
                stationsName.forEach(s->
                    arrStations.add(s.text()));
                    //System.out.println(s);});
                 // array of station
                linesArray.put(numberLine, arrStations);
            });
            object.put("lines", linesArray);

            //---------------------------example
            /*JSONObject object1 = new JSONObject();
            JSONArray arr = new JSONArray();*/
            /*arr.add("dad");
            arr.add("mom");
            arr.add("son");
            JSONArray arr2 = new JSONArray();
            arr2.add("milk");
            arr2.add("bread");
            arr2.add("apple");
            object1.put("line1", arr);

            object1.put("line2", arr2);
            //object.put("lines", object1);*/
            //---------------------------
            String path ="F:\\JAVA\\Projects\\FilesAndNetwork\\DataCollector\\jsonFiles\\map.json";

            Files.write(Paths.get(path), object.toJSONString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void printStations(){
        Document doc = Jsoup.parse(htmlFile);
        Elements lines = doc.select("div.js-metro-stations.t-metrostation-list-table");

        lines.forEach(line-> {
                    String numberLine = line.attr("data-line");
                    System.out.println(numberLine);
            Elements stationsName = line.getElementsByClass("name");
            stationsName.forEach(s-> System.out.println(s.text()));
        });
    }
}
