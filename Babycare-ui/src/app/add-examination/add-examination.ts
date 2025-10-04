import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Listbox } from 'primeng/listbox';
import { Examination, SymptomName } from '../model/examination';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { InputNumber } from 'primeng/inputnumber';
import { BabyService } from '../service/baby.service';

@Component({
  selector: 'app-add-examination',
  imports: [FormsModule, Listbox, CommonModule, ButtonModule, InputNumber],
  templateUrl: './add-examination.html',
  styleUrl: './add-examination.css'
})
export class AddExamination {
  
  constructor(private router: Router, private babyService: BabyService, private route: ActivatedRoute) {}
  selectedSymptoms: any[] = [];
  symptoms: { name: string; code: SymptomName }[] = [];
  temperature!: number;
  height!: number;
  weight!: number;
  heartRate!: number;
  respirationRate!: number;
  babyId!: number;
  ngOnInit() {
    this.babyId = Number(this.route.snapshot.paramMap.get('babyId'));
    this.symptoms = Object.values(SymptomName).map((symptom) => ({
      name: this.formatSymptomName(symptom),
      code: symptom,
    }));
  }

  formatSymptomName(symptom: string): string {
    return symptom
      .toLowerCase()
      .split('_')
      .map((w) => w.charAt(0).toUpperCase() + w.slice(1))
      .join(' ');
  }

  addExamination(){
    if (!this.selectedSymptoms || !this.temperature || !this.height || !this.weight || !this.heartRate || !this.respirationRate) {
      alert('Please fill in all fields before saving.');
      return;
    }
    const examination: Examination = {
      height: this.height, 
      weight: this.weight,
      temperature: this.temperature,
      heartRate: this.heartRate,
      respirationRate: this.respirationRate,
      symptoms: this.selectedSymptoms.map(s => s.code)
    }
    console.log(examination);
    this.babyService.createExamination(this.babyId, examination).subscribe({
      next: (res) => console.log('Examination created:', res),
      error: (err) => console.error('Error:', err)
    });
  }
  goBack() {
    this.router.navigate(['/']);
  }
}
