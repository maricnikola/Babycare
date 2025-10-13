import { Component, OnInit, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DatePickerModule } from 'primeng/datepicker';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { SelectChangeEvent, SelectModule } from 'primeng/select';
import { DiagnosisService, Disease } from '../service/diagnosis.service';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { Message } from 'primeng/message';

@Component({
  selector: 'app-disease-check',
  imports: [SelectModule, ButtonModule, InputTextModule,
     DatePickerModule, FormsModule, ToastModule, Message,CommonModule],
  providers:[DiagnosisService,MessageService],
  templateUrl: './disease-check.html',
  styleUrl: './disease-check.css'
})
export class DiseaseCheck {
  @Input() selectedBabyId: number | null = null;
  
  displayDialog = false;
  selectedDisease: Disease | null = null;
  diseaseOptions = [
    { label: 'Bronhiolitis', value: Disease.BRONHIOLITIS },
    { label: 'Pneumonia', value: Disease.PNEUMONIA },
    { label: 'Asthma', value: Disease.ASTHMA },
    { label: 'Anemia', value: Disease.ANEMIA }
  ];
  
  loading = false;
  error = "";
  warn = "";
  success = "";
  constructor(private diagnosisService: DiagnosisService,
    private messageService: MessageService
  ) {}
  
  ngOnInit(): void {}
  
  
  checkDisease(): void {
    if (!this.selectedBabyId || !this.selectedDisease) {
      return;
    }
    
    this.diagnosisService.checkDisease(this.selectedDisease, this.selectedBabyId)
    .subscribe({
      next: (result) => {
        this.loading = false;
        if(result.hasDisease){
          this.error = "";
          this.warn = "";
          this.success = `Baby has disease: ${result.disease}`;
          // this.messageService.add({ severity: 'success', summary: 'Success', detail: `Baby has disease: ${result.disease}` });
        }else{
          this.success = "";
          this.error = "";
          this.warn = `Baby does not have ${result.disease}`;
          // this.messageService.add({ severity: 'warn', summary: 'Warn', detail: `Baby does not have ${result.disease}`});
        }
        this.resetMessages();
      },
      error: (err) => {
        this.loading = false;
        const errorMsg =
          err.error?.message || 
          err.error?.error ||    
          'An error occurred while checking the disease';
        // this.messageService.add({ severity: 'error', summary: 'Error', detail: errorMsg });
        this.error = errorMsg;
        this.warn = "";
        this.success = "";
        this.resetMessages();
      }
    });
  }
  resetMessages(): void {
    setTimeout(() => {
      this.success = '';
      this.warn = '';
      this.error = '';
    }, 30000);
  }

}
