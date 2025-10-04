import { Component } from '@angular/core';
import { Dialog } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DatePickerModule } from 'primeng/datepicker';
import { FormsModule } from '@angular/forms';
import { InputNumber } from 'primeng/inputnumber';
import { CascadeSelectModule } from 'primeng/cascadeselect';
import { Baby, Gender } from '../model/baby';
import { BabyService } from '../service/baby.service';
import { Router } from '@angular/router';
import { SelectModule } from 'primeng/select';

@Component({
  selector: 'app-examination-baby',
  imports: [SelectModule, CascadeSelectModule, Dialog, ButtonModule, InputTextModule, DatePickerModule, FormsModule, InputNumber],
  providers: [BabyService],
  templateUrl: './examination-baby.html',
  styleUrl: './examination-baby.css'
})
export class ExaminationBaby {
    
  constructor(private babyService: BabyService, private router: Router) {}
    firstName!: string;
    lastName!: string;
    genders: string[] = ["FEMALE", "MALE"];
    selectedGender!: string;
    height: number = 0;
    weight: number = 0;
    visible: boolean = false;
    birthDate!: Date;

    selectedBabyId: number | null = null;
    babies: any[] = [];

    onBabyChange(event: any) {
      this.selectedBabyId = event.value;
      console.log('Selected baby ID:', event.value);
    }

    showDialog() {
        this.visible = true;
    }

    ngOnInit() {

      this.babyService.getAllBabies().subscribe(babies => {
        this.babies = babies.map(b => ({
          label: `${b.firstName} ${b.lastName}`,
          value: b.id
        }));
      });
        
    //   const babyData: Baby[] = [
    //   { id: 1, firstName: 'John', lastName: 'Doe' },
    //   { id: 2, firstName: 'Emma', lastName: 'Smith' },
    //   { id: 3, firstName: 'Lucas', lastName: 'Brown' }
    // ];

    // this.babies = babyData.map(baby => ({
    //   label: `${baby.firstName} ${baby.lastName}`,
    //   value: baby.id
    // }));
    }

   saveBaby() {
    if (!this.birthDate || !this.firstName?.trim() || !this.lastName?.trim() || !this.selectedGender || !this.height || !this.weight) {
      alert('Please fill in all fields before saving.');
      return;
    }

    const newBaby: Baby = {
      firstName: this.firstName,
      lastName: this.lastName,
      birthDate: this.birthDate,
      gender: Gender[this.selectedGender as keyof typeof Gender],
      height: this.height,
      weight: this.weight
    };

    this.babyService.createBaby(newBaby).subscribe({
      next: (res) => console.log('Baby created:', res),
      error: (err) => console.error('Error:', err)
    });
   this.visible = false;
  }

    goToAddExamination() {
      if (this.selectedBabyId) {
        this.router.navigate(['/addExamination', this.selectedBabyId]);
      }
      console.log(this.selectedBabyId);
    }
}
