package org.example.studentmanager.controller;

import org.example.studentmanager.exception.ResourceNotFoundException;
import org.example.studentmanager.model.Student;
import org.example.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student) {
        return studentService.getStudentById(id)
                .map(existingStudent -> ResponseEntity.ok(studentService.updateStudent(id, student)))
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        return studentService.getStudentById(id)
                .map(existingStudent -> {
                    studentService.deleteStudent(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }
}
