import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadSpinnerCircleComponent } from './load-spinner-circle.component';

describe('LoadSpinnerCircleComponent', () => {
  let component: LoadSpinnerCircleComponent;
  let fixture: ComponentFixture<LoadSpinnerCircleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoadSpinnerCircleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoadSpinnerCircleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
