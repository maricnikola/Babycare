import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { env } from '../../env/environment';

export enum SymptomType {
  REDNESS = 'REDNESS',
  SWELLING = 'SWELLING',
  PAIN = 'PAIN',
  CONSCIOUSNESS_CRISIS = 'CONSCIOUSNESS_CRISIS',
  COUGH = 'COUGH',
  LOW_FEVER = 'LOW_FEVER',
  MODERATE_FEVER = 'MODERATE_FEVER',
  HIGH_FEVER = 'HIGH_FEVER',
  CRITICAL_FEVER = 'CRITICAL_FEVER'
}


@Injectable({
  providedIn: 'root'
})
export class PostVaccinationService {

  private apiUrl = `${env.apiUrl}postVaccination`;

  constructor(private http: HttpClient) {}

  addSymptom(symptom: SymptomType, babyId: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${babyId}`, JSON.stringify(symptom), {
      headers: { 'Content-Type': 'application/json' }
    });
  }
}
