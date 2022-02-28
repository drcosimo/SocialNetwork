import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Post } from 'src/post';
import { User } from 'src/user';
import { StringDecoder } from 'string_decoder';

@Injectable({
  providedIn: 'root'
})
export class CentralServiceService {

  constructor(private http:HttpClient) { }

  // placeholder per l'utente
  user :{
    email: string,
    password: string
  } = {
    email : '',
    password : ''
  };

  login(cred : { email:string,password:string}){
    return this.http.post<User>('http://localhost:8080/login', cred, {observe:"response"});
  }

  // salvo le credenziali
  setUser(user:User|null){
    if(user != null){
      this.user.email = user.email;
      this.user.password = user.password;
    }
  }
  // get di tutti i post
  getPost(){

    const bodyReq = {"nickname": this.user.email, "password": this.user.password}

    return this.http.post<Post []>('http://localhost:8080/',bodyReq,{observe:"response"});
  }
}
