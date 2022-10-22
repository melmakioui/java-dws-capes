package csvwriter;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class ExportCSV {

    public void exportMovieCSV () throws IOException {

        String[] fields = {"id","title","year","genre","duration"};
        CSVWriter writer = new CSVWriter(new FileWriter("file.csv"));

    }

    public void exportDirector(){

    }
}
