export interface Therapy{
    name: string;
    dose: number;
    frequency: number;
    method: string;
}
export interface Treatment {
  treatmentType: string; 
  durationDays: number;
  therapies?: Therapy[];
}