import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from '../../env/environment';
import { Observable } from 'rxjs';
import { Baby } from '../model/baby';
import { Examination } from '../model/examination';

@Injectable({
  providedIn: 'root'
})
export class BabyService {

  constructor(private http: HttpClient) {}

  createBaby(baby: Baby): Observable<Baby> {
    const url = env.apiUrl + 'baby';
    return this.http.post<Baby>(url, baby, {
      headers: { 'Content-Type': 'application/json' }
    });
  }
  // getAllBabies(): Observable<Baby[]>{
    
  //   return this.http.get<BabyDTO[]>(`${env.apiUrl}babies`).subscribe(babies => {
  // });
  // }

  getAllBabies():  Observable<Baby[]> {
    return this.http.get<Baby[]>(`${env.apiUrl}baby`);
  }
  createExamination(babyId: number, examination: Examination): Observable<Examination>{
    const url = `${env.apiUrl}examination/create/${babyId}`;
    return this.http.post<Examination>(url, examination, {
      headers: { 'Content-Type': 'application/json' }
    });
  }
}
