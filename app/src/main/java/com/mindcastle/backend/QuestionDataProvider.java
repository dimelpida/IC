package com.mindcastle.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionDataProvider {
    private Map<String, List<Question>> questionsByMonument;
    private List<Monument> monuments;
    private static QuestionDataProvider instance;

    private QuestionDataProvider() {
        initializeMonuments();
        initializeQuestions();
    }

    public static QuestionDataProvider getInstance() {
        if (instance == null) {
            instance = new QuestionDataProvider();
        }
        return instance;
    }

    private void initializeMonuments() {
        monuments = new ArrayList<>();

        // Προσθέτουμε τα βασικά μνημεία του Κάστρου των Ιωαννίνων
        monuments.add(new Monument(
                "aslan_pasha",
                "Aslan Pasha Mosque",
                "The Aslan Pasha Mosque was built in 1618 by Aslan Pasha, then governor of the region. Located on the northeastern side of the Castle of Ioannina, it is characterized by its impressive minaret. Today, the mosque functions as the Municipal Museum, preserving the history and culture of the city.\n",
                39.67362016161929, 20.860332890091314,
                "aslan_pasha_moque"
        ));

        monuments.add(new Monument(
                "its_kale",
                "Its Kale",
                "Its Kale is the inner acropolis of the Castle of Ioannina, constructed in the 13th century by Byzantine emperor Andronikos II Palaiologos. The name means \"Inner Fortress\" in Ottoman. During the Ottoman era, it served as a barracks, administrative center, and prison. Inside, you'll find the Seraglio of Ali Pasha, a landmark of historical significance.\n",
                39.6711134399187, 20.862392259089965,
                "its_kale"
        ));

        monuments.add(new Monument(
                "fethiye_mosque",
                "Fethiye Mosque",
                "Originally a Byzantine church, the Fethiye Mosque was converted into a mosque during the 15th century, following the Ottoman conquest. It was named \"Fethiye\" by Sultan Mehmed II. Its most prominent architectural element is the dome, and it stands as a testament to the cultural transformations of the time.",
                39.67068965160131, 20.863252276482367,
                "fethiye_mosque"
        ));

        monuments.add(new Monument(
                "treasury",
                "Treasury",
                "This building served as the personal treasury of Ali Pasha, housing gold, heirlooms, and weapons. Located next to the Seraglio inside Its Kale, it also functioned as a refuge during the Greek Revolution. Excavations revealed numerous valuable artifacts, emphasizing Ali Pasha’s immense wealth and power.",
                39.67114991068991, 20.861964165525343,
                "treasury"
        ));

        monuments.add(new Monument(
                "byzantine_museum",
                "Byzantine Museum",
                "The Byzantine Museum highlights the art and cultural heritage of the Byzantine period. It is best known for its collection of icons, the museum’s most valuable exhibits. Located next to Its Kale, it showcases items from the 5th to the 15th century, offering visitors a glimpse into centuries of history.",
                39.6704359960328, 20.862766004961284,
                "byzantine_museum"
        ));
    }

    private void initializeQuestions() {
        questionsByMonument = new HashMap<>();

        // Ερωτήσεις για το Τζαμί του Ασλάν Πασά
        List<Question> aslanPashaQuestions = new ArrayList<>();
        aslanPashaQuestions.add(new Question(
                "aslan_q1",
                "What year was the Aslan Pasha Mosque built?",
                Arrays.asList("1618", "1650", "1700", "1550"),
                0,
                "aslan_pasha",
                "drawable/aslan_pasha_q1",
                "The Aslan Pasha Mosque was built in 1618 to honor Aslan Pasha, the governor of the region."
        ));
        aslanPashaQuestions.add(new Question(
                "aslan_q2",
                "Who built the Aslan Pasha Mosque?",
                Arrays.asList("Ali Pasha", "Aslan Pasha", "Suleiman the Magnificent", "Muhammad II"),
                1,
                "aslan_pasha",
                "drawable/aslan_pasha_q2",
                "The Mosque was built by Aslan Pasha, the governor of the region during the Ottoman period."
        ));
        aslanPashaQuestions.add(new Question(
                "aslan_q3",
                "What is housed today in the Aslan Pasha Mosque?",
                Arrays.asList("Town hall", "Restaurant", "Municipal Museum", "Library"),
                2,
                "aslan_pasha",
                "drawable/aslan_pasha_q3",
                "The Mosque of Aslan Pasha now operates as the Municipal Museum of the city of Ioannina."
        ));
        aslanPashaQuestions.add(new Question(
                "aslan_q4",
                "Where in the Castle is the Aslan Pasha Mosque located?",
                Arrays.asList("Northeast", "Southwest", "Northwest", "Southeast"),
                0,
                "aslan_pasha",
                "drawable/aslan_pasha_q4",
                "The Mosque is located on the northeastern side of the Castle of Ioannina."
        ));
        aslanPashaQuestions.add(new Question(
                "aslan_q5",
                "What architectural element characterizes the Aslan Pasha Mosque?",
                Arrays.asList("Belfry", "Dome", "Minaret", "Arch"),
                2,
                "aslan_pasha",
                "drawable/aslan_pasha_q5",
                "The Minaret is the most characteristic architectural element of the Mosque."
        ));
        questionsByMonument.put("aslan_pasha", aslanPashaQuestions);

        // Ερωτήσεις για το Ιτς Καλέ
        List<Question> itsKaleQuestions = new ArrayList<>();
        itsKaleQuestions.add(new Question(
                "its_q1",
                "When was Its Kale built?",
                Arrays.asList("13th century", "15th century", "17th century", "19th century"),
                0,
                "its_kale",
                "drawable/its_kale_q1",
                "Its Kale was built in the 13th century, during the Byzantine period."
        ));
        itsKaleQuestions.add(new Question(
                "its_q2",
                "Who originally built Its Kale?",
                Arrays.asList("Ali Pasha", "Sultan Mehmet", "Andronikos II Palaiologos", "Theodore Komnenos"),
                2,
                "its_kale",
                "drawable/its_kale_q2",
                "Its Kale was built by Andronikos II Palaiologos during the Byzantine era."
        ));
        itsKaleQuestions.add(new Question(
                "its_q3",
                "What does the term 'Its Kale' mean?",
                Arrays.asList("Great Castle", "Inner Fortress", "High Walls", "Emperor's Fortress"),
                1,
                "its_kale",
                "drawable/its_kale_q3",
                "The term 'Its Kale' means 'Inner Fortress' in Ottoman."
        ));
        itsKaleQuestions.add(new Question(
                "its_q4",
                "What was the use of Its Kale during the Ottoman period?",
                Arrays.asList("Barrack", "Administrative center", "Prisons", "All of the above"),
                3,
                "its_kale",
                "drawable/its_kale_q4",
                "During the Ottoman period, Its Kale had multiple uses, including barracks, administrative center, and prison."
        ));
        itsKaleQuestions.add(new Question(
                "its_q5",
                "What important building is located inside Its Kale?",
                Arrays.asList("The Byzantine Museum", "The Seraglio of Ali Pasha", "The Library of Ioannina", "The City Hall"),
                1,
                "its_kale",
                "drawable/its_kale_q5",
                "The Seraglio of Ali Pasha is the most important building within Its Kale."
        ));
        questionsByMonument.put("its_kale", itsKaleQuestions);


        List<Question> fethiyeMosqueQuestions = new ArrayList<>();
        fethiyeMosqueQuestions.add(new Question(
                "fethiye_q1",
                "What was the original use of the Fethiye Mosque?",
                Arrays.asList("Byzantine church", "Museum", "Barrack", "Residence"),
                0,
                "fethiye_mosque",
                "drawable/fethiye_mosque_q1",
                "The Fethiye Mosque was originally a Byzantine church before being converted into a mosque during the Ottoman period."
        ));
        fethiyeMosqueQuestions.add(new Question(
                "fethiye_q2",
                "When was it converted into a mosque?",
                Arrays.asList("14th century", "15th century", "16th century", "17th century"),
                1,
                "fethiye_mosque",
                "drawable/fethiye_mosque_q2",
                "The Fethiye Mosque was converted into a mosque in the 15th century during the Ottoman conquest."
        ));
        fethiyeMosqueQuestions.add(new Question(
                "fethiye_q3",
                "Who gave the name 'Fetihiye' to the mosque?",
                Arrays.asList("Sultan Mehmed II", "Ali Pasha", "Aslan Pasha", "Muhammad II"),
                0,
                "fethiye_mosque",
                "drawable/fethiye_mosque_q3",
                "The name 'Fetihiye' was given to the mosque by Sultan Mehmet II after the conquest of Ioannina."
        ));
        fethiyeMosqueQuestions.add(new Question(
                "fethiye_q4",
                "What is the characteristic architecture of the Fethiye Mosque?",
                Arrays.asList("Dome", "Arch", "Minaret", "Belfry"),
                0,
                "fethiye_mosque",
                "drawable/fethiye_mosque_q4",
                "The dome is a characteristic element of the architecture of the Fethiye Mosque."
        ));
        fethiyeMosqueQuestions.add(new Question(
                "fethiye_q5",
                "What event contributed to the Ottoman conquest of the region?",
                Arrays.asList("The Battle of Preveza", "The Paris Agreement", "The siege of Ioannina", "The campaign of Mehmed II"),
                3,
                "fethiye_mosque",
                "drawable/fethiye_mosque_q5",
                "Mehmed II's campaign was decisive for the Ottoman conquest of the region."
        ));
        questionsByMonument.put("fethiye_mosque", fethiyeMosqueQuestions);

        List<Question> treasuryQuestions = new ArrayList<>();
        treasuryQuestions.add(new Question(
                "treasury_q1",
                "What was the purpose of Ali Pasha's treasury?",
                Arrays.asList("Gold storage", "Shopping center", "Public market", "Residence"),
                0,
                "treasury",
                "drawable/treasury_q1",
                "Ali Pasha's treasury was used to store gold and valuable objects."
        ));
        treasuryQuestions.add(new Question(
                "treasury_q2",
                "Where was the treasury located inside Its Kale?",
                Arrays.asList("Next to the mosque", "In the center of the acropolis", "Near the entrance", "Next to the Serai"),
                3,
                "treasury",
                "drawable/treasury_q2",
                "The treasury was located next to the Seraglio of Ali Pasha in Its Kale."
        ));
        treasuryQuestions.add(new Question(
                "treasury_q3",
                "What objects were found during the excavations in the treasury?",
                Arrays.asList("\n" +
                        "Gold coins", "Heirlooms", "Weapons of war", "All of the above"),
                3,
                "treasury",
                "drawable/treasury_q3",
                "During the excavations, gold coins, relics and war weapons were found."
        ));
        treasuryQuestions.add(new Question(
                "treasury_q4",
                "What was the use of the treasury during the Greek Revolution?",
                Arrays.asList("Weapons depot", "Refuge", "Administrative center", "It was not used"),
                1,
                "treasury",
                "drawable/treasury_q4",
                "During the Greek Revolution, the treasury was used as a refuge by Ali Pasha.\n"
        ));
        treasuryQuestions.add(new Question(
                "treasury_q5",
                "How did Ali Pasha manage his wealth?",
                Arrays.asList("Public market", "Private vault", "Disposition to soldiers", "Funding of monuments"),
                1,
                "treasury",
                "drawable/treasury_q5",
                "Ali Pasha managed his wealth through a private treasury and strategic investments."
        ));
        questionsByMonument.put("treasury", treasuryQuestions);

        // Ερωτήσεις για το Βυζαντινό Μουσείο
        List<Question> byzantineMuseumQuestions = new ArrayList<>();
        byzantineMuseumQuestions.add(new Question(
                "byzantine_q1",
                "What is the main theme of the Byzantine Museum?",
                Arrays.asList("Ancient history", "Byzantine art", "Ottoman period", "Modern Greek art"),
                1,
                "byzantine_museum",
                "drawable/byzantine_museum_q1",
                "The main theme of the Byzantine Museum is Byzantine art and cultural heritage."
        ));
        byzantineMuseumQuestions.add(new Question(
                "byzantine_q2",
                "What is the museum's most important collection?",
                Arrays.asList("Icons", "Gold items", "Currency", "Photographs"),
                0,
                "byzantine_museum",
                "drawable/byzantine_museum_q2",
                "The icons constitute the most important collection of the Byzantine Museum"
        ));
        byzantineMuseumQuestions.add(new Question(
                "byzantine_q3",
                "Where is the Byzantine Museum located inside the Castle?",
                Arrays.asList("Near Its Kale", "Next to Near Fethiye Mosque in Its Kale", "In the center of the Castle", "Near the lake"),
                0,
                "byzantine_museum",
                "drawable/byzantine_museum_q3",
                "The Byzantine Museum is located next to Its Kale, inside the Castle of Ioannina."
        ));
        byzantineMuseumQuestions.add(new Question(
                "byzantine_q4",
                "What time period do the museum's exhibits cover?",
                Arrays.asList("5th-15th century", "10th-20th century", "8th-18th century", "3rd-13th century"),
                0,
                "byzantine_museum",
                "drawable/byzantine_museum_q4",
                "The exhibits of the Byzantine Museum cover the period from the 5th to the 15th century."
        ));
        byzantineMuseumQuestions.add(new Question(
                "byzantine_q5",
                "What is the most popular exhibit at the museum?",
                Arrays.asList("Collection of images", "Ancient relics", "War items", "Ottoman art"),
                0,
                "byzantine_museum",
                "drawable/byzantine_museum_q5",
                "The Icon Collection is the most popular exhibition of the Byzantine Museum."
        ));
        questionsByMonument.put("byzantine_museum", byzantineMuseumQuestions);
    }


    public List<Monument> getMonuments() {
        return monuments;
    }

    public List<Question> getQuestionsForMonument(String monumentId) {
        return questionsByMonument.get(monumentId);
    }

    public Monument getMonumentById(String monumentId) {
        for (Monument monument : monuments) {
            if (monument.getId().equals(monumentId)) {
                return monument;
            }
        }
        return null;
    }
}