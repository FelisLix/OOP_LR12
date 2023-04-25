package lr12.oop_lr12.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lr12.oop_lr12.data.Abiturient;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AbiturientService {

    @Getter
    private List<Abiturient> abiturients;

    @PostConstruct
    public void init() {
        try (BufferedReader reader = Files.newBufferedReader(Path.of("abiturients.txt"))) {
            abiturients = new ArrayList<>(reader.lines().map(line -> {
                String[] s = line.split(";");
                int id = Integer.parseInt(s[0]);
                String surname = s[1];
                String name = s[2];
                String fname = s[3];
                String address = s[4];
                int phone = Integer.parseInt(s[5]);
                double mark = Double.parseDouble(s[6]);
                return new Abiturient(id, surname, name, fname,address, phone, mark);
            }).toList());
        } catch (IOException e) {
            abiturients = new ArrayList<>();
        }
    }

    public void AbiturientsName(String name){
        ArrayList<Abiturient> abs = new ArrayList<>();
        for (Abiturient abiturient : abiturients) {
            if (abiturient.getName().equals(name)){
                abs.add(abiturient);
            }
        }
        abiturients.clear();
        abiturients.addAll(abs);
    }

    public void AbiturientsMarkHigherThen(double mark){
        ArrayList<Abiturient> abs = new ArrayList<>();
        for (Abiturient abiturient : abiturients) {
            if (abiturient.getMark() > mark){
                abs.add(abiturient);
            }
        }
        abiturients.clear();
        abiturients.addAll(abs);
    }

    public void AbiturientsWithHigherMark(int n){
        ArrayList<Abiturient> abs = new ArrayList<>();
        abiturients.sort(Comparator.comparingDouble(Abiturient::getMark).reversed());
        for (int i = 0; i < n; i++){
            abs.add(abiturients.get(i));
        }
        abiturients.clear();
        abiturients.addAll(abs);
    }

    public void add(Abiturient abiturient) {
        abiturients.add(abiturient);
    }
    public void deleteById(int id) {
        abiturients.removeIf(s -> s.getID() == id);
    }
    public void save() {
        try (PrintWriter out = new PrintWriter("abiturients.txt")) {
            for (Abiturient s : abiturients) {
                out.println(s.getID() + ";" + s.getSurname() + ";" + s.getName() + ";" + s.getFName() + ";" + s.getAddress() + ";" + s.getPhone() + ";" + s.getMark());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
