import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { env } from '../../env/environment';

@Injectable({
  providedIn: 'root'
})
export class MonitoringService {

  private apiUrl = `${env.apiUrl}monitoring`;

  constructor(private http: HttpClient) {}

  loadData(): Observable<string> {
    return this.http.post(this.apiUrl + '/load', {}, { responseType: 'text' });
  }
}
