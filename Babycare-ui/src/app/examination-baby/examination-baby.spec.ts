import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationBaby } from './examination-baby';

describe('ExaminationBaby', () => {
  let component: ExaminationBaby;
  let fixture: ComponentFixture<ExaminationBaby>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExaminationBaby]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExaminationBaby);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
