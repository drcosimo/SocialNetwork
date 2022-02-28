import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CentralServiceService } from '../services/central-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private service: CentralServiceService) { }
  formLogin !: FormGroup;

  ngOnInit(): void {
    this.formLogin = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('',Validators.required)
    });
  }

  login(){
    // salvo le credenziali dal form  
    const cred = {
        email: this.formLogin.get('email')?.value,
        password : this.formLogin.get('password')?.value
      }
    // effettuo la richiesta al servizio rest per la validazione delle credenziali
    this.service.login(cred).subscribe(
      response=>{
        this.router.navigateByUrl('homepage');
      },
      error=>{
        console.log("fai schifo");
      }
    );
    
    }

}
