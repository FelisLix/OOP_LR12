package lr12.oop_lr12.controller;

import lombok.AllArgsConstructor;
import lr12.oop_lr12.data.Abiturient;
import lr12.oop_lr12.service.AbiturientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AbiturientController {
    private AbiturientService abiturientService;

    @GetMapping("/abiturients")
    public String showAbiturient(Model model) {
        model.addAttribute("abiturients", abiturientService.getAbiturients());
        return "abiturients";
    }

    @PostMapping("/add_abiturient")
    public String addStudent(
            @RequestParam("abiturient_id") int id,
            @RequestParam("abiturient_surname") String surname,
            @RequestParam("abiturient_name") String name,
            @RequestParam("abiturient_fname") String fname,
            @RequestParam("abiturient_address") String address,
            @RequestParam("abiturient_phone") int phone,
            @RequestParam("abiturient_mark") double mark) {
        Abiturient abiturient = new Abiturient(id, surname, name, fname, address, phone, mark);
        abiturientService.add(abiturient);
        return "redirect:/abiturients";
    }

    @GetMapping("/delete_abiturient")
    public String deleteAbs(@RequestParam int id) {
        abiturientService.deleteById(id);
        return "redirect:/abiturients";
    }

    @GetMapping("/save_abiturient")
    public String saveAbs() {
        abiturientService.save();
        return "redirect:/abiturients";
    }
    @GetMapping("/show_again_abiturient")
    public String showAbs() {
        abiturientService.init();
        return "redirect:/abiturients";
    }

    @PostMapping("/NAME_abiturient")
    public String showByName(@RequestParam("NAME_abiturient") String name) {
        abiturientService.AbiturientsName(name);
        return "redirect:/abiturients";
    }

    @PostMapping("/MARK_abiturient")
    public String showByMark(@RequestParam("MARK_abiturient") double mark) {
        abiturientService.AbiturientsMarkHigherThen(mark);
        return "redirect:/abiturients";
    }

    @PostMapping("/N_abiturient")
    public String showN(@RequestParam("N_abiturient") int n) {
        abiturientService.AbiturientsWithHigherMark(n);
        return "redirect:/abiturients";
    }

}
