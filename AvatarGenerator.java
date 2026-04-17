package avatergenerator;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class AvatarGenerator {
    // ===================== Hash Engine =====================
    static class HashEngine {
        private final String canonicalStr;
        private final int hashValue;
        public HashEngine(String name, int age, String gender, String country, String city) {
            this.canonicalStr = (name.trim().toLowerCase() + age + gender.trim().toLowerCase() + country.trim().toLowerCase() + city.trim().toLowerCase());
            this.hashValue = generateHash();
        }
        private int generateHash() {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] bytes = md.digest(canonicalStr.getBytes(StandardCharsets.UTF_8));
                int result = ((bytes[0] & 0xFF) << 24) | ((bytes[1] & 0xFF) << 16) | ((bytes[2] & 0xFF) << 8) |(bytes[3] & 0xFF);
                return Math.abs(result);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return canonicalStr.hashCode();
            }
        }
        public int getHashValue() {
            return hashValue;
        }
    }
    // ===================== Appearance Generator =====================
    static class AppearanceGenerator {
        private static final String[] hairColors = {"Black", "Brown", "Blonde", "Red", "Grey"};
        private static final String[] hairLengths = {"Short", "Medium", "Long", "Bald"};
        private static final String[] eyeColors = {"Brown", "Blue", "Green", "Hazel", "Grey"};
        private static final String[] faceShapes = {"Oval", "Round", "Square", "Heart", "Diamond"};
        private static final String[] heights = {"Short", "Average", "Tall"};
        private static final String[] clothingStyles = {"Casual", "Formal", "Sporty", "Traditional", "Fantasy"};
        private final int hashValue;
        public AppearanceGenerator(int hashValue) {
            this.hashValue = hashValue;
        }
        private String selectTrait(String[] traitBank, int segment) {
            int index = segment % traitBank.length;
            return traitBank[index];
        }
        public Appearance generateAppearance() {
            int seed = hashValue;
            String hairColor = selectTrait(hairColors, seed % 100);
            String hairLength = selectTrait(hairLengths, (seed / 100) % 100);
            String eyeColor = selectTrait(eyeColors, (seed / 10000) % 100);
            String faceShape = selectTrait(faceShapes, (seed / 1000000) % 100);
            String height = selectTrait(heights, (seed / 100000000) % 100);
            String clothing = selectTrait(clothingStyles, (int) ((seed / 10000000000L) % 100));
            return new Appearance(hairColor, hairLength, eyeColor, faceShape, height, clothing);
        }
        static class Appearance {
            public String hairColor, hairLength, eyeColor, faceShape, height, clothing;
            public Appearance(String hairColor, String hairLength, String eyeColor, String faceShape, String height, String clothing) {
                this.hairColor = hairColor;
                this.hairLength = hairLength;
                this.eyeColor = eyeColor;
                this.faceShape = faceShape;
                this.height = height;
                this.clothing = clothing;
            }
            @Override
            public String toString() {
                return "Appearance [Hair: " + hairColor + ", Length: " + hairLength + ", Eyes: " + eyeColor + ", Face: " + faceShape + ", Height: " + height + ", Clothing: " + clothing + "]";
            }
        }
    }
    // ===================== Archetype Generator =====================
    static class ArchetypeGenerator {
        private static final String[] archetypes = {"Strategist", "Artist", "Analyst", "Explorer", "Guardian"};
        private final int hashValue;
        public ArchetypeGenerator(int hashValue) {
            this.hashValue = hashValue;
        }
        public String generateArchetype() {
            int index = hashValue % archetypes.length;
            return archetypes[index];
        }
    }
    // ===================== Main Method =====================
    public static void main(String[] args) {
        String name = "Alice";
        int age = 25;
        String gender = "Female";
        String country = "Pakistan";
        String city = "Islamabad";
        HashEngine hashEngine = new HashEngine(name, age, gender, country, city);
        int seed = hashEngine.getHashValue();
        System.out.println("Deterministic Hash: " + seed);
        AppearanceGenerator appearanceGen = new AppearanceGenerator(seed);
        AppearanceGenerator.Appearance appearance = appearanceGen.generateAppearance();
        System.out.println(appearance);
        ArchetypeGenerator archetypeGen = new ArchetypeGenerator(seed);
        String archetype = archetypeGen.generateArchetype();
        System.out.println("Archetype: " + archetype);
    }
}
