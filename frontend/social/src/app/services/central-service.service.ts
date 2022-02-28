import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CentralServiceService {

  constructor(private http:HttpClient) { }

  getPost(): Observable<[]>{
    return this.http.post<[]>('http://localhost:8080/',{},{responseType:"json"});
  }

  // placeholder per l'utente
  user :{
    email: string,
    password: string
  } = {
    email : '',
    password : ''
  };

  login(cred : { email:string,password:string}){
    return this.http.post('http://localhost:8080/login', cred).pipe(
      tap((data) =>{
        this.user.email = cred.email;
        this.user.password = cred.password;
      })
    );
  }
}
