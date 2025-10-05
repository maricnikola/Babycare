import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { WebSocketService } from '../service/websocket.service'
import { PostVaccinationService, SymptomType } from '../service/postVaccination.service';
import { SelectModule } from 'primeng/select';

@Component({
  selector: 'app-post-vaccination',
  imports: [FormsModule, CommonModule, ButtonModule, SelectModule],
  templateUrl: './post-vaccination.html',
  styleUrl: './post-vaccination.css'
})
export class PostVaccination {
  symptomOptions: { label: string, value: SymptomType }[] = [
    { label: 'Redness', value: SymptomType.REDNESS },
    { label: 'Swelling', value: SymptomType.SWELLING },
    { label: 'Pain', value: SymptomType.PAIN },
    { label: 'Consciousness crisis', value: SymptomType.CONSCIOUSNESS_CRISIS },
    { label: 'Cough', value: SymptomType.COUGH },
    { label: 'Low fever (up to 37°C)', value: SymptomType.LOW_FEVER },
    { label: 'Moderate fever (37°C - 38.5°C)', value: SymptomType.MODERATE_FEVER },
    { label: 'High fever (above 38.5°C)', value: SymptomType.HIGH_FEVER },
    { label: 'Critical fever (above 38.5°C for more than 24h)', value: SymptomType.CRITICAL_FEVER }
  ];

  selectedSymptom: any;
  babyId!: number;
  facts: Array<{message: string, type: string}> = [];

  constructor(private router: Router,
    private route: ActivatedRoute,
    private socket: WebSocketService,
    private service: PostVaccinationService,
  ) {}

  addSymptom() {

    this.service.addSymptom(this.selectedSymptom, this.babyId).subscribe(() => {
      console.log('Symptom sent successfully');
    }, (error) => {
      console.error('Error sending symptom: ', error);
    });
  }
  ngOnInit() {
    this.babyId = Number(this.route.snapshot.paramMap.get('babyId'));

    this.socket.subscribeToTopic('/topic/alarm').subscribe((msg) => {
      console.log('Received: ', msg);  
      this.facts.push({
          message: `ALARM: ${msg}`,
          type: 'error'
      });
    });
    this.socket.subscribeToTopic('/topic/therapy').subscribe((msg) => {
      console.log('Received: ', msg);
      this.facts.push({
          message: `INFO: ${msg}`,
          type: 'info'
      }); 
    });
  }


  goBack() {
    this.router.navigate(['/']);
  }
}
