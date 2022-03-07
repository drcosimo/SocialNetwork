import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { Post } from 'src/post';
import { CentralServiceService } from './services/central-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  posts: Post[] = [];

  public vaiALogin :boolean=true;

  constructor(private central:CentralServiceService){
  }

  ngOnInit(): void{
  }

  redirect(){
    this.vaiALogin = !this.vaiALogin;
  }
}
