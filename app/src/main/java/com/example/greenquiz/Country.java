package com.example.greenquiz;

public enum Country {
    MONDE("MONDE","Bravo, votre consommation est celui de la moyenne mondiale. Pour rappel la moyenne modiale est de 4.91 tonnes de CO².",
            R.drawable.monde),
    FRANCE("FRANCE","Bravo, vous êtes Français !",
            R.drawable.france),
    ESPAGNE("ESPAGNE","Votre consommation est de 6.09 tonnes de CO² se qui est proche de la moyenne modiale. Pour rappel, la moyenne mondial qui est de 4.91 tonnes de CO² par habitant.",
            R.drawable.espagne),
    EU("EUROPE","Votre consommation est importante, elle est de 7.72 tonnes de CO². Pour rappel, votre consommation est près de 1.5  fois supérieur à la moyenne mondial qui est de 4.91 tonnes de CO² par habitant.",
            R.drawable.eu),
    CHINE("CHINE","Votre consommation est importante, elle est de 7.72 tonnes de CO². Pour rappel, votre consommation est de 1.5  fois supérieur à la moyenne mondial qui est de 4.91 tonnes de CO² par habitant.",
            R.drawable.chine),
    ALLEMANGNE("ALLEMAGNE","Votre consommation est importante, elle est de 9.70 tonnes de CO². Pour rappel, votre consommation est plus de 2  fois supérieur à la moyenne mondial qui est de 4.91 tonnes de CO² par habitant.",
            R.drawable.allemand),
    USA("USA","Votre consommation est une des plus importante, elle est de 15.74 tonnes de CO². Pour rappel, votre consommation est plud de 3 fois supérieur à la moyenne mondial qui est de 4.91 tonnes de CO² par habitant.",
            R.drawable.usa),
    CANADA("CANADA","Votre consommation est une des plus importante, elle est de 16.85 tonnes de CO². Pour rappel, votre consommation est plus de 3 fois supérieur à la moyenne mondial qui est de 4.91 tonnes de CO² par habitant.",
            R.drawable.canada),
    ARABIESAOUDITE("ARABIE SAOUDITE","Votre consommation est une des plus importante, elle est de 19.39 tonnes de CO². Pour rappel, votre consommation est près de 4 fois supérieur à la moyenne mondial qui est de 4.91 tonnes de CO² par habitant.",
            R.drawable.arabiesaoudite),
    KOWEIT("KOWEIT","Votre consommation est une des plus importante, elle est de 23.49 tonnes de CO². Pour rappel, votre consommation est près de 5 fois supérieur à la moyenne mondial qui est de 4.91 tonnes de CO² par habitant.",
            R.drawable.koweit),
    QATAR("QATAR", "Votre consommation est de loin la plus importante, elle est de 37.05 tonnes de CO². Pour rappel, votre consommation est près de 8 fois supérieur à la moyenne mondial qui est de 4.91 tonnes de CO² par habitant.",
            R.drawable.qatar),
    ;

    private final String title;
    private final String description;
    private final int resource;

    Country(String title, String description, int resource) {
        this.title = title;
        this.description = description;
        this.resource = resource;
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

    public static Country getByName(String identifier) {
        for (Country country : values()) {
            if(country.name().equalsIgnoreCase(identifier)) {
                return country;
            }
        }
        return null;
    }
}
