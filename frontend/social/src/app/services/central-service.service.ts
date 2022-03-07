import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { Post } from 'src/post';
import { RegistraUser } from 'src/registraUser';
import { User } from 'src/user';
import { VisualizeUser } from 'src/VisualizeUser';

@Injectable({
  providedIn: 'root'
})
export class CentralServiceService {

  constructor(private http:HttpClient, private router: Router) { }

  // placeholder per l'utente
  user :User= {
    nickname:"",
    password:""
  }

  messaggio:string = "";

  login(cred : User){
    // salvo le credenziali
    this.setUser(cred);
    return this.http.post<VisualizeUser>('http://localhost:8080/login', cred, {observe:"response"});
  }

  // salvo le credenziali
  setUser(user:User){
    if(user != null){
      this.user.nickname = user.nickname;
      this.user.password = user.password;
    }
  }

  // ottengo tutti i post
  getPost(){
    if(this.user.nickname != "" && this.user.password != ""){
      const bodyReq = {nickname: this.user.nickname, password: this.user.password};

      return this.http.post<Post []>('http://localhost:8080/vediTuttiPost',bodyReq,{observe:"response"});
    }else{
      this.router.navigateByUrl("login");
    }
  }

  // ottengo tutti i post associati ad un utente
  getPostUser(){
    if(this.user.nickname != "" && this.user.password != ""){
      const bodyReq = {nickname: this.user.nickname, password: this.user.password};
  
      return this.http.post<Post []>('http://localhost:8080/vediPostUtente',bodyReq,{observe:"response"});
    }else{
        this.router.navigateByUrl("login");
      }
  }

  // creazione di un nuovo post
  creaPost(post :{titolo:string,testo:string}){
    if(this.user.nickname != "" && this.user.password != ""){
    const bodyReq = {nickname: this.user.nickname, password : this.user.password,titolo:post.titolo,testo:post.testo};

    return this.http.post<Post>('http://localhost:8080/creaPost',bodyReq,{observe:"response"});
    }else{
      this.router.navigateByUrl("login");
    }
  }

  // registrazione nuovo utente
  registrazione(user:RegistraUser){
    return this.http.post<RegistraUser>('http://localhost:8080/registrazione',user,{observe:"response"});
  }

  setMessaggio(mex:string){
    this.messaggio = mex;
  }
  
  like(idPost:number){
    return this.http.post<Post>('http://localhost:8080/miPiace',{id:idPost,nickname:this.user.nickname, password:this.user.password}, {observe:"response"});
  }

  dislike(idPost:number){
    return this.http.post<Post>('http://localhost:8080/nonMiPiace',{id:idPost,nickname:this.user.nickname, password:this.user.password}, {observe:"response"});
  }
    
}
