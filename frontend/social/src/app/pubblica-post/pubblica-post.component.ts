import { Component, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CentralServiceService } from '../services/central-service.service';

@Component({
  selector: 'app-pubblica-post',
  templateUrl: './pubblica-post.component.html',
  styleUrls: ['./pubblica-post.component.css']
})
export class PubblicaPostComponent implements OnInit {

  formCreaPost !: FormGroup;
  sub !: Subscription;
  messaggio !: string;

  constructor(private service: CentralServiceService, private router: Router) {
    // creo la formGroup nel costruttore 
    this.formCreaPost = new FormGroup({
      testo: new FormControl('',Validators.required),
      titolo: new FormControl('',Validators.required)
    })
  }

  ngOnInit(): void {
  }

  vaiAllaHomepage() {
    this.router.navigateByUrl("private/homepage/");
  }

  // funzione di creazione del post
  creaPost() {
    // sottoscrizione alla richiesta http per la creazione del post
    this.sub = this.service.creaPost(
      { "titolo": this.formCreaPost.get("titolo")?.value, "testo": this.formCreaPost.get("testo")?.value }).subscribe(
        (next) => {
          if (next.status == 201) {
            this.messaggio = "creazione post avvenuta correttamente";
          } 
          // imposto il valore del messaggio nel servizio
          this.service.setMessaggio(this.messaggio);
          // torno alla homepage
          this.vaiAllaHomepage();
        },
        (error) =>{
          this.messaggio = "Ops... qualcosa è andato storto";
           // imposto il valore del messaggio nel servizio
           this.service.setMessaggio(this.messaggio);
           // torno alla homepage
           this.vaiAllaHomepage();
        }
      );
  }
}
