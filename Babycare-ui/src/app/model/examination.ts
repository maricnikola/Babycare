export class Examination {
  height?: number;             
  weight?: number;
  temperature?: number;
  heartRate?: number;          
  respirationRate?: number;
  symptoms?: SymptomName[];
}

export enum SymptomName {
  PALE_SKIN = 'PALE_SKIN',
  COUGH = 'COUGH',
  VOMITING = 'VOMITING',
  TACHYPNEA = 'TACHYPNEA',
  DYSPNEA = 'DYSPNEA',
  FATIGUE = 'FATIGUE',
  WHEEZING = 'WHEEZING',
  CHEST_PAIN = 'CHEST_PAIN',
  PROLONGED_EXPIRATION = 'PROLONGED_EXPIRATION',
  APPETITE_LOSS = 'APPETITE_LOSS',
}