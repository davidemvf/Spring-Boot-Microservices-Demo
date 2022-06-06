import { Injectable } from '@angular/core';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
    TOKEN_KEY: string = 'auth-token';
    USER_KEY: string = 'auth-user';
  
    constructor() { }

    signOut() {
        window.sessionStorage.clear();
    }
    
    public saveToken(token: string) {
        window.sessionStorage.removeItem(this.TOKEN_KEY);
        window.sessionStorage.setItem(this.TOKEN_KEY, token);
    }
    
    public getToken(): string {
        return sessionStorage.getItem(this.TOKEN_KEY);
    }
    
    public saveUser(user: User) {
        window.sessionStorage.removeItem(this.USER_KEY);
        window.sessionStorage.setItem(this.USER_KEY, JSON.stringify(user));
    }
  
    public getUser() {
        return JSON.parse(sessionStorage.getItem(this.USER_KEY));
    }
}