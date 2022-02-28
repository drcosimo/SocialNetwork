import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { CentralServiceService } from './services/central-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  posts: {
    id : number,
    title: string
  }[] = [];

  private getSub !: Subscription;

  constructor(private central:CentralServiceService){
  }

  ngOnInit(): void{
  }

  download():void{
    this.getSub = this.central.getPost().subscribe(
      // salvo i dati quando vengono ottenuti dalla chiamata
      (data) => {
        this.posts = data;
      }
    );
  }

  ngOnDestroy(): void{
    // mi disiscrivo dal metodo per rilevare memoria
    if(this.getSub){
      this.getSub.unsubscribe();
    }
  }
}
