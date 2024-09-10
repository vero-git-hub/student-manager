import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.css'
})
export class StudentListComponent implements OnInit {
  students: any[] = [];

  constructor(private http: HttpClient) { }

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
}
