import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from 'src/post';
import { User } from 'src/user';
import { VisualizeUser } from 'src/VisualizeUser';
import { CentralServiceService } from '../services/central-service.service';

@Component({
  selector: 'app-profilo',
  templateUrl: './profilo.component.html',
  styleUrls: ['./profilo.component.css']
})
export class ProfiloComponent implements OnInit {

  constructor(private service: CentralServiceService, private router: Router) { }

  user: VisualizeUser={
    nickname:"",
    nome:"",
    email:"",
    cognome:"",
    dataNascita:null
    };
  posts: Post[] = [];

  ngOnInit(): void {
    // ottengo i dati dell'utente
    this.service.login({ nickname: this.service.user.nickname, password: this.service.user.password }).subscribe(
      (next) => {
        if (next.body != null && next.status == 200) {
          this.user.nickname = next.body.nickname;
          this.user.nome = next.body.nome;
          this.user.cognome = next.body.cognome;
          this.user.email= next.body.email;
          this.user.dataNascita = next.body.dataNascita;
        }
      },
      (error) => {
        // redirezione pagina di login
        this.router.navigateByUrl("/login");
      }
    );
    // ottengo i post a lui associati
    this.service.getPostUser().subscribe(
      (next) => {
        if (next.status == 200 && next.body != null) {
          this.posts = next.body;
        }
      },
      (error) => {
        // redirezione pagina di login
        this.router.navigateByUrl("/login");
      }
    );
  
  }
}
