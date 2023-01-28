import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long younger18 = persons.stream().filter(personAge -> (personAge.getAge() < 18)).count();
        List<String> mansForArmy = persons.stream()
                .filter(mansSex -> (mansSex.getSex().equals(Sex.MAN)))
                .filter(mansAge -> (mansAge.getAge() >= 18 && mansAge.getAge() <=27))
                .map(Person::getFamily)
                .toList();
        List<Person> personsCanWork = persons.stream()
                .filter(personsEducation -> personsEducation.getEducation().equals(Education.HIGHER))
                .filter(ageForWork -> (ageForWork.getSex().equals(Sex.MAN) && ageForWork.getAge() >= 18 && ageForWork.getAge() < 65
                                    || ageForWork.getSex().equals(Sex.WOMAN) && ageForWork.getAge() >= 18 && ageForWork.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();

        System.out.println(personsCanWork); //проверка сортировки по фамилии
        System.out.println(younger18);
        System.out.println(mansForArmy.size());
        System.out.println(personsCanWork.size());
    }
}
