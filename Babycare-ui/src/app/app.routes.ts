import { Routes } from '@angular/router';
import { ExaminationBaby } from './examination-baby/examination-baby';
import { AddExamination } from './add-examination/add-examination';
import { Monitoring } from './monitoring/monitoring';
import { PostVaccination } from './post-vaccination/post-vaccination';

export const routes: Routes = [
    {path : "", component : ExaminationBaby},
    {path : 'addExamination/:babyId', component : AddExamination},
    {path : 'monitoring/:babyId', component : Monitoring},
    {path : 'postVaccination/:babyId', component : PostVaccination},
];
