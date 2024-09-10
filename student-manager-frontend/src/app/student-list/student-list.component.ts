import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import {MatIconButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [MatTableModule, MatIconButton, MatIcon, NgIf, RouterLink],
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.css'
})
export class StudentListComponent implements OnInit {
  students: any[] = [];

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.getStudents();
  }

  getStudents(): void {
    this.http.get<any[]>('http://localhost:8080/api/students').subscribe(
      (response) => {
        this.students = response;
      },
      (error) => {
        console.error('Error fetching students:', error);
      }
    );
  }

  editStudent(student: any): void {
    this.router.navigate(['/edit-student', student.id]);
  }

  deleteStudent(student: any): void {
    if (confirm(`Are you sure you want to delete ${student.name}?`)) {
      this.http.delete(`http://localhost:8080/api/students/${student.id}`).subscribe(
        () => {
          this.students = this.students.filter(s => s.id !== student.id);
          alert('Student deleted successfully');
        },
        (error) => {
          console.error('Error deleting student:', error);
        }
      );
    }
  }

}
