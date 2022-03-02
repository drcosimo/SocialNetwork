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

  ngOnDestroy(): void {
    // mi disiscrivo dal metodo per liberare memoria
    if (this.getSub) {
      this.getSub.unsubscribe();
    }
  }
}
