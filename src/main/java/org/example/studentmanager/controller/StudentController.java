package org.example.studentmanager.controller;

import jakarta.validation.Valid;
import org.example.studentmanager.exception.ResourceNotFoundException;
import org.example.studentmanager.model.Student;
import org.example.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vero-git-hub
 **/
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @Valid @RequestBody Student student) {
        return studentService.getStudentById(id)
                .map(existingStudent -> ResponseEntity.ok(studentService.updateStudent(id, student)))
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable String id) {
        return studentService.getStudentById(id)
                .map(existingStudent -> {
                    studentService.deleteStudent(id);
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Student with id " + id + " was successfully deleted.");
                    return ResponseEntity.ok(response);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }
}
