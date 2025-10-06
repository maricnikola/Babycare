export class Examination {
  height?: number;             
  weight?: number;
  temperature?: number;
  heartRate?: number;          
  respirationRate?: number;
  crp?:number;
  erythrocytes?: number;
  symptoms?: SymptomName[];
}

export enum SymptomName {
  PALE_SKIN = 'PALE_SKIN',
  COUGH = 'COUGH',
  VOMITING = 'VOMITING',
  DYSPNEA = 'DYSPNEA',
  FATIGUE = 'FATIGUE',
  WHEEZING = 'WHEEZING',
  CHEST_PAIN = 'CHEST_PAIN',
  PROLONGED_EXPIRATION = 'PROLONGED_EXPIRATION',
  APPETITE_LOSS = 'APPETITE_LOSS',
  YELLOW_SKIN = 'YELLOW_SKIN',
  BLOOD_IN_URINE = 'BLOOD_IN_URINE',
  UNPLEASANT_URINE_ODOR = 'UNPLEASANT_URINE_ODOR',
  REDDNES_OF_SKIN = 'REDDNES_OF_SKIN',
  PIMPLED_SKIN = 'PIMPLED_SKIN',
  VOMITING_IN_STREAM = 'VOMITING_IN_STREAM'
}