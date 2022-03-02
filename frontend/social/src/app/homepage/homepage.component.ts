import { Component, DoCheck, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Post } from 'src/post';
import { CentralServiceService } from '../services/central-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private service: CentralServiceService) { }

  // lista di post
  posts: Post[] = [];
  getSub!: Subscription;
  
  ngOnInit(): void {
  }
  ngOnDestroy(): void{
  }
}
