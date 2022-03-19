package com.example.greenquiz;

public enum Country {
    FRANCE("FRANCE","Bravo, vous êtes Français !",
            R.drawable.france, "FR"),
    EU("CHINE","Votre consommation est importante, elle est de 7.72 tonnes de CO². Pour rappel, votre consommation est près de 1.5  fois supérieur à la moyenne mondial qui est de 4.91 tonnes de CO² par habitant.",
            R.drawable.eu, "CN"),
    ALLEMANGNE("ALLEMAGNE","Votre consommation est importante, elle est de 9.70 tonnes de CO². Pour rappel, votre consommation est plus de 2  fois supérieur à la moyenne mondial qui est de 4.91 tonnes de CO² par habitant.",
            R.drawable.allemand, "DE"),
    CANADA("CANADA","Votre consommation est une des plus importante, elle est de 16.85 tonnes de CO². Pour rappel, votre consommation est plus de 3 fois supérieur à la moyenne mondial qui est de 4.91 tonnes de CO² par habitant.",
            R.drawable.canada, "CA"),
    QATAR("QATAR", "Votre consommation est de loin la plus importante, elle est de 37.05 tonnes de CO². Pour rappel, votre consommation est près de 8 fois supérieur à la moyenne mondial qui est de 4.91 tonnes de CO² par habitant.",
            R.drawable.qatar, "QA"),
    ;

    private final String title;
    private final String description;
    private final int resource;
    private final String countryCode;

    Country(String title, String description, int resource, String countryCode) {
        this.title = title;
        this.description = description;
        this.resource = resource;
        this.countryCode = countryCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getResource() {
        return resource;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public static Country getByName(String identifier) {
        for (Country country : values()) {
            if(country.name().equalsIgnoreCase(identifier)) {
                return country;
            }
        }
        return null;
    }
}
