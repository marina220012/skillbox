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

import java.util.ArrayList;
import java.util.List;

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
            File file = new File("F:\\JAVA\\Projects\\FilesAndNetwork\\DataCollector\\data\\data");
            collectInformationAboutStationsFromFiles(file);
        }catch (Exception e){
            e.printStackTrace();
        }

        stationsInfoFromFiles.forEach(System.out::println);
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
        return ;
    }

    public static void ifFileJson(String path){
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonData = (JSONArray) parser.parse(parseFile(path));
            parseInfo(jsonData);

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

                if (isDate) {
                    stationsInfoFromFiles.add(new Station(fragments[0], "0", fragments[1]));
                } else {
                    stationsInfoFromFiles.add(new Station(fragments[0], fragments[1], "-"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void parseInfo(JSONArray namePlusInfoArray) {
        namePlusInfoArray.forEach(item ->
        {
            JSONObject itemObject = (JSONObject) item;
            String stationName = (String) itemObject.get("name");
            String depth = "0";
            String date = "-";
            if(itemObject.get("depth")== null){
                date = (String) itemObject.get("date");
            }else {
                depth = itemObject.get("depth").toString();
            }

            Station station = new Station(stationName, depth, date);
            stationsInfoFromFiles.add(station);
        });

    }
    public static String getFileExtension(File file) {
        String fileName = file.getName();

        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        return "";
    }
}
