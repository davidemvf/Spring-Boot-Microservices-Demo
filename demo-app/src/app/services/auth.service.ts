import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../models/user.model";

@Injectable({
    providedIn: 'root'
  })
export class AuthService {

    AUTH_API : string = 'http://localhost:8080/api/auth/';
    httpOptions : any = {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
    
    constructor(private http: HttpClient) {

    }

    login(credentials): Observable<any> {
        return this.http.post(this.AUTH_API + 'signin', {
            username: credentials.username,
            password: credentials.password
        }, this.httpOptions);
    }

    register(user: User): Observable<any> {
        return this.http.post(this.AUTH_API + 'signup', {
            username: user.username,
            email: user.email,
            password: user.password
        }, this.httpOptions);
    }
}