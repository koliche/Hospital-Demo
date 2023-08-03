package com.koliche.demo_hospital.web;

import com.koliche.demo_hospital.entities.Patient;
import com.koliche.demo_hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        return "Patient";
    }

    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page=%d&keyword=%s".formatted(page, keyword);
    }





}
