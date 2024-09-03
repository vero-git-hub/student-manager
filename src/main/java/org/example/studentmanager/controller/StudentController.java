package org.example.studentmanager.controller;

import jakarta.validation.Valid;
import org.example.studentmanager.exception.ResourceNotFoundException;
import org.example.studentmanager.model.Student;
import org.example.studentmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

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

    @Operation(summary = "Get list of all students", description = "Returns a list of all students stored in the database.")
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Operation(summary = "Get student data", description = "Returns student data by ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @Parameter(description = "ID of the student to find")
            @PathVariable String id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @Operation(summary = "Register new student", description = "Creates a record for a new student and saves it to the database.")
    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @Operation(summary = "Update student data", description = "Updates data of an existing student in the database.")
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @Parameter(description = "ID of the student whose data needs to be updated")
            @PathVariable String id,
            @Valid @RequestBody Student student) {
        return studentService.getStudentById(id)
                .map(existingStudent -> ResponseEntity.ok(studentService.updateStudent(id, student)))
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @Operation(summary = "Delete student", description = "Deletes a student from the database by ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteStudent(
            @Parameter(description = "ID of the student to delete")
            @PathVariable String id) {
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
