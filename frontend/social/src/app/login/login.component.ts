import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/user';
import { CentralServiceService } from '../services/central-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private service: CentralServiceService) { }
  formLogin !: FormGroup;
  errore: string ="";
  cred:User = {
    "nickname":"",
    "password":""
  }
  ngOnInit(): void {
    this.formLogin = new FormGroup({
      nickname: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  login() {
    // salvo le credenziali dal form  
    this.cred= {
      nickname: this.formLogin.get('nickname')?.value,
      password: this.formLogin.get('password')?.value
    }
    // effettuo la richiesta al servizio rest per la validazione delle credenziali
    this.service.login(this.cred).subscribe(
      (next)=> { 
        // se il login avviene con successo
        if (next.status == 200) {
          // accedo alla homepage
          this.router.navigateByUrl('private');
        }
      },
      (error) => {
        // visualizzo errore
        this.errore = "credenziali invalide";
      }
    );
  }
}
