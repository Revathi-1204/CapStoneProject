import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UPCResponse } from './upc/UPCResponse';

@Injectable({
  providedIn: 'root'
})
export class UpcService {
    private baseUrl = 'http://localhost:8080/donor';

    constructor(private http: HttpClient) {}

    generateUpc(userDetails: { number: string }): Observable<UPCResponse> {
      return this.http.post<UPCResponse>(`${this.baseUrl}/create`, userDetails);
    }
}
