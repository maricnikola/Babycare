import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Listbox } from 'primeng/listbox';
import { Examination, SymptomName } from '../model/examination';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { InputNumber } from 'primeng/inputnumber';
import { FloatLabel } from 'primeng/floatlabel';
import { ToggleButtonModule } from 'primeng/togglebutton';

@Component({
  selector: 'app-monitoring',
  imports: [FormsModule, CommonModule, ButtonModule,
     InputNumber, FloatLabel,ToggleButtonModule],
  templateUrl: './monitoring.html',
  styleUrl: './monitoring.css'
})
export class Monitoring {
  babyId!: number;
  heartRate: any;
  respirationRate: any;
  checked: any;
  constructor(private router: Router, private route: ActivatedRoute) {}
  ngOnInit() {
    this.babyId = Number(this.route.snapshot.paramMap.get('babyId'));
  }
  goBack() {
    this.router.navigate(['/']);
  }
}
