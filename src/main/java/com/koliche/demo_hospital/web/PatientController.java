package com.koliche.demo_hospital.web;

import com.koliche.demo_hospital.entities.Patient;
import com.koliche.demo_hospital.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "4") int size,
                        @RequestParam(defaultValue = "") String keyword){
        Page<Patient> pagePatient = patientRepository.findByNameContains(keyword, PageRequest.of(page, size));
        model.addAttribute("patientList", pagePatient.getContent());
        model.addAttribute("pages", new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patient";
    }

    @GetMapping("/delete")
    public String delete(Long id,
                         @RequestParam(defaultValue = "") String keyword,
                         @RequestParam(defaultValue = "0") int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page=%d&keyword=%s".formatted(page, keyword);
    }

    @GetMapping("/formPatient")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }

    @PostMapping("/savePatient")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {return "formPatient";}

        patientRepository.save(patient);
        return "redirect:/index?keyword=%s".formatted(patient.getName());
    }

    @GetMapping("/editPatient")
    public String editPatient(Model model, @RequestParam Long id){
        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient", patient);
        return "editPatient";
    }




}
