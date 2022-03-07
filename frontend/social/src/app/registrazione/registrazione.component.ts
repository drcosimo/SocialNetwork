import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistraUser } from 'src/registraUser';
import { User } from 'src/user';
import { CentralServiceService } from '../services/central-service.service';

@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrls: ['./registrazione.component.css']
})
export class RegistrazioneComponent implements OnInit {

  constructor(private service:CentralServiceService, private router:Router) { }

  formRegistrazione !: FormGroup;
  newUser : RegistraUser;

  errore: string ="";
  
  ngOnInit(): void {
    this.formRegistrazione = new FormGroup({
      nickname: new FormControl('', Validators.required),
      nome: new FormControl('', Validators.required),
      cognome: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      dataNascita: new FormControl('', Validators.required)
    });
  }

  registrazione(){
    // salvo le credenziali
    this.newUser = {
      nickname : this.formRegistrazione.get('nickname')?.value,
      nome : this.formRegistrazione.get('nome')?.value,
      cognome : this.formRegistrazione.get('cognome')?.value,
      email : this.formRegistrazione.get('email')?.value,
      password : this.formRegistrazione.get('password')?.value,
      dataNascita : this.formRegistrazione.get('dataNascita')?.value
    };

    this.service.registrazione(this.newUser).subscribe(
      (next)=>{
         // se il login avviene con successo
         if (next.status == 201) {
           
          // accedo alla homepage
          this.router.navigateByUrl('login');
        }
      },(error)=>{
          // visualizzo errore
        this.errore = "registrazione fallita";
      }
      );
  }
}
