package com.example.demo;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Entity
class Project {
    @Id
    int id = 9;

    String name = "spring";
    String description = "Spring Boot Project";
    LocalDate date = LocalDate.of(2026, 5, 2);
    boolean status = true;
    String technology = "Spring Boot";
}

interface ProjectRepo extends JpaRepository<Project, Integer> {
}

@Service
class ProjectService {
    @Autowired
    ProjectRepo repo;

    Project insert() {
        Project p = new Project();
        return repo.save(p);
    }

    Project view(int id) {
        return repo.findById(id).get();
    }
}

@RestController
@RequestMapping("/project")
class ProjectController {
    @Autowired
    ProjectService service;

    @PostMapping("/insert")
    String insert() {
        service.insert();
        return "Project Created Successfully";
    }

    @GetMapping("/view/{id}")
    Project view(@PathVariable int id) {
        return service.view(id);
    }
}