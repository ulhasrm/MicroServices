
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import { LoggedInUser } from '@/models/logerInUser';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<LoggedInUser>;
    role: String;
    public currentUser: Observable<LoggedInUser>;
    authUrl = environment.authUrl;
    loggedUser : LoggedInUser;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<LoggedInUser>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): LoggedInUser {
        return this.currentUserSubject.value;
    }

    public get isAdmin(): Boolean {
        return this.currentUserSubject.value.admin == true;
    }

    login(username: string, password: string) {
        return this.http.post<LoggedInUser>(
            this.authUrl,
            {
                username: username,
                password: password,
                role: this.role
            },
        ).pipe(map(user => {
            if (user && user.token) {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('currentUser', JSON.stringify(user));
                this.loggedUser = user;
                this.currentUserSubject.next(user);
            }

            return user;
        }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}