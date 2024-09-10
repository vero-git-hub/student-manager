import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {NgIf} from "@angular/common";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-student-detail',
  standalone: true,
  imports: [
    NgIf,
    MatButton,
    RouterLink
  ],
  templateUrl: './student-detail.component.html',
  styleUrl: './student-detail.component.css'
})
export class StudentDetailComponent implements OnInit {
  student: any;

  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const studentId = this.route.snapshot.paramMap.get('id');
    if (studentId) {
      this.getStudentDetails(studentId);
    }
  }

  getStudentDetails(id: string): void {
    this.http.get<any>(`http://localhost:8080/api/students/${id}`).subscribe(
      (response) => {
        this.student = response;
      },
      (error) => {
        console.error('Error fetching student details:', error);
      }
    );
  }
}
