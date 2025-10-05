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
import { WebSocketService } from '../service/websocket.service'
import { MonitoringService } from '../service/monitoring.service';

@Component({
  selector: 'app-monitoring',
  imports: [FormsModule, CommonModule, ButtonModule,ToggleButtonModule],
  templateUrl: './monitoring.html',
  styleUrl: './monitoring.css'
})
export class Monitoring {
  babyId!: number;
  heartRate: any;
  respirationRate: any;
  checked: boolean = false;
     facts: Array<{message: string, type: string}> = [];

    sendData() {
        this.facts.push({
            message: `Heart Rate: ${this.heartRate} bpm`,
            type: 'info'
        });
        
        this.facts.push({
            message: `Respiration Rate: ${this.respirationRate} breaths/min`,
            type: 'warning'
        });
        
        this.facts.push({
            message: `Oxygen Therapy: ${this.checked ? 'ON' : 'OFF'}`,
            type: this.checked ? 'success' : 'error'  // Zeleno ako ON, crveno ako OFF
        });
        
        this.facts.push({
            message: '---',
            type: 'separator'
        });
        
    }
  constructor(private router: Router,
    private route: ActivatedRoute,
    private socket: WebSocketService,
    private monitoringService: MonitoringService
  ) {}
  ngOnInit() {
    this.babyId = Number(this.route.snapshot.paramMap.get('babyId'));
    this.monitoringService.loadData().subscribe((response) => {
      console.log('Data loaded: ', response);
    });
    this.socket.subscribeToTopic('/topic/rules').subscribe((msg) => {
      console.log('Received: ', msg);
    });
  }
  goBack() {
    this.router.navigate(['/']);
  }
}
