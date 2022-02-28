import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Post } from 'src/post';
import { User } from 'src/user';

@Injectable({
  providedIn: 'root'
})
export class CentralServiceService {

  constructor(private http:HttpClient) { }

  // placeholder per l'utente
  user :User= {
     nickname : '',
    password : ''
  };

  login(cred : User){
    // salvo le credenziali
    this.setUser(cred);
    return this.http.post<User>('http://localhost:8080/login', cred, {observe:"response"});
  }

  // salvo le credenziali
  setUser(user:User|null){
    if(user != null){
      this.user.nickname = user.nickname;
      this.user.password = user.password;
    }
  }

  // ottengo tutti i post
  getPost(){
    const bodyReq = {"nickname": this.user.nickname, "password": this.user.password};

    return this.http.post<Post []>('http://localhost:8080/vediTuttiPost',bodyReq,{observe:"response"});
  }
}
