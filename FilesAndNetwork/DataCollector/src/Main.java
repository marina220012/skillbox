import java.io.File;

public class Main {


    public static void main(String[] args) {
        FilesFunctions file = new FilesFunctions();
        file.setHtmlFile("F:\\JAVA\\Projects\\FilesAndNetwork\\DataCollector\\code\\code.html");

        //file.findAllLines();
        //file.findAllStations();

        try {
            String pathToFiles = "F:\\JAVA\\Projects\\FilesAndNetwork\\DataCollector\\data\\data";
            File files = new File(pathToFiles);
            file.collectInformationAboutStationsFromFiles(files);
        }catch (Exception e){
            e.printStackTrace();
        }

        String mkNewJsonFile = "F:\\JAVA\\Projects\\FilesAndNetwork\\DataCollector\\jsonFiles\\map.json";
        file.makingNewJsonFile(mkNewJsonFile);

        file.printStations();
    }

}
