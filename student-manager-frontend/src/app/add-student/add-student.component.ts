import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Router, RouterLink} from '@angular/router';
import {FormsModule} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-add-student',
  standalone: true,
  imports: [
    FormsModule,
    MatButton,
    RouterLink,
    NgIf
  ],
  templateUrl: './add-student.component.html',
  styleUrl: './add-student.component.css'
})
export class AddStudentComponent {
  student: any = {
    name: '',
    contactDetails: '',
    address: '',
    pincode: ''
  };

  validationErrors: any = {};

  constructor(private http: HttpClient, private router: Router) {}

  addStudent(): void {
    this.http.post('http://localhost:8080/api/students', this.student).subscribe(
      () => {
        console.log('Student added successfully');
        this.router.navigate(['/students']);
      },
      (error) => {
        if (error.status === 400) {
          this.validationErrors = error.error;
        } else {
          console.error('Error adding student:', error);
        }
      }
    );
  }
}
