import { Component, DoCheck, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Post } from 'src/post';
import { CentralServiceService } from '../services/central-service.service';

@Component({
  selector: 'app-visualizza-post',
  templateUrl: './visualizza-post.component.html',
  styleUrls: ['./visualizza-post.component.css']
})
  export class VisualizzaPostComponent implements OnInit,DoCheck {

  constructor(private service: CentralServiceService) { }

  // lista di post
  posts: Post[] = [];
  getSub!: Subscription;
  messaggio !: string|undefined;

  // controllo appartenenza al post
  confrontaUtenti(nickname:string):boolean{
    return this.service.user.nickname == nickname;
  }

  ngOnInit(): void {
    this.getSub = this.service.getPost().subscribe(
      // salvo i dati quando vengono ottenuti dalla chiamata
      (data) => {
        if (data.body != null && data.status == 200) {
          this.posts = data.body;
        }
      }, (error) => (console.trace(error))
    );
  }
  
  ngDoCheck():void{
    if(this.messaggio == ""){
      this.messaggio = this.service.messaggio;
    }else{
      this.messaggio = "";      
    }
  }

  aggiornaPost(){
    this.getSub = this.service.getPost().subscribe(
      // salvo i dati quando vengono ottenuti dalla chiamata
      (data) => {
        if (data.body != null && data.status == 200) {
          this.posts = data.body;
        }
      }, (error) => (console.trace(error))
    );
  }

  ngOnDestroy(): void {
    // mi disiscrivo dal metodo per liberare memoria
    if (this.getSub) {
      this.getSub.unsubscribe();
    }
  }

  mettiLike(post:Post){
    this.service.like(post.id).subscribe(
      (next)=> {
        if(next.status == 200){
          this.aggiornaPost();
        }
      }
    )
  }

  mettiDislike(post:Post){
    this.service.dislike(post.id).subscribe(
      (next)=> {
        if(next.status == 200){
          this.aggiornaPost();
        }
      }
    )
  }
}
