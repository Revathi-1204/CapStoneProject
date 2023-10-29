import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Request } from './request';
import { Subscriber } from './subscriber';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SubscriberService {
  private apiUrl = 'http://localhost:8080/sub';

  constructor(private http: HttpClient) {}

  addSubscriber(request: Request) {
    return this.http.post<Request>(`${this.apiUrl}/create`, request);
  }

  getSubscriberByNumber(number: string): Observable<Request> {
    return this.http.get<Request>(`${this.apiUrl}/getStatus/${number}`);
  }

  getAllSubscribers(): Observable<Subscriber[]> {
    return this.http.get<Subscriber[]>(`${this.apiUrl}/allSubscribers`);
  }

  activateSubscriber(number: string): Observable<Subscriber> {
    return this.http.get<Subscriber>(`${this.apiUrl}/activate/${number}`);
  }

}

