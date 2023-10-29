import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Request } from './request';
import { StatusDto } from './status-dto';

@Injectable({
  providedIn: 'root'
})
export class StatusService {
  private baseUrl = 'http://localhost:8080/donor';

  constructor(private http: HttpClient) {}

  getStatus(number: String): Observable<StatusDto> {
    return this.http.get<StatusDto>(`${this.baseUrl}/get/${number}`);
  }
  getAllRequests(): Observable<Request[]> {
    return this.http.get<Request[]>(`${this.baseUrl}/allrequests`);
  }
  verification(number:String):Observable<void>{
    return this.http.get<void>(`${this.baseUrl}/forward/${number}`);
  }

  deletePortingRequest(number: String) {
    return this.http.delete(`${this.baseUrl}/delete/${number}`);
  }

  getClearedRequests() {
    return this.http.get<Request[]>(`${this.baseUrl}/getcleared`);
  }
  
}