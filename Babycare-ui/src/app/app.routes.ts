import { Routes } from '@angular/router';
import { ExaminationBaby } from './examination-baby/examination-baby';
import { AddExamination } from './add-examination/add-examination';
import { Monitoring } from './monitoring/monitoring';

export const routes: Routes = [
    {path : "", component : ExaminationBaby},
    {path : 'addExamination/:babyId', component : AddExamination},
    {path : 'monitoring/:babyId', component : Monitoring},
];
