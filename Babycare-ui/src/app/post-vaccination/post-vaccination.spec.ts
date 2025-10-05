import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostVaccination } from './post-vaccination';

describe('PostVaccination', () => {
  let component: PostVaccination;
  let fixture: ComponentFixture<PostVaccination>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostVaccination]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostVaccination);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
