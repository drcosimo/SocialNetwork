import { Component, OnInit } from '@angular/core';
import { Post } from 'src/post';
import { CentralServiceService } from '../services/central-service.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private service: CentralServiceService) { }

  // lista di post
  posts: Post[] = [];


  ngOnInit(): void {
    this.service.getPost().subscribe(
      response => {
        if (response.status == 200) {
          if(response.body != null){
           
          }
        }
      }
    );
  }

}
