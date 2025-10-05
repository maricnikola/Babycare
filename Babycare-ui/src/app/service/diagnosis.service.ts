import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from '../../env/environment';
import { Observable } from 'rxjs';
import { Baby } from '../model/baby';

export enum Disease {
  BRONHIOLITIS = 'BRONHIOLITIS',
  PNEUMONIA = 'PNEUMONIA',
  ASTHMA = 'ASTHMA',
  ANEMIA = 'ANEMIA'
}

@Injectable({
  providedIn: 'root'
})
export class DiagnosisService {
  private apiUrl = `${env.apiUrl}diagnosis`; 

  constructor(private http: HttpClient) {}

  checkDisease(disease: Disease, babyId: number): Observable<any> {
    const url = `${this.apiUrl}?disease=${disease}&babyId=${babyId}`;
    return this.http.get<any>(url);
  }
}
