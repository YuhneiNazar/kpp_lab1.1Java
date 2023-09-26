import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Toy {
    private String name;
    private int price;
    private String ageRange;
    private String specialAttribute;

    public Toy(String name, int price, String ageRange, String specialAttribute) {
        this.name = name;
        this.price = price;
        this.ageRange = ageRange;
        this.specialAttribute = specialAttribute;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public String getSpecialAttribute() {
        return specialAttribute;
    }
}

class ToyInfo {
    public static void main(String[] args) {
        List<Toy> toys = readFile("toys.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть діапазон років: ");
        String dipyears = scanner.nextLine();
        String condipyears = dipyears + " years";

        toys.stream()
                .filter(t -> t.getName().equals("konstruktor") && t.getAgeRange().equals(condipyears))
                .sorted((t1, t2) -> Integer.compare(t2.getPrice(), t1.getPrice()))
                .forEach(toy -> {
                    int priceInGrn = toy.getPrice() / 100;
                    int priceInKop = toy.getPrice() % 100;
                    System.out.printf("%s - %d грн и %02d коп. кількість: %s%n",
                            toy.getName(), priceInGrn, priceInKop, toy.getSpecialAttribute());
                });
    }

    public static List<Toy> readFile(String fileName) {
        List<Toy> toys = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                toys.add(new Toy(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return toys;
    }
}
