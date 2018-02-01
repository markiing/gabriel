import { Injectable, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { LocalStorageService, SessionStorageService } from 'ngx-webstorage';
import { User } from './../account/user';

@Injectable()
export class AuthService {

    showPrivateMenus = new EventEmitter<boolean>();

    isLoggedIn: boolean = true;
    redirectUrl: string;

    constructor(private http: Http, private storage: LocalStorageService, private router:Router) {
        this.checkUserToken();
    }

    logout(): void {
        this.storage.clear('userdata');
        this.storage.clear('token');
        this.isLoggedIn = false;
        this.router.navigate(['../']);
    }

    checkUserToken(): void {

        if(this.storage.retrieve('token') != null){
            this.isLoggedIn = true;
            this.showPrivateMenus.emit(true);
        } else {
            this.isLoggedIn = false;
            this.showPrivateMenus.emit(false);
        }
    }

    getToken() {
        return this.storage.retrieve('token');
    }

    getProfile(): Observable<User> {
        return this.http.get('v1/users/me')
            .map((res: Response) => res.json());
    }

    checkUserLogged(): boolean {
        return this.isLoggedIn;
    }

    singup(user): Observable<User>{
        return this.http.post('v1/users', user)
            .map((res: Response) => res.json());
    }

    forgot(email): Observable<any>{
        return this.http.post('v1/users/forgot', {email})
            .map((res: Response) => res.json());
    }

    login(email, password): Observable <any>{
        return this.http.post('v1/oauth/login/credentials', {email, password})
            .map((res: Response) => res.json());
    }

    refreshToken() {
        return this.http.post('/v1/oauth/refresh', {token: this.getToken()})
            .map(
                (res) => {
                    console.log(res);
                }
            );
    }
}
