package model;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import com.google.gson.Gson;
public class Data {
    private ArrayList<Country> countries;
    private Gson gson;

    public Data() {
        countries = new ArrayList<>();
        gson = new Gson();
    }

    public void addCountry(String[] arrayOfCountries) {
        String nameOfCountry = arrayOfCountries[0];
        String[] arrayOfMedals = Arrays.copyOfRange(arrayOfCountries, 1, arrayOfCountries.length);

        for (Country country : countries) {
            if (country.getNameOfCountry().equals(nameOfCountry)) {
                country.insertMedals((arrayOfMedals));
                return;
            }
        }
        countries.add(new Country(nameOfCountry));
        countries.get(countries.size() - 1).insertMedals(arrayOfMedals);
    }

    private ArrayList<Country> sortByWinners() {
        ArrayList<Country> temp = countries;

        Collections.sort(temp, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o2.compareTo(o1);
            }
        });
        return temp;
    }

    public String printSortedWinners() {
        String msg = "";
        ArrayList<Country> arrayOfCountries = sortByWinners();

        for (Country country : arrayOfCountries) {
            msg += country.getNameOfCountry() + " Gold: " + country.getMedals()[0] + " Silver: "
                    + country.getMedals()[1] + " Bronze: " + country.getMedals()[2] + "\n";
        }
        return msg;
    }

    private ArrayList<Country> sortByTotalMedals() {
        ArrayList<Country> temp = countries;

        Collections.sort(temp, ((o1, o2) -> o2.getTotalMedals() - o1.getTotalMedals()));
        return temp;
    }

    public String printSortedTotalMedals() {
        String msg = "";
        ArrayList<Country> array = sortByTotalMedals();
        for (Country country : array) {
            msg += country.getNameOfCountry() + "Total Medals: " + country.getTotalMedals() + "\n";
        }
        return msg;
    }

    private ArrayList<Country> sortCountries() {
        ArrayList<Country> temp = countries;

        for (int i = 0; i < temp.size(); i++) {
            for (int j = 1; j < temp.size() - i; j++) {
                Country country1 = temp.get(j);
                Country country2 = temp.get(j - 1);
                if (country2.compareTo(country1) < 0) {
                    //values
                    Country lastOne = temp.get(j - 1);
                    Country current = temp.get(j);
                    //swap
                    temp.set(j, lastOne);
                    temp.set(j - 1, current);
                }
            }
        }
        return temp;
    }

    public String printCountries() {
        ArrayList<Country> array = sortCountries();
        String msg = "";
        for (Country country : array) {
            msg += " * " + country.getNameOfCountry() + "\n";
        }
        return msg;
    }

    public void loadData(int choice) throws IOException {
        countries.clear();

        String folder = "saves";
        String[] fileList = new File(folder).list();

        String path = "saves/" + fileList[choice - 1];
        File file = new File(path);

        FileInputStream fis = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        String json = "";
        String line;
        if ((line = reader.readLine()) != null) {
            json = line;
        }
        fis.close();
        Country[] countriesFromJson = gson.fromJson(json, Country[].class);
        Collections.addAll(countries, countriesFromJson);
    }

    public void saveData (String fileNmae){
        String json = gson.toJson(countries);

        try{
            FileOutputStream fos = new FileOutputStream((new File("saves\" + fileName + "+ json)));
            fos.write(json.getBytes(StandardCharsets.UTF_8));
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

}