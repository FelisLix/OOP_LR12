package lr12.oop_lr12.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Abiturient  {
    private int ID;
    private String Surname;
    private String Name;
    private String FName;
    private String Address;
    private int Phone;
    private double Mark;

}
