import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddExamination } from './add-examination';

describe('AddExamination', () => {
  let component: AddExamination;
  let fixture: ComponentFixture<AddExamination>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddExamination]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddExamination);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
