package model;

public class Country {

    private int[] medals;

    private final String[] TYPES_OF_MEDALS;

    private String nameOfCountry;

    private int totalMedals;

    public Country(String nameOfCountry) {

        TYPES_OF_MEDALS = new String[]{"gold", "silver", "bronze"};
        medals = new int[3];
        this.nameOfCountry = nameOfCountry;
        totalMedals = 0;
    }

    public void insertMedals(String[] array){
        int counter = 0;

        while(counter<array.length){
            addByTypesOfMedals(array,counter);
            counter+=2;
        }
    }
    private void addByTypesOfMedals(String[] array, int counter){
        String typeOfmedal = array[counter];
        int amount = Integer.parseInt(array[counter+1]);

        for (int i = 0; i < TYPES_OF_MEDALS.length; i++) {
            if(typeOfmedal.equalsIgnoreCase(TYPES_OF_MEDALS[i])){
                medals[i] += amount;
                totalMedals += amount;
                break;
            }
        }
    }

    public int compareTo(Country country) {
        //gold
        int value = this.medals[0] - country.getMedals()[0];

        if (value == 0) {
            //Silver
            value = this.medals[1] - country.getMedals()[1];
            if (value == 0) {
                //bronze
                value = this.medals[1] - country.getMedals()[1];
                //alphabetic order
                if (value == 0) {
                    value = country.getNameOfCountry().compareTo(this.nameOfCountry);
                }
            }
        }
        return value;
    }

    public int[] getMedals() {
        return medals;
    }

    public void setMedals(int[] medals) {
        this.medals = medals;
    }

    public String getNameOfCountry() {
        return nameOfCountry;
    }

    public void setNameOfCountry(String nameOfCountry) {
        this.nameOfCountry = nameOfCountry;
    }

    public int getTotalMedals() {
        return totalMedals;
    }

    public void setTotalMedals(int totalMedals) {
        this.totalMedals = totalMedals;
    }
}
