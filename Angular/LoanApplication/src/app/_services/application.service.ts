import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../models/user';
import { Application } from '@/models/application';
import { environment } from '../../environments/environment';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class ApplicationService {
    loanAppUrl = environment.loanappUrl;
    constructor(private http: HttpClient) { }

    getAll(userId : any ) {
        var url = this.loanAppUrl + "User/" + userId + "/Application";
        return this.http.get<Application[]>(url);
    }

    getAllForAdmin() {
        var url = this.loanAppUrl + "/Application";
        return this.http.get<Application[]>(url);
    }

    getById(id: number) {
        var url = this.loanAppUrl + "/Application/"+id;
        return this.http.get<Application>(url);
    }

    create(loanType: String, amount: DoubleRange, userId: String) {
        var url = this.loanAppUrl + "User/" + userId + "/Application"
        return this.http.post<Application>(
            url,
            {
                "loanType": loanType,
                "amount": amount
            },
        ).pipe(map(result => {
            return result;
        }));
    }

    update(application: Application) {
        var url = this.loanAppUrl + "/Application";
        return this.http.put(url, application ).pipe(map(result => {
            return result;
        }));
    }

    delete(id: number) {
        return this.http.delete(`${config.apiUrl}/users/${id}`);
    }
}