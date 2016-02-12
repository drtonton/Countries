import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {

    static HashMap<String, ArrayList<Country>> countryMap = new HashMap<>();
    static Scanner fileScanner;

    public static void main(String[] args) throws Exception {
        File f = new File("countries.txt");
        fileScanner = new Scanner(f);
        ArrayList<Country> countryList = new ArrayList<>();

        while (fileScanner.hasNext()) {
            String nextLine = fileScanner.nextLine();
            String [] columns = nextLine.split("\\|");
            Country country = new Country(columns[0], columns[1]);
            countryList.add(country);
        }
        for (Country tempCountry : countryList) {
            String firstLetter = String.valueOf(tempCountry.name.charAt(0));
            countryMap.put(firstLetter, new ArrayList<Country>());
        }
        for (Country tempCountry : countryList) {
            String firstLetter = String.valueOf(tempCountry.name.charAt(0));
            countryMap.get(firstLetter).add(tempCountry);
        }
        System.out.println("Type the first letter of the country you seek:");
        Scanner scanner = new Scanner(System.in);
        String nextLine = scanner.nextLine();
        if (nextLine.length()!=1) {
            throw new Exception("Invalid Entry: Must be one letter");
        }
        ArrayList<Country> tempList = new ArrayList();
        tempList = countryMap.get(nextLine);
        String fileContent = new String();
        for (Country tempCountry : tempList) {
            fileContent += String.format("%s %s \n", tempCountry.name, tempCountry.abv);
        }
        String fileName = nextLine + "_countries.txt";
        writeFile(fileName, fileContent);




    }


    static void writeFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();
    }
}
