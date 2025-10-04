export interface Baby {
  id?: number;
  firstName: string;
  lastName: string;
  birthDate?: Date;
  gender?: Gender;
  height?: number;
  weight?: number;
}
export enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE'
}
