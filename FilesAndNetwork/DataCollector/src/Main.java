import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    private static List<Station> stationsInfoFromFiles = new ArrayList<>();

    public static void main(String[] args) {
        String htmlFile = parseFile("F:\\JAVA\\Projects\\FilesAndNetwork\\DataCollector\\code\\code.html");

        Document doc = Jsoup.parse(htmlFile);
        Elements lines = doc.select("span.js-metro-line.t-metrostation-list-header.t-icon-metroln");
        lines.forEach(line -> System.out.println(line.text() + " "
                + line.toString().substring(line.toString().indexOf("data-line=\""),
                line.toString().lastIndexOf("\"")).substring(11)));

        Elements stations = doc.select("p.single-station");
        stations.forEach(station -> System.out.println(station.text() + " "
                + station.parent().toString().substring(station.parent().toString().indexOf("data-line=\""),
                station.parent().toString().lastIndexOf("\" style")).substring(11)));

        try {
            String path = "F:\\JAVA\\Projects\\FilesAndNetwork\\DataCollector\\data\\data";
            File file = new File(path);
            collectInformationAboutStationsFromFiles(file);
        }catch (Exception e){
            e.printStackTrace();
        }

        stationsInfoFromFiles.forEach(System.out::println);
        makingNewJsonFile();
    }

    public static String parseFile(String path){
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line + "\n"));
        } catch (Exception e) {
            e.getStackTrace();
        }
        return builder.toString();
    }

    public static void collectInformationAboutStationsFromFiles(File folder){
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

    public static void ifFileJson(String path){
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonData = (JSONArray) parser.parse(parseFile(path));
            parseJsonFile(jsonData);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static void ifFileCsv(File file){
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
    private static void parseJsonFile(JSONArray namePlusInfoArray) {
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

    public static void addStationToList(String stationName, String stationDepth, String stationDate, boolean finalIsDate){
        if (stationName == null){
            return;
        }

        boolean isStationIsExist = false;
        if (!stationsInfoFromFiles.isEmpty())
        {

        /*for (Station station : stationsInfoFromFiles) {
            if (station.getName().equals(stationName)) {
                isStationIsExist = true;
            }

        }*/
        isStationIsExist = stationsInfoFromFiles.stream().anyMatch(station ->
                Objects.equals(station.getName(), stationName));
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

    public static void checkingSameInformation(){
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

                String[] Depths = station.getDepth().split(", ");
                station.setDepth("");

                List<String> DepthsList = Arrays.asList(Depths);
                DepthsList = DepthsList.stream().distinct().collect(Collectors.toList());
                DepthsList.forEach(Depth->station.setDepth(station.getDepth().concat(Depth).concat(", ")));

                station.setDepth(station.getDepth().substring( 0 , station.getDepth().lastIndexOf(", ")));
            }
        });
    }
    public static String getFileExtension(File file) {
        String fileName = file.getName();

        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        return "";
    }

    public static void makingNewJsonFile(){
        try {
            PrintWriter writer = new PrintWriter("F:\\JAVA\\Projects\\FilesAndNetwork\\DataCollector\\jsonFiles\\map.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
