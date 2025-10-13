import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiseaseCheck } from './disease-check';

describe('DiseaseCheck', () => {
  let component: DiseaseCheck;
  let fixture: ComponentFixture<DiseaseCheck>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DiseaseCheck]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiseaseCheck);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
