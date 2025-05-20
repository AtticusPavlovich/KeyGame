
import java.util.Scanner;

public class MarvelRivals {
    public static void main(String[] args) {

        String[] characters = {
            "Adam Warlock", "Black Panther", "Black Widow", "Captain America", 
            "Cloak and Dagger", "Doctor Strange", "Groot", "Hawkeye", "Hela", 
            "Hulk", "Human Torch", "Invisible Woman", "Iron Fist", "Iron Man", 
            "Jeff the Land Shark", "Loki", "Luna Snow", "Magik", "Mantis", 
            "Mister Fantastic", "Moon Knight", "Namor", "Peni Parker", 
            "Psylocke", "The Punisher", "The Thing", "Rocket Raccoon", 
            "Scarlet Witch", "Squirrel Girl", "Spider Man", "Star Lord", 
            "Storm", "Thor", "Venom", "Winter Soldier", "Wolverine"
        };

        Scanner scanner1 = new Scanner(System.in);
        System.out.print("How many do you want generated? ");
        int numgenerated = scanner1.nextInt();
        scanner1.close();

        for (int i = numgenerated; i > 0; i--) {
            int randomnum = (int)(Math.random() * characters.length); 
            
            System.out.println(characters[randomnum]);
        }

    }
}