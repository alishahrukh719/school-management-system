package com.example.studentapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/students", "/students" })
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    void createSampleStudent() {
        if (!studentRepository.existsById("sample-id")) {
            Student sample = new Student();
            sample.setId("sample-id");
            sample.setName("Sample Student");
            sample.setEmail("sample@example.com");
            studentRepository.save(sample);
        }
    }

    @GetMapping
    public List<Student> listStudents() {
        return new ArrayList<>(studentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        return studentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return createStudentFromModel(student);
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<Student> createStudentFromForm(@RequestParam Map<String, String> fields) {
        return createStudentFromModel(studentFromFields(fields));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> replaceStudent(@PathVariable String id, @RequestBody Student student) {
        return replaceStudentFromModel(id, student);
    }

    @PutMapping(path = "/{id}", consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<Student> replaceStudentFromForm(
            @PathVariable String id,
            @RequestParam Map<String, String> fields) {
        return replaceStudentFromModel(id, studentFromFields(fields));
    }

    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student) {
        return updateStudentFromModel(id, student);
    }

    @PatchMapping(path = "/{id}", consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<Student> updateStudentFromForm(
            @PathVariable String id,
            @RequestParam Map<String, String> fields) {
        return updateStudentFromModel(id, studentFromFields(fields));
    }

    private ResponseEntity<Student> createStudentFromModel(Student student) {
        String id = student.getId();
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
        }
        student.setId(id);
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    private ResponseEntity<Student> replaceStudentFromModel(String id, Student student) {
        boolean exists = studentRepository.existsById(id);
        student.setId(id);
        Student savedStudent = studentRepository.save(student);
        return exists
                ? ResponseEntity.ok(savedStudent)
                : ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    private ResponseEntity<Student> updateStudentFromModel(String id, Student student) {
        Student existing = studentRepository.findById(id).orElseGet(Student::new);
        boolean exists = existing.getId() != null;
        existing.setId(id);
        if (student.getName() != null) {
            existing.setName(student.getName());
        }
        if (student.getEmail() != null) {
            existing.setEmail(student.getEmail());
        }
        Student savedStudent = studentRepository.save(existing);
        return exists
                ? ResponseEntity.ok(savedStudent)
                : ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    private Student studentFromFields(Map<String, String> fields) {
        Student student = new Student();
        student.setId(fields.get("id"));
        student.setName(fields.get("name"));
        student.setEmail(fields.get("email"));
        return student;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
