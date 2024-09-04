package org.example.studentmanager.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.studentmanager.model.Student;
import org.example.studentmanager.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

/**
 * @author vero-git-hub
 **/
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setId("12345");
        student.setName("John Doe");
        student.setContactDetails("john@example.com");
        student.setAddress("123 Main St");
        student.setPincode("12345");
    }

    @Test
    public void testGetStudentById() {
        when(studentRepository.findById("12345")).thenReturn(Optional.of(student));
        Optional<Student> foundStudentOptional = studentService.getStudentById("12345");

        assertTrue(foundStudentOptional.isPresent());
        Student foundStudent = foundStudentOptional.get();
        assertEquals("John Doe", foundStudent.getName());
        verify(studentRepository, times(1)).findById("12345");
    }

    @Test
    public void testCreateStudent() {
        when(studentRepository.save(student)).thenReturn(student);
        Student createdStudent = studentService.addStudent(student);

        assertNotNull(createdStudent);
        assertEquals("John Doe", createdStudent.getName());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testDeleteStudent() {
        studentService.deleteStudent("12345");
        verify(studentRepository, times(1)).deleteById("12345");
    }
}
